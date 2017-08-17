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
import com.example.eridhobufferyrollian.beispielsql.model.NeighborMemo;

import java.util.ArrayList;
import java.util.List;

public class NeighborDbSource {
    private static final String LOG_TAG = NeighborDbSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DateiMemoDbHelper dbHelper;
    private DateiMemoDbSource dateiMemoDbSource;

    //neue Array String für Neighbor
    private String[] columns_Neighbor = {
            DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX,
            DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY,
            DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX,
            DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY,
            DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX,
            DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY,
            DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX,
            DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTY,
            DateiMemoDbHelper.COLUMN_PUNKTX,
            DateiMemoDbHelper.COLUMN_PUNKTY,
            DateiMemoDbHelper.COLUMN_UIP,
            DateiMemoDbHelper.COLUMN_RTT,
            DateiMemoDbHelper.COLUMN_UID,
            DateiMemoDbHelper.COLUMN_CHECKED
    };

    public NeighborDbSource(Context context) {
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
    //    private double cornerTopRightX;
    //    private double cornerTopRightY;
    //    private double cornerTopLeftX;
    //    private double cornerTopLeftY;
    //    private double cornerBottomRightX;
    //    private double cornerBottomRightY;
    //    private double cornerBottomLeftX;
    //    private double cornerBottomLeftY;
    //    private double punktX;
    //    private double punktY;
    //    private String UIP;
    //    private double RTT;
    //    private boolean checked;
    //    private long uid;
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
    public NeighborMemo createNeighborMemo(NeighborMemo neighborMemo) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_UID, dateiMemoDbSource.getUid());
        values.put(DateiMemoDbHelper.COLUMN_CHECKED, neighborMemo.isChecked());
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX, neighborMemo.getCornerTopLeftX());
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY, neighborMemo.getCornerTopLeftY());
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX, neighborMemo.getCornerTopRightX());
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY, neighborMemo.getCornerTopRightY());
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX, neighborMemo.getCornerBottomLeftX());
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY, neighborMemo.getCornerBottomLeftY());
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX, neighborMemo.getCornerBottomRightX());
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX, neighborMemo.getCornerBottomRightY());
        values.put(DateiMemoDbHelper.COLUMN_PUNKTX, neighborMemo.getPunktX());
        values.put(DateiMemoDbHelper.COLUMN_PUNKTY, neighborMemo.getPunktY());
        values.put(DateiMemoDbHelper.COLUMN_UIP, neighborMemo.getUIP());
        values.put(DateiMemoDbHelper.COLUMN_RTT, neighborMemo.getRTT());



        //
        //insert row
        //insert muss long
        //
        long neighbor_Id = database.insert(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST, null, values);

        //
        //neighbor_Id
        //insert data in Array
        //
        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                columns_Neighbor, DateiMemoDbHelper.COLUMN_UID + "=" + neighbor_Id ,
                null, null, null, null);

        cursor.moveToFirst();
        neighborMemo = cursorToNeighborMemo(cursor);
        cursor.close();

        return neighborMemo;
    }

    /*
  *
  *
  *                                           Delete data
  *
  *
  *
  * */
    public void deleteNeighbormemo(NeighborMemo neighborMemo) {
        long id = neighborMemo.getUid();

        database.delete(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                DateiMemoDbHelper.COLUMN_UID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + neighborMemo.toString());
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
    /*
    *
    *               Update Corner Top Right X und Y
    *
    *
    *
    *
    *
    * */
    public double updateCornerTopRightXNeighbor(double newCornerTopRightX) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX, newCornerTopRightX);

        database.update(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX + "=" + newCornerTopRightX,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                columns_Neighbor, DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX + "=" + newCornerTopRightX,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerTopRightX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX));
        cursor.close();

        return cornerTopRightX;
    }

    public double updateCornerTopRightY(double newCornerTopRightY) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY, newCornerTopRightY);

        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY + "=" + newCornerTopRightY,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY + "=" + newCornerTopRightY,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerTopRightY = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY));
        cursor.close();

        return cornerTopRightY;
    }
    //
    // ================================================================================================================================
    //


    /*
    *               Update Corner Top Left X und Y
    *
    *
    *
    *
    *
    * */
    public double updateCornerTopLeftX(double newCornerTopLeftX) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX, newCornerTopLeftX);

        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX + "=" + newCornerTopLeftX,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX + "=" + newCornerTopLeftX,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerTopLeftX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX));
        cursor.close();

        return cornerTopLeftX;
    }

    public double updateCornerTopLeftY(double newCornerTopLeftY) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY, newCornerTopLeftY);

        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY + "=" + newCornerTopLeftY,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY + "=" + newCornerTopLeftY,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerTopLeftY = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY));
        cursor.close();

        return cornerTopLeftY;
    }
    //
    // ================================================================================================================================
    //

    /*
   *               Update Corner Bottom Right X und Y
   *
   *
   *
   *
   *
   *
   * */
    public double updateCornerBottomRightX(double newCornerBottomRightX) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX, newCornerBottomRightX);

        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX + "=" + newCornerBottomRightX,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX + "=" + newCornerBottomRightX,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerBottomRightX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX));
        cursor.close();

        return cornerBottomRightX;
    }

    public double updateCornerBottomRightY(double newCornerBottomRightY) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTY, newCornerBottomRightY);

        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTY + "=" + newCornerBottomRightY,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTY + "=" + newCornerBottomRightY,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerBottomRightY = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTY));
        cursor.close();

        return cornerBottomRightY;
    }
    //
    // ================================================================================================================================
    //

    /*
  *               Update Corner Bottom Left X und Y
  *
  *
  *
  *
  *
  *
  * */
    public double updateCornerBottomLeftX(double newCornerBottomLeftX) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX, newCornerBottomLeftX);

        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX + "=" + newCornerBottomLeftX,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX + "=" + newCornerBottomLeftX,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerBottomLeftX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX));
        cursor.close();

        return cornerBottomLeftX;
    }

    public double updateCornerBottomLeftY(double newCornerBottomLeftY) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY, newCornerBottomLeftY);

        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY + "=" + newCornerBottomLeftY,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY + "=" + newCornerBottomLeftY,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerBottomLeftY = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY));
        cursor.close();

        return cornerBottomLeftY;
    }
    /*
    *  ================================================================================================================================
    */
}
