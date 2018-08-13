package com.example.atharva.memeapp;

public class modelmessagenew {
    private  String sender;
    private String reciver;
    private String pic;

    public modelmessagenew(String sender, String reciver, String pic) {
        this.sender = sender;
        this.reciver = reciver;
        this.pic = pic;
    }

    public modelmessagenew() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
