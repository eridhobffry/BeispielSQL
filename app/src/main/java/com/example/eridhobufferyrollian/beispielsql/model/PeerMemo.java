package com.example.eridhobufferyrollian.beispielsql.model;


public class PeerMemo {
    DateiMemo dateiMemo;

    private long uid;
    private int peerId;
    private String peerIp;
    private boolean checked;



//    public PeerMemo(long uid, int peerId, String peerIp, boolean checked) {
//        this.uid = uid;
//        this.peerId = peerId;
//        this.peerIp = peerIp;
//        this.checked = checked;
//    }

    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
       this.uid = uid;
    }

    public int getPeerId() {
        return peerId;
    }

    public void setPeerId(int peerId) {
        this.peerId = peerId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked (boolean checked) {
        this.checked = checked;
    }


    public String getPeerIp() {
        return peerIp;
    }

    public void setPeerIp(String peerIp) {
        this.peerIp = peerIp;
    }


    public int getLength(int zahl){
        String s = String.valueOf(zahl);
        return s.length();
    }


    public int getPeerCount () {
        int length = getLength(peerId);
        int i = 0;
        while (i<length){
            i = i+1;
        }
        return i;
    }

    public void decreasePeers(){
        peerId = peerId-1;
    }

    public void increasePeers(){
        peerId = peerId+1;
    }



    @Override
    public String toString() {
        String output = uid + " -- " + peerId;
        return output;
    }
}
