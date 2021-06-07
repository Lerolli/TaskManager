package TaskManagerSite.controllers;

import TaskManagerSite.dataBase.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class myListController {
    private UserRepository userRepository = UserRepository.getInstance();

    @GetMapping("/myLists")
    public String myLists(Model model){
        var user = userRepository.getActiveUser();
        if (user == null)
            return "login";
        model.addAttribute("tasks", userRepository.getActiveUser().getMyLists());
        return "myLists";
    }

    @PostMapping("/myLists")
    public String myListsAdd(@RequestParam String nameList, Model model){
        userRepository.getActiveUser().addList(nameList);
        model.addAttribute("tasks", userRepository.getActiveUser().getMyLists());

        return "myLists";
    }

    @GetMapping("/myLists/{name}")
    public String myList(@PathVariable(value = "name") String name, Model model){
        var list = userRepository.getActiveUser().findList(name);
        model.addAttribute("list", list);

        return "currentMyList";
    }

    @PostMapping("/myLists/{name}")
    public String myListAdd(@PathVariable(value = "name") String name, @RequestParam String task, Model model){
        var list = userRepository.getActiveUser().findList(name);
        list.addTasks(task);
        model.addAttribute("list", list);
        return "currentMyList";
    }

    @GetMapping("/myLists/{name}/{task}/edit")
    public String editTask(@PathVariable(value = "name") String name, @PathVariable(value = "task") String task, Model model){
        return "editTask";
    }

    @PostMapping("/myLists/{name}/{task}/edit")
    public String editPostTask(@PathVariable(value = "name") String name, @PathVariable(value = "task") String task, @RequestParam String newTask, Model model){
        var list = userRepository.getActiveUser().findList(name);
        list.editTask(task, newTask);
        return "redirect:/myLists/{name}/";
    }

    @PostMapping("/myLists/{name}/{task}/delete")
    public String editPostTask(@PathVariable(value = "name") String name, @PathVariable(value = "task") String task, Model model){
        var list = userRepository.getActiveUser().findList(name);
        list.deleteTask(task);
        return "redirect:/myLists/{name}/";
    }

    @GetMapping("/myLists/{name}/share")
    public String shareGetList(@PathVariable(value = "name") String name, Model model){
        return "shareTask";
    }

    @PostMapping("/myLists/{name}/share")
    public String shareList(@PathVariable(value = "name") String name, @RequestParam String userName, Model model){
        var user = userRepository.findUserByUsername(userName);
        if (user != null)
            user.addSharedList(userRepository.getActiveUser().findList(name));
        return "redirect:/myLists";
    }

}
