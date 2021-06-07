package TaskManagerSite.dataBase;

import java.util.ArrayList;

public class User {

    private final double id;
    private String username;
    private String password;
    private final ArrayList<ListTasks> myLists;
    private final ArrayList<ListTasks> mySharedList;

    public User(){
        id = Math.random();
        username = "user";
        password = "123";
        myLists = new ArrayList<>();
        mySharedList = new ArrayList<>();
    }

    public ArrayList<ListTasks> getMyLists() {
        return myLists;
    }

    public void addSharedList(ListTasks list){
        mySharedList.add(list);
    }

    public ArrayList<ListTasks> getSharedList(){
        return mySharedList;
    }
    public void addList(String name){
        var newList = new ListTasks();
        newList.setName(name);
        myLists.add(newList);
    }

    public ListTasks findList(String name) {
        for (var list : myLists) {
            if (list.getName().equals(name)) {
                return list;
            }
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public ListTasks findSharedList(String name) {
        for (var list : mySharedList) {
            if (list.getName().equals(name)) {
                return list;
            }
        }
        return null;
    }
}
