package TaskManagerSite.dataBase;

import java.util.ArrayList;
import java.util.Objects;

public class ListTasks {
    private String name;
    private ArrayList<String> tasks;

    public ListTasks(){
        name = "null";
        tasks = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public void editTask(String task, String newTask){
        for (int t = 0; t < tasks.size(); t++){
            if (tasks.get(t).equals(task)){
               tasks.set(t, newTask);
            }
        }
    }

    public void deleteTask(String task){
        for (int t = 0; t < tasks.size(); t++){
            if (tasks.get(t).equals(task)){
                tasks.remove(t);
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTasks() {
        return Objects.requireNonNullElseGet(tasks, ArrayList::new);
    }

    public void addTasks(String task) {
        tasks.add(task);
    }
}
