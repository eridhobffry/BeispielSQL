package com.example.eridhobufferyrollian.beispielsql;

/**
 * Created by en on 15.06.17.
 */

public class DateiMemo {

    private String username;
    private String password;
    private long uid;
    private boolean checked;

    public DateiMemo(String username, String password, long uid, boolean checked) {
        this.username = username;
        this.password = password;
        this.uid = uid;
        this.checked = checked;
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


    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked (boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        String output = uid + " -- " + username;
        return output;
    }
}