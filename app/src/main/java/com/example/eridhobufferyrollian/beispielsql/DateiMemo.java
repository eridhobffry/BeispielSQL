package com.example.eridhobufferyrollian.beispielsql;

/**
 * Created by en on 15.06.17.
 */

public class DateiMemo {

    private String username;
    private String password;
    private long nid;


    public DateiMemo(String username, String password, long nid) {
        this.username = username;
        this.password = password;
        this.nid = nid;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public long getNid() {
        return nid;
    }

    public void setNid(long nid) {
        this.nid = nid;
    }


    @Override
    public String toString() {
        String output = nid + " -- " + username;

        return output;
    }
}