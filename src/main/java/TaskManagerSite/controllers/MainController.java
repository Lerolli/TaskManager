package TaskManagerSite.controllers;

import TaskManagerSite.dataBase.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    UserRepository userRepository = TaskManagerSite.dataBase.UserRepository.getInstance();
    @GetMapping("/")
    public String home(Model model){
        return "home";
    }
}
