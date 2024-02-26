package com.itsc;

public class Users {
    private int id;
    private String name;
    private String description;


    public Users() {
    }

    public Users(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpwd() {
        return description;
    }

    public void setpwd(String description) {
        this.description = description;
    }
}
