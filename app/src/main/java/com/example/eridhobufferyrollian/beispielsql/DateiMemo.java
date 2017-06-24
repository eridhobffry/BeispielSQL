package com.example.eridhobufferyrollian.beispielsql;

/**
 * Created by en on 15.06.17.
 */

public class DateiMemo {

    private String username;
    private String password;
    private long nid;
    private long peerId;
    private long neighId;


    public DateiMemo(String username, String password, long nid, long peerId, long neighId) {
        this.username = username;
        this.password = password;
        this.nid = nid;
        this.peerId = peerId;
        this.neighId = neighId;
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

    public long getPeerId() {
        return peerId;
    }

    public void setPeerId(long peerId){
        this.peerId = peerId;
    }

    public long getNeighId(){
        return neighId;
    }

    public void setNeighId(long neighId) {
        this.neighId = neighId;
    }


    @Override
    public String toString() {
        String output = nid + " -- " + username;

        return output;
    }
}