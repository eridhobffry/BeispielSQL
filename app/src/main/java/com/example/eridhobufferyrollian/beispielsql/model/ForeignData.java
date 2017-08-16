package com.example.eridhobufferyrollian.beispielsql.model;

/**
 * Created by eridhobufferyrollian on 12.08.17.
 * Class Foreign Data
 */

public class ForeignData {
    DateiMemo dateiMemo;

    private long uid;
    private boolean checked;
    private int fotoId;
    private double punktX;
    private double punktY;

    public ForeignData(long uid, boolean checked, int fotoId, double punktX, double punktY){
        this.uid = uid;
        this.checked = checked;
        this.fotoId = fotoId;
        this.punktX = punktX;
        this.punktY = punktY;

    }

    public long getUid() {
        return dateiMemo.getUid();
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getFotoId() {
        return fotoId;
    }

    public void setFotoId(int fotoId) {
        this.fotoId = fotoId;
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

    @Override
    public String toString() {
        String output = uid + " -- " + fotoId +
                "\nCorner Punkt : x -> "+ punktX + " -- y -> "+ punktY ;
        return output;
    }
}
