package TaskManagerSite.controllers;

import TaskManagerSite.dataBase.User;
import TaskManagerSite.dataBase.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

        UserRepository userRepository = UserRepository.getInstance();

        @GetMapping("/registration")
        public String registration(){
            return "registration";
        }

        @PostMapping("/registration")
        public String addUser(@RequestParam String username, @RequestParam String password, Model model){
            var newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            userRepository.addUser(newUser);
            return "redirect:/login";
        }
}
