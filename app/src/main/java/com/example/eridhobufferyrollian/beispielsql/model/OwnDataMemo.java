package com.example.eridhobufferyrollian.beispielsql.model;

/**
 * Created by eridhobufferyrollian on 12.08.17.
 */

public class OwnDataMemo {
    DateiMemo dateiMemo;

    public long uid;
    public boolean checked;
    public int fileId;



//    public OwnDataMemo(long uid, boolean checked, int fileId){
//        this.uid = uid;
//        this.checked = checked;
//        this.fileId = fileId;
//    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        String output = uid + " -- " + fileId;
        return output;
    }
}
