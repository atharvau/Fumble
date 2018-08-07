package com.example.atharva.memeapp;

public class ModelInfo
{
    private String name;
    private String uid;
    private String username;
    private String profilepic;


    public ModelInfo(String name, String uid, String username, String profilepic) {
        this.name = name;
        this.uid = uid;
        this.username = username;
        this.profilepic = profilepic;
    }

    public ModelInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }
}

