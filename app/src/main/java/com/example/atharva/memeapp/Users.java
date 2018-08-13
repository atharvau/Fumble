package com.example.atharva.memeapp;

public class Users {
private String name;
    private String profilepicture;
    private String username;

    public Users(String name, String profilepicture, String username) {
        this.name = name;
        this.profilepicture = profilepicture;
        this.username = username;
    }

    public Users() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

