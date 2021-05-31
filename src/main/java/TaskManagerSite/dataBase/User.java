package TaskManagerSite.dataBase;

import java.util.ArrayList;
import java.util.Random;

public class User {

    private double id;
    private String username;
    private String password;
    private ArrayList<ArrayList> myLists;

    public User(){
        id = Math.random();
        username = "user" + id;
        password = "123";
        myLists = new ArrayList<>();
    }

    public ArrayList<ArrayList> getMyLists() {
        return myLists;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

}
