package com.example.atharva.memeapp;

public class ModelStories {

    private String sender;
    private String meme;
    private int seen;
private String profilepicture;

    public ModelStories(String sender, String meme, int seen, String profilepicture) {
        this.sender = sender;
        this.meme = meme;
        this.seen = seen;
        this.profilepicture = profilepicture;
    }

    public ModelStories() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMeme() {
        return meme;
    }

    public void setMeme(String meme) {
        this.meme = meme;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }
}
