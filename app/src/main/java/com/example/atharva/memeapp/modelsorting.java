package com.example.atharva.memeapp;

public class modelsorting {
    long count;
    String postname;

    public modelsorting(long count, String postname) {
        this.count = count;
        this.postname = postname;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public modelsorting() {
    }
}