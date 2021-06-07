package TaskManagerSite.dataBase;


import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        var index = nameUsers.indexOf(user.getUsername());
        if (index != -1) {
            userList.set(index, user);
            nameUsers.add(user.getUsername());
        }
        else{
            userList.add(user);
            nameUsers.add(user.getUsername());
        }
        updateDataBase();
    }

    public ArrayList<ListTasks> showListsUser(User user){
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

    public void getUserFromDataBase(){
        userList.clear();
        nameUsers.clear();
        try {
            Scanner sc = new Scanner(new File("src\\bestDataBase.txt"));
            var str = "";
            while(!str.equals("end")){
                str = sc.nextLine();
                if (!sc.hasNextLine())
                    break;
                var user = new User();
                user.setUsername(str);

                str = sc.nextLine();
                user.setPassword(str);

                sc.nextLine(); //Mylists
                str = sc.nextLine();

                while (!str.equals("SharedLists")) {
                    user.addList(str);
                    var ListTask = user.findList(str);
                    str = sc.nextLine();
                    while (!str.equals("!endList!")) {
                        ListTask.addTasks(str);
                        str = sc.nextLine();
                    }
                    str = sc.nextLine();
                }
                str = sc.nextLine();
                while (!str.equals("endUser")){
                    var listTask = new ListTasks();
                    listTask.setName(str);
                    str = sc.nextLine();
                    while (!str.equals("!endList!")) {
                        listTask.addTasks(str);
                        str = sc.nextLine();
                    }
                    user.addSharedList(listTask);
                    str = sc.nextLine();
                }
                userList.add(user);
                nameUsers.add(user.getUsername());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateDataBase(){
        try(FileWriter writer = new FileWriter("src\\bestDataBase.txt", false))
        {
            var strider = new StringBuilder();
            for (var user : userList) {
                strider.append(user.getUsername() + "\n");
                strider.append(user.getPassword() + "\n");
                strider.append("myLists\n");
                for (var list : user.getMyLists()) {
                    strider.append(list.getName() + "\n");
                    for (var task : list.getTasks())
                        strider.append(task + "\n");
                    strider.append("!endList!\n");
                }
                strider.append("SharedLists\n");

                for (var list : user.getSharedList()) {
                    strider.append(list.getName() + "\n");
                    for (var task : list.getTasks())
                        strider.append(task + "\n");
                    strider.append("!endList!\n");
                }
                strider.append("endUser\n");
            }
            strider.append("end");
            writer.write(strider.toString());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}