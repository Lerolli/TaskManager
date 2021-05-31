package TaskManagerSite.controllers;

import TaskManagerSite.dataBase.User;
import TaskManagerSite.dataBase.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    UserRepository userRepository = UserRepository.getInstance();

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userFromDb = userRepository.findUserByUsername(user.getUsername());

        if (userFromDb != null){
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        userRepository.addUser(user);
        return "redirect:/login";
    }

}
