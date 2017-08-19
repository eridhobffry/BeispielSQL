package com.example.eridhobufferyrollian.beispielsql.source;

/**
 * Created by en on 13.08.17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import android.content.ContentValues;
import android.database.Cursor;

import com.example.eridhobufferyrollian.beispielsql.DateiMemoDbHelper;
import com.example.eridhobufferyrollian.beispielsql.model.DateiMemo;
import com.example.eridhobufferyrollian.beispielsql.model.OwnDataMemo;
import com.example.eridhobufferyrollian.beispielsql.model.PeerMemo;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

public class PeerDbSource {
    private static final String LOG_TAG = PeerDbSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DateiMemoDbHelper dbHelper;
    private DateiMemoDbSource dateiMemoDbSource;



    //neue Array String für Peer
    private String[] columns_Peer = {
            DateiMemoDbHelper.COLUMN_PEERID,
            DateiMemoDbHelper.COLUMN_PEERIP,
            DateiMemoDbHelper.COLUMN_UID,
            DateiMemoDbHelper.COLUMN_CHECKED
    };

    public PeerDbSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new DateiMemoDbHelper(context);
    }

    //mit getWritableDatabase öffnet man die Verbindung DB
    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }


    /*
    *
    *
    *           Converting List to Double -- List to Integer -- List to Long
    *
    * */
    public double listToDouble(List<Double> list){
        double[] tmp = new double[list.size()];
        double ret = 0;

        for (int i = 0; i < list.size(); ++i) { //iterate over the elements of the list
            tmp[i] = Double.valueOf(list.get(i));
        }
        for (int j = 0; j < tmp.length; ++j) {
            ret = tmp[j];
        }

        return ret;
     }

    public int listToInt(List<Integer> list){
        int[] tmp = new int[list.size()];
        int ret = 0;

        for (int i = 0; i < list.size(); ++i) { //iterate over the elements of the list
            tmp[i] = Integer.valueOf(list.get(i));
        }
        for (int j = 0; j < tmp.length; ++j) {
            ret = tmp[j];
        }

        return ret;
    }

    public long listToLong(List<Long> list){
        long[] tmp = new long[list.size()];
        long ret = 0;

        for (int i = 0; i < list.size(); ++i) { //iterate over the elements of the list
            tmp[i] = Long.valueOf(list.get(i));
        }
        for (int j = 0; j < tmp.length; ++j) {
            ret = tmp[j];
        }

        return ret;
    }


    //
    //==================================================================================================================
    //


    //
    //    private long uid;
    //    public int peerId;
    //    public double peerIp;
    //    private boolean checked;
    //
    //----------------------------- Insert, delete, update, get values in Table ---------------------------------
    //
    //
    /*
    *
    *                                             Insert Data
    *
    *
    * */
    public PeerMemo createPeerMemo(PeerMemo peerMemo) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_PEERID, peerMemo.getPeerId());
        values.put(DateiMemoDbHelper.COLUMN_PEERIP, peerMemo.getPeerIp());
        values.put(DateiMemoDbHelper.COLUMN_UID, dateiMemoDbSource.getUid());
        values.put(DateiMemoDbHelper.COLUMN_CHECKED, peerMemo.isChecked());

        //
        //insert row
        //
        long data_Id = database.insert(DateiMemoDbHelper.TABLE_PEER_LIST, null, values);

        //
        //dataId
        //insert data in Array
        //
        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns_Peer, DateiMemoDbHelper.COLUMN_UID + "=" + data_Id ,
                null, null, null, null);

        cursor.moveToFirst();
        peerMemo = cursorToPeerMemo(cursor);
        cursor.close();

        return peerMemo;
    }

    /*
    *
    *
    *                                           Delete data
    *
    *
    *
    * */
    public void deletePeerMemo(PeerMemo peerMemo) {
        long id = peerMemo.getUid();

        database.delete(DateiMemoDbHelper.TABLE_PEER_LIST,
                DateiMemoDbHelper.COLUMN_UID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + peerMemo.toString());
    }
    /*
    *
    * ==================================================================================================================
    * */


     /*
    *  ----------------------------------              update data        ----------------------------------------------------------------
    *
    *
    *
    *
    * */

    public PeerMemo updatePeerMemo(long newUid, int newPeerId, int newPeerIp, boolean newChecked) {
        int intValueChecked = (newChecked)? 1 : 0;
        newUid = dateiMemoDbSource.getUid();
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_UID, newUid);
        values.put(DateiMemoDbHelper.COLUMN_PEERID, newPeerId);
        values.put(DateiMemoDbHelper.COLUMN_PEERIP, newPeerIp);
        values.put(DateiMemoDbHelper.COLUMN_CHECKED, intValueChecked);


        database.update(DateiMemoDbHelper.TABLE_PEER_LIST,
                values,
                DateiMemoDbHelper.COLUMN_UID + "=" + newUid,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_PEER_LIST,
                columns_Peer, DateiMemoDbHelper.COLUMN_UID + "=" + newUid,
                null, null, null, null);

        cursor.moveToFirst();
        PeerMemo peerMemo = cursorToPeerMemo(cursor);
        cursor.close();

        return peerMemo;
    }


    /*
    *
    *
    *                   Peerscount -- Increase -- Decrease
    *
    *
    * */
    public int getPeersCount() {
        String countQuery = "SELECT  * FROM " + DateiMemoDbHelper.TABLE_PEER_LIST;
        Cursor cursor = database.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }


    public int decreaseCountPeers (PeerMemo peerMemo) {
        if (getPeersCount() == 0){
            System.out.println("No more Peers");
        }
        deletePeerMemo(peerMemo);
        return getPeersCount();
    }

    public int increaseCountPeers (PeerMemo peerMemo) {
        if (getPeersCount() == 2){
            System.out.println("Prepare to split");
        }
        createPeerMemo(peerMemo);
        return getPeersCount();
    }
    //
    //===============================================================================================
    //


    private PeerMemo cursorToPeerMemo(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_UID);
        int idPeerId = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PEERID);
        int idPeerIp = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PEERIP);
        int idChecked = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CHECKED);

        long uid = cursor.getLong(idIndex);

        int intValueChecked = cursor.getInt(idChecked);
        boolean isChecked = (intValueChecked != 0);

        int peerId = cursor.getInt(idPeerId);
        String peerIp = cursor.getString(idPeerIp);


        PeerMemo peerMemo = new PeerMemo(uid, peerId, peerIp, isChecked);

        return peerMemo;
    }

    /*
    *           Get
    *
    *
    *           All Data
    *
    *
    *
    * */
    public List<PeerMemo> getAllPeerMemos() {
        List<PeerMemo> PeerMemoList = new ArrayList<>();

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns_Peer, null, null, null, null, null);

        cursor.moveToFirst();
        PeerMemo peerMemo;

        while(!cursor.isAfterLast()) {
            peerMemo = cursorToPeerMemo(cursor);
            PeerMemoList.add(peerMemo);
            Log.d(LOG_TAG, "ID: " + peerMemo.getUid() + ", Inhalt: " + peerMemo.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return PeerMemoList;
    }

    /*
    *
    *           Get UID
    *
    * */
    public double getUidPeer() {
        return dateiMemoDbSource.getUid();
    }


    /*
    *
    *
    *           Get Peer Ip
    *
    * */
    public List<Integer> getPeerIp(long peerId) {
        List<Integer> PeerIdList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_PEERIP + " FROM " + DateiMemoDbHelper.TABLE_PEER_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_PEERID + " = " + peerId;;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        int peerIp;

        while(!cursor.isAfterLast()) {
            peerIp = cursor.getInt(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PEERIP));
            PeerIdList.add(peerIp);
            Log.d(LOG_TAG, selectQuery);
            cursor.moveToNext();
        }

        cursor.close();

        return PeerIdList;
    }

    public List<PeerMemo> getAllPeer() {
        List<PeerMemo> PeerMemoList = new ArrayList<>();

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_PEER_LIST,
                columns_Peer, null, null, null, null, null);

        cursor.moveToFirst();
        PeerMemo peerMemo;

        while(!cursor.isAfterLast()) {
            peerMemo = cursorToPeerMemo(cursor);
            PeerMemoList.add(peerMemo);
            Log.d(LOG_TAG, "ID: " + peerMemo.getUid() + ", Inhalt: " + peerMemo.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return PeerMemoList;
    }

}




    /*
    *
    *
    *                               Get einzelne Data
    *
    *
    *
    * */
