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
        values.put(DateiMemoDbHelper.COLUMN_UID, listToInt(dateiMemoDbSource.getUid()));
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
        PeerMemo peerMemo = cursorToDateiMemo(cursor);
        cursor.close();

        return peerMemo;
    }


}
