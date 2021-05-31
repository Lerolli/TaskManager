package TaskManagerSite.controllers;

import TaskManagerSite.dataBase.User;
import TaskManagerSite.dataBase.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationAndLoginController {

    UserRepository userRepository = UserRepository.getInstance();

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username, @RequestParam String password, Model model){

        User userFromDb = userRepository.findUserByUsername(username);

        if (userFromDb != null){
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        var newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.addUser(newUser);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model){
        var user = userRepository.findUserByUsername(username);
        if (user == null){
            return "login";
        }
        if (user.checkPassword(password)){
            userRepository.setActiveUser(user);
        }
        return "home";
    }

}
