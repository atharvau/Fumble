package com.example.atharva.memeapp;

public class Modelmemepost {
    private String name;
    private String uid;
    private String username;
    private String profilepicture;
    private String meme;
    private int like;
private String caption;

    public Modelmemepost(String name, String uid, String username, String profilepicture, String meme, int like, String caption) {
        this.name = name;
        this.uid = uid;
        this.username = username;
        this.profilepicture = profilepicture;
        this.meme = meme;
        this.like = like;
        this.caption = caption;
    }

    public Modelmemepost() {
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

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getMeme() {
        return meme;
    }

    public void setMeme(String meme) {
        this.meme = meme;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}


