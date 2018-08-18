package com.example.atharva.memeapp;

public class ModelStory {

    private String profilepicture;
    private String sender;
    private int seen;
    private String meme;

    public ModelStory() {
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public String getMeme() {
        return meme;
    }

    public void setMeme(String meme) {
        this.meme = meme;
    }

    public ModelStory(String profilepicture, String sender, int seen, String meme) {
        this.profilepicture = profilepicture;
        this.sender = sender;
        this.seen = seen;
        this.meme = meme;
    }
}
