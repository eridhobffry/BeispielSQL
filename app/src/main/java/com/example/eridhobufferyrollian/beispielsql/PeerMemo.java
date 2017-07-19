package com.example.eridhobufferyrollian.beispielsql;

/**
 * Created by Annisa on 7/18/2017.
 */

public class PeerMemo {

    private long uip;
    private long peerId;
    private boolean checked;

    public PeerMemo(long uip, long peerId, boolean checked) {
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

    public long gePeerId() {
        return peerId;
    }

    public void setPeerId(long peerId) {
        this.peerId = peerId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked (boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        String output = uip + " -- " + peerId;
        return output;
    }
}
