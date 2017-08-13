package com.example.eridhobufferyrollian.beispielsql.Model;

import com.example.eridhobufferyrollian.beispielsql.Model.DateiMemo;

/**
 * Created by eridhobufferyrollian on 12.08.17.
 */

public class ForeignData {
    DateiMemo dateiMemo;

    public long uid;
    public boolean checked;
    public int fotoId;



    public ForeignData(long uid, boolean checked, int fotoId){
        this.uid = uid;
        this.checked = checked;
        this.fotoId = fotoId;
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

    @Override
    public String toString() {
        String output = uid + " -- " + fotoId;
        return output;
    }
}