//    public PeerMemo getDataEinzelneRow(long peerId) {
//
//
//
//        String selectQuery = "SELECT  * FROM " + DateiMemoDbHelper.TABLE_PEER_LIST + " WHERE "
//                + DateiMemoDbHelper.COLUMN_PEERID + " = " + peerId;
//
//        Log.e(LOG_TAG, selectQuery);
//
//        Cursor c = database.rawQuery(selectQuery, null);
//        int idChecked = c.getColumnIndex(DateiMemoDbHelper.COLUMN_CHECKED);
//        int intValueChecked = c.getInt(idChecked);
//        boolean isChecked = (intValueChecked > 0);
//        if (c != null)
//            c.moveToFirst();
//
//        PeerMemo peerMemo = new PeerMemo();
//        peerMemo.setUid(c.getLong(c.getColumnIndex(DateiMemoDbHelper.COLUMN_UID)));
//        peerMemo.setPeerId(c.getInt(c.getColumnIndex(DateiMemoDbHelper.COLUMN_PEERID)));
//        peerMemo.setPeerIp(c.getInt(c.getColumnIndex(DateiMemoDbHelper.COLUMN_PEERIP)));
//        peerMemo.setChecked(isChecked);
//
//
//        c.close();
//
//        return peerMemo;
//    }
