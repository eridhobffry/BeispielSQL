package com.example.eridhobufferyrollian.beispielsql.Model;


import com.example.eridhobufferyrollian.beispielsql.Model.DateiMemo;

public class NeighborMemo {

    DateiMemo dateiMemo;

    public double cornerTopRight;
    public double cornerTopLeft;
    public double cornerBottomRight;
    public double cornerBottomLeft;
    public double punktX;
    public double punktY;
    public double UIP;
    public double RTT;
    public boolean checked;
    public int uid;

    public NeighborMemo(int uid, boolean checked, double cornerTopRight, double cornerTopLeft, double cornerBottomRight, double cornerBottomLeft, double punktX, double punktY, double UIP, double RTT) {
        this.uid = uid;
        this.checked = checked;
        this.cornerTopRight = cornerTopRight;
        this.cornerTopLeft = cornerTopLeft;
        this.cornerBottomRight = cornerBottomRight;
        this.cornerBottomLeft = cornerBottomLeft;
        this.punktX = punktX;
        this.punktY = punktY;
        this.UIP = UIP;
        this.RTT = RTT;
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

    public double getUIP() {
        return UIP;
    }

    public void setUIP(double UIP) {
        this.UIP = UIP;
    }

    public double getRTT() {
        return RTT;
    }

    public void setRTT(double RTT) {
        this.RTT = RTT;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public long getUid() {
        return dateiMemo.getUid();
    }

    @Override
    public String toString() {
        String output = uid + " -- " + UIP;
        return output;
    }
}
