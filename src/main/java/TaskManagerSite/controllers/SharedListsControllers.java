package TaskManagerSite.controllers;


import TaskManagerSite.dataBase.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SharedListsControllers {
    private UserRepository userRepository = UserRepository.getInstance();

    @GetMapping("/sharedLists")
    private String sharedLists(Model model){
        var user = userRepository.getActiveUser();
        if (user == null)
            return "login";
        model.addAttribute("tasks", userRepository.getActiveUser().getSharedList());
        return "SharedLists";
    }

    @GetMapping("/shared/{name}")
    public String sharedList(@PathVariable(value = "name") String name, Model model){
        var list = userRepository.getActiveUser().findSharedList(name);
        model.addAttribute("list", list);
        return "currentSharedList";
    }

    @PostMapping("/shared/{name}")
    public String myListAdd(@PathVariable(value = "name") String name, @RequestParam String task, Model model){
        var list = userRepository.getActiveUser().findSharedList(name);
        list.addTasks(task);
        model.addAttribute("list", list);
        return "currentSharedList";
    }


    @GetMapping("/shared/{name}/{task}/edit")
    public String editTask(@PathVariable(value = "name") String name, @PathVariable(value = "task") String task, Model model){
        return "editTask";
    }

    @PostMapping("/shared/{name}/{task}/edit")
    public String editPostTask(@PathVariable(value = "name") String name, @PathVariable(value = "task") String task, @RequestParam String newTask, Model model){
        var list = userRepository.getActiveUser().findSharedList(name);
        list.editTask(task, newTask);
        return "redirect:/shared/{name}/";
    }

    @PostMapping("/shared/{name}/{task}/delete")
    public String editPostTask(@PathVariable(value = "name") String name, @PathVariable(value = "task") String task, Model model){
        var list = userRepository.getActiveUser().findSharedList(name);
        list.deleteTask(task);
        return "redirect:/shared/{name}/";
    }
}
