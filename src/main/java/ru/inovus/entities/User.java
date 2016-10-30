package ru.inovus.entities;

/**
 * Created by Vlad.M on 27.10.2016.
 */
public class User {
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
    private String name;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
