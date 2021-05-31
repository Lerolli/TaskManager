package TaskManagerSite.dataBase;

import java.util.ArrayList;

public class UserRepository {
    private final ArrayList<User> userList;
    private final ArrayList<String> nameUsers;
    private static volatile UserRepository instance;
    private User activeUser;

    public UserRepository(){
        userList = new ArrayList<>();
        nameUsers = new ArrayList<>();
        activeUser = null;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public User getActiveUser(){
        return activeUser;
    }


    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void addUser(User user){
        userList.add(user);
        nameUsers.add(user.getUsername());
    }

    public ArrayList<ArrayList> showListsUser(User user){
        var index = userList.indexOf(user);
        if (index != -1)
            return userList.get(index).getMyLists();
        else
            return null;
    }


    public void removeUser(User user){
        userList.remove(user);
        nameUsers.remove(user.getUsername());
    }

    public User findUserByUsername(String name){
        var index = nameUsers.indexOf(name);

        if (index != -1)
            return userList.get(index);
        else
            return null;
    }
}