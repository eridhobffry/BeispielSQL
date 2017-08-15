package com.example.eridhobufferyrollian.beispielsql.model;

/**
 * Created by en on 15.06.17.
 */

public class DateiMemo {
    PeerMemo peerMemo;

//    private String username;
//    private String password;
    private long uid;
    private double cornerTopRightX;
    private double cornerTopRightY;
    private double cornerTopLeftX;
    private double cornerTopLeftY;
    private double cornerBottomRightX;
    private double cornerBottomRightY;
    private double cornerBottomLeftX;
    private double cornerBottomLeftY;
    private double punktX;
    private double punktY;
    private String IP;
    private int countPeers;
    private boolean checked;

    public DateiMemo(long uid, boolean checked, double cornerTopRightX, double cornerTopRightY, double cornerTopLeftX, double cornerTopLeftY,
                     double cornerBottomRightX, double cornerBottomRightY, double cornerBottomLeftX, double cornerBottomLeftY, double punktX, double punktY, double IP, int countPeers) {
//        this.username = username;
//        this.password = password;
        this.uid = uid;
        this.checked = checked;
        this.cornerTopRightX = cornerTopRightX;
        this.cornerTopRightY = cornerTopRightY;
        this.cornerTopLeftX = cornerTopLeftX;
        this.cornerTopLeftY = cornerTopLeftY;
        this.cornerBottomRightX = cornerBottomRightX;
        this.cornerBottomRightY = cornerBottomRightY;
        this.cornerBottomLeftX = cornerBottomLeftX;
        this.cornerBottomLeftY = cornerBottomLeftY;
        this.punktX = punktX;
        this.punktY = punktY;
        this.IP = IP;
        this.countPeers = countPeers;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }


    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public double getCornerTopRightX() {
        return cornerTopRightX;
    }

    public void setCornerTopRightX(double cornerTopRightX) {
        this.cornerTopRightX = cornerTopRightX;
    }

    public double getCornerTopRightY() {
        return cornerTopRightY;
    }

    public void setCornerTopRightY(double cornerTopRightY) {
        this.cornerTopRightY = cornerTopRightY;
    }

    public double getCornerTopLeftX() {
        return cornerTopLeftX;
    }

    public void setCornerTopLeftX(double cornerTopLeftX) {
        this.cornerTopLeftX = cornerTopLeftX;
    }

    public double getCornerTopLeftY() {
        return cornerTopLeftY;
    }

    public void setCornerTopLeftY(double cornerTopLeftY) {
        this.cornerTopLeftY = cornerTopLeftY;
    }

    public double getCornerBottomRightX() {
        return cornerBottomRightX;
    }

    public void setCornerBottomRightX(double cornerBottomRightX) {
        this.cornerBottomRightX = cornerBottomRightX;
    }

    public double getCornerBottomRightY() {
        return cornerBottomRightY;
    }

    public void setCornerBottomRightY(double cornerBottomRightY) {
        this.cornerBottomRightY = cornerBottomRightY;
    }

    public double getCornerBottomLeftX() {
        return cornerBottomLeftX;
    }

    public void setCornerBottomLeftX(double cornerBottomLeftX) {
        this.cornerBottomLeftX = cornerBottomLeftX;
    }

    public double getCornerBottomLeftY() {
        return cornerBottomLeftY;
    }

    public void setCornerBottomLeftY(double cornerBottomLeftY) {
        this.cornerBottomLeftY = cornerBottomLeftY;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked (boolean checked) {
        this.checked = checked;
    }

//    public double getCornerTopRight() {
//        return cornerTopRight;
//    }
//
//    public void setCornerTopRight(double cornerTopRight) {
//        this.cornerTopRight = cornerTopRight;
//    }
//
//    public double getCornerTopLeft() {
//        return cornerTopLeft;
//    }
//
//    public void setCornerTopLeft(double cornerTopLeft) {
//        this.cornerTopLeft = cornerTopLeft;
//    }
//
//    public double getCornerBottomRight() {
//        return cornerBottomRight;
//    }
//
//    public void setCornerBottomRight(double cornerBottomRight) {
//        this.cornerBottomRight = cornerBottomRight;
//    }
//
//    public double getCornerBottomLeft() {
//        return cornerBottomLeft;
//    }
//
//    public void setCornerBottomLeft(double cornerBottomLeft) {
//        this.cornerBottomLeft = cornerBottomLeft;
//    }

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

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getCountPeers() {
        return peerMemo.getPeerCount();
    }
    public void setCountPeers(int countPeers) {
        this.countPeers = countPeers;
    }

    @Override
    public String toString() {
        String output = uid + " -- " + username + " Und andere Information.";
        return output;
    }
}