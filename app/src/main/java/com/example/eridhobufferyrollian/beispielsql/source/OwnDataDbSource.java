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
import com.example.eridhobufferyrollian.beispielsql.model.ForeignData;
import com.example.eridhobufferyrollian.beispielsql.model.NeighborMemo;
import com.example.eridhobufferyrollian.beispielsql.model.OwnDataMemo;

import java.util.ArrayList;
import java.util.List;

public class OwnDataDbSource {
    private static final String LOG_TAG = OwnDataDbSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DateiMemoDbHelper dbHelper;
    private DateiMemoDbSource dateiMemoDbSource;

    //Array
    private String[] columns_OwnData = {
            DateiMemoDbHelper.COLUMN_FILEID,
            DateiMemoDbHelper.COLUMN_UID,
            DateiMemoDbHelper.COLUMN_CHECKED
    };

    public OwnDataDbSource(Context context) {
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
    //    public long uid;
    //    public boolean checked;
    //    public int fileId
    //----------------------------- Insert, delete, update, get values in Table ---------------------------------
    //
    //
    /*
    *
    *                                             Insert Data
    *
    *
    * */
    public OwnDataMemo createOwnData(OwnDataMemo ownDataMemo) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_UID, dateiMemoDbSource.getUid());
        values.put(DateiMemoDbHelper.COLUMN_CHECKED, ownDataMemo.isChecked());
        values.put(DateiMemoDbHelper.COLUMN_FOTOID, ownDataMemo.getFileId());

        //
        //insert row
        //insert muss long
        //
        long ownData_Id = database.insert(DateiMemoDbHelper.TABLE_OWNDATA_LIST, null, values);

        //
        //Own Data ID
        //insert data in Array
        //
        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_OWNDATA_LIST,
                columns_OwnData, DateiMemoDbHelper.COLUMN_UID + "=" + ownData_Id ,
                null, null, null, null);

        cursor.moveToFirst();
        ownDataMemo = cursorToOwnData(cursor);
        cursor.close();

        return ownDataMemo;
    }

    /*
*
*
*                                           Delete data
*
*
*
* */
    public void deleteOwnData(OwnDataMemo ownDataMemo) {
        long id = ownDataMemo.getUid();

        database.delete(DateiMemoDbHelper.TABLE_OWNDATA_LIST,
                DateiMemoDbHelper.COLUMN_UID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + ownDataMemo.toString());
    }
    /*
    *
    * ==================================================================================================================
    * */

    /*
*
*
*               Hilfklasse für Update Methode und Insert Methode
*
* */
    private OwnDataMemo cursorToOwnData(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_UID);
        int idChecked = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CHECKED);
        int idFileId = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_FILEID);



        long uid = cursor.getLong(idIndex);

        int intValueChecked = cursor.getInt(idChecked);
        boolean isChecked = (intValueChecked != 0);

        int fileId = cursor.getInt(idFileId);



        OwnDataMemo ownDataMemo = new OwnDataMemo(uid, isChecked, fileId);

        return ownDataMemo;
    }

    /*
   *
   *
   *                   get Foto ID
   *
   *
   * */
    public int getFileId(long uid) {
        //List<long> UidList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_FILEID + " FROM " + DateiMemoDbHelper.TABLE_OWNDATA_LIST+ " WHERE "
                + DateiMemoDbHelper.COLUMN_UID + " = " + uid;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        int fileId;
        fileId = cursor.getInt(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_FILEID));

        cursor.close();

        return fileId;
    }
    //
    // ================================================================================================================================
    //

    /*
    *
    *           Get UID
    *
    * */
    public double getUidOwn() {
        return dateiMemoDbSource.getUid();
    }

    public List<OwnDataMemo> getAllOwnData() {
        List<OwnDataMemo> OwnDataMemoList = new ArrayList<>();

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_OWNDATA_LIST,
                columns_OwnData, null, null, null, null, null);

        cursor.moveToFirst();
        OwnDataMemo ownDataMemo;

        while(!cursor.isAfterLast()) {
            ownDataMemo = cursorToOwnData(cursor);
            OwnDataMemoList.add(ownDataMemo);
            Log.d(LOG_TAG, "ID: " + ownDataMemo.getUid() + ", Inhalt: " + ownDataMemo.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return OwnDataMemoList;
    }
}
