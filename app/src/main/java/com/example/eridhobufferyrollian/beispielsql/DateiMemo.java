package com.example.eridhobufferyrollian.beispielsql;

/**
 * Created by en on 15.06.17.
 */

public class DateiMemo {

    private String username;
    private String password;
    private long id;


    public DateiMemo(String username, String password, long id) {
        this.username = username;
        this.password = password;
        this.id = id;
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        String output = id + " -- " + username;

        return output;
    }
}