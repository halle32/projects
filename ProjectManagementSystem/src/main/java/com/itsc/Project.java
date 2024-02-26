package com.itsc;

public class Project {
    private int id;
    private String name;
    private String description;
    private int adminId;

    public Project() {
    }

    public Project(int id, String name, String description, int adminId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.adminId = adminId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}