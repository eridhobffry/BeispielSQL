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
            DateiMemoDbHelper.COLUMN_PASSWORD
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

    public DateiMemo createDateiMemo(String username, String password, long nid) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_USERNAME, username);
        values.put(DateiMemoDbHelper.COLUMN_PASSWORD, password);
        //values.put(DateiMemoDbHelper.COLUMN_NID, nid);

        long insertId = database.insert(DateiMemoDbHelper.TABLE_DATEI_LIST, null, values);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_NID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        DateiMemo DateiMemo = cursorToDateiMemo(cursor);
        cursor.close();

        return DateiMemo;
    }

    private DateiMemo cursorToDateiMemo(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_NID);
        int idUsername = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_USERNAME);
        int idPassword = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PASSWORD);

        String username = cursor.getString(idUsername);
        String password = cursor.getString(idPassword);
        long nid = cursor.getLong(idIndex);

        DateiMemo DateiMemo = new DateiMemo(username, password, nid);

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