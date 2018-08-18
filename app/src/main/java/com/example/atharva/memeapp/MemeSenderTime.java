package com.example.atharva.memeapp;

public class MemeSenderTime {
    private String meme;
    private String sender;
    private int seen;
    private  String time;

    public MemeSenderTime(String meme, String sender, int seen, String time) {
        this.meme = meme;
        this.sender = sender;
        this.seen = seen;
        this.time = time;
    }

    public MemeSenderTime() {
    }


    public String getMeme() {
        return meme;
    }

    public void setMeme(String meme) {
        this.meme = meme;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
