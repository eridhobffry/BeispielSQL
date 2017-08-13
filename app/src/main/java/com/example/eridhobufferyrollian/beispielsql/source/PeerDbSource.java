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


}
