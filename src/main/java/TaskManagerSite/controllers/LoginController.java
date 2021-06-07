package TaskManagerSite.controllers;

import TaskManagerSite.dataBase.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    UserRepository userRepository = UserRepository.getInstance();


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model){
        userRepository.getUserFromDataBase();
        var user = userRepository.findUserByUsername(username);
        if (user == null){
            return "login";
        }
        if (user.checkPassword(password)){
            userRepository.setActiveUser(user);
        }

        return "home";
    }

    @GetMapping("/reLogin")
    public String reLogin(){
        userRepository.updateDataBase();
        userRepository.setActiveUser(null);

        return "redirect:/login";
    }

}
