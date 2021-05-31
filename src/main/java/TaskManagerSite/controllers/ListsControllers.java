package TaskManagerSite.controllers;


import TaskManagerSite.dataBase.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListsControllers {
    private UserRepository userRepository = UserRepository.getInstance();


    @GetMapping("/myLists")
    public String myLists(Model model){
        var user = userRepository.getActiveUser();
        if (user == null)
            return "login";
        return "myLists";
    }

    @GetMapping("/sharedLists")
    private String sharedLists(Model model){
        var user = userRepository.getActiveUser();
        if (user == null)
            return "login";
        return "sharedLists";
    }
}
