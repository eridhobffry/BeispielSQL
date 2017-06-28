package com.example.eridhobufferyrollian.beispielsql;

/**
 * Created by en on 15.06.17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;


public class DateiMemoDbSource {

    private static final String LOG_TAG = DateiMemoDbSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DateiMemoDbHelper dbHelper;

    //neue Aray String
    private String[] columns = {
            DateiMemoDbHelper.COLUMN_NID,
            DateiMemoDbHelper.COLUMN_USERNAME,
            DateiMemoDbHelper.COLUMN_PASSWORD,
            DateiMemoDbHelper.COLUMN_PEERID,
            DateiMemoDbHelper.COLUMN_NEIGHID
    };


    public DateiMemoDbSource(Context context) {
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


    //Es muss noch zusätzlich getPeerId(), getEckPunkt(), getNachbarId(), getIP()
    public DateiMemo createDateiMemo(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_USERNAME, username);
        values.put(DateiMemoDbHelper.COLUMN_PASSWORD, password);
        //values.put(DateiMemoDbHelper.COLUMN_NID, nid);


        //Erstmal gibt man die Velues ein, dann würde es die ID kriegen
        //insert Peer Id und insertNeighId muss noch überlegt werden, da die von der NodeList gekriegt wird
        long insertId = database.insert(DateiMemoDbHelper.TABLE_DATEI_LIST, null, values);
//        long insertPeerId = database.insert(DateiMemoDbHelper.TABLE_DATEI_LIST, null, values);
//        long insertNeighId = database.insert(DateiMemoDbHelper.TABLE_DATEI_LIST, null, values);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_NID + "=" + insertId ,
                null, null, null, null);

        cursor.moveToFirst();
        DateiMemo DateiMemo = cursorToDateiMemo(cursor);
        cursor.close();

        return DateiMemo;
    }

    public void deleteDateiMemo(DateiMemo dateiMemo) {
        long id = dateiMemo.getNid();

        database.delete(DateiMemoDbHelper.TABLE_DATEI_LIST,
                DateiMemoDbHelper.COLUMN_NID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + dateiMemo.toString());
    }

    //Wir muessen noch ueberlegen, wie machen wir die Update-Methode fur die PeerID, NachbarID und Eckpunt
    public DateiMemo updateDateiMemo(long id, String newName, String newPassword) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_USERNAME, newName);
        values.put(DateiMemoDbHelper.COLUMN_PASSWORD, newPassword);

        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_NID + "=" + id,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_NID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        DateiMemo dateiMemo = cursorToDateiMemo(cursor);
        cursor.close();

        return dateiMemo;
    }

    private DateiMemo cursorToDateiMemo(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_NID);
        int idUsername = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_USERNAME);
        int idPassword = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PASSWORD);
        int idPeer = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PEERID);
        int idNeigh = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_NEIGHID);

        String username = cursor.getString(idUsername);
        String password = cursor.getString(idPassword);
        long nid = cursor.getLong(idIndex);
        long peerId = cursor.getLong(idPeer);
        long neighId = cursor.getLong(idNeigh);

        DateiMemo DateiMemo = new DateiMemo(username, password, nid, peerId, neighId);

        return DateiMemo;
    }

    public List<DateiMemo> getAllDateiMemos() {
        List<DateiMemo> DateiMemoList = new ArrayList<>();

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        DateiMemo DateiMemo;

        while(!cursor.isAfterLast()) {
            DateiMemo = cursorToDateiMemo(cursor);
            DateiMemoList.add(DateiMemo);
            Log.d(LOG_TAG, "ID: " + DateiMemo.getNid() + ", Inhalt: " + DateiMemo.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return DateiMemoList;
    }
}