package com.example.atharva.memeapp;

public class ModelMessage {
     String message;
     String uid;
     String profilepic;

    public ModelMessage(String message, String uid, String profilepic) {
        this.message = message;
        this.uid = uid;
        this.profilepic = profilepic;
    }

    public String getMessage() {
        return message;
    }

    public ModelMessage() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

}
