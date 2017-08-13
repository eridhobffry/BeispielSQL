package com.example.eridhobufferyrollian.beispielsql.Model;

/**
 * Created by en on 15.06.17.
 */

public class DateiMemo {
    PeerMemo peerMemo;

    private String username;
    private String password;
    private long uid;
    public double cornerTopRight;
    public double cornerTopLeft;
    public double cornerBottomRight;
    public double cornerBottomLeft;
    public double punktX;
    public double punktY;
    public double IP;
    public int countPeers;
    private boolean checked;

    public DateiMemo(String username, String password, long uid, boolean checked, double cornerTopRight, double cornerTopLeft, double cornerBottomRight, double cornerBottomLeft, double punktX, double punktY, double IP, int countPeers) {
        this.username = username;
        this.password = password;
        this.uid = uid;
        this.checked = checked;
        this.cornerTopRight = cornerTopRight;
        this.cornerTopLeft = cornerTopLeft;
        this.cornerBottomRight = cornerBottomRight;
        this.cornerBottomLeft = cornerBottomLeft;
        this.punktX = punktX;
        this.punktY = punktY;
        this.IP = IP;
        this.countPeers = countPeers;
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

    public double getCornerTopRight() {
        return cornerTopRight;
    }

    public void setCornerTopRight(double cornerTopRight) {
        this.cornerTopRight = cornerTopRight;
    }

    public double getCornerTopLeft() {
        return cornerTopLeft;
    }

    public void setCornerTopLeft(double cornerTopLeft) {
        this.cornerTopLeft = cornerTopLeft;
    }

    public double getCornerBottomRight() {
        return cornerBottomRight;
    }

    public void setCornerBottomRight(double cornerBottomRight) {
        this.cornerBottomRight = cornerBottomRight;
    }

    public double getCornerBottomLeft() {
        return cornerBottomLeft;
    }

    public void setCornerBottomLeft(double cornerBottomLeft) {
        this.cornerBottomLeft = cornerBottomLeft;
    }

    public double getPunktX() {
        return punktX;
    }

    public void setPunktX(double punktX) {
        this.punktX = punktX;
    }

    public double getPunktY() {
        return punktY;
    }

    public void setPunktY(double punktY) {
        this.punktY = punktY;
    }

    public double getIP() {
        return IP;
    }

    public void setIP(double IP) {
        this.IP = IP;
    }

    public int getCountPeers() {
        return peerMemo.getPeerCount();
    }

    @Override
    public String toString() {
        String output = uid + " -- " + username + " Und andere Information.";
        return output;
    }
}