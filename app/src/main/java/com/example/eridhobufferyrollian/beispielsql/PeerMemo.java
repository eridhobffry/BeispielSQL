package com.example.eridhobufferyrollian.beispielsql;



public class PeerMemo {

    private long uip;
    public int peerId;
    private boolean checked;



    public PeerMemo(long uip, int peerId, boolean checked) {
        this.uip = uip;
        this.peerId = peerId;
        this.checked = checked;
    }

    public long getUip() {
        return uip;
    }

    public void setUip(long uip) {
        this.uip = uip;
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
        String output = uip + " -- " + peerId;
        return output;
    }
}
