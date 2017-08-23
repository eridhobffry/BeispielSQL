package com.example.eridhobufferyrollian.beispielsql.source;

/**
 * Created by en on 13.08.17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import android.content.ContentValues;
import android.database.Cursor;

import com.example.eridhobufferyrollian.beispielsql.DatabaseManager;
import com.example.eridhobufferyrollian.beispielsql.DateiMemoDbHelper;
import com.example.eridhobufferyrollian.beispielsql.model.DateiMemo;
import com.example.eridhobufferyrollian.beispielsql.model.NeighborMemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NeighborDbSource {
    private static final String LOG_TAG = NeighborDbSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DateiMemoDbHelper dbHelper;
    private DateiMemoDbSource dateiMemoDbSource;
    private NeighborMemo neighborMemo;

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
            //DateiMemoDbHelper.COLUMN_CHECKED
    };

    public NeighborDbSource(){
        neighborMemo = new NeighborMemo();
    }

      /*
    *
    * For single table
    *
    * */

//    public NeighborDbSource(Context context) {
//        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
//        dbHelper = new DateiMemoDbHelper(context);
//    }
//
//    //mit getWritableDatabase öffnet man die Verbindung DB
//    public void open() {
//        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
//        database = dbHelper.getWritableDatabase();
//        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
//    }
//
//    public void close() {
//        dbHelper.close();
//        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
//    }
//
//==================================================================================================================
//


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
    public int createNeighborMemo(NeighborMemo neighborMemo) {
        database = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_NID, neighborMemo.getUid());
        //values.put(DateiMemoDbHelper.COLUMN_CHECKED, neighborMemo.isChecked());
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
        int neighbor_Id = (int) database.insert(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST, null, values);
        DatabaseManager.getInstance().closeDatabase();

        //
        //neighbor_Id
        //insert data in Array
        //
//        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
//                columns_Neighbor, DateiMemoDbHelper.COLUMN_UID + "=" + neighbor_Id ,
//                null, null, null, null);
//
//        cursor.moveToFirst();
//        neighborMemo = cursorToNeighborMemo(cursor);
//        cursor.close();

        return neighbor_Id;
    }

    /*
  *
  *
  *                                           Delete data
  *
  *
  *
  * */
    public void deleteNeighbormemo() {
        database = DatabaseManager.getInstance().openDatabase();
        database.delete(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST, null, null);
        DatabaseManager.getInstance().closeDatabase();
        //Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + neighborMemo.getUid() + " Inhalt: " + neighborMemo.toString());
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

    public double updateCornerTopRightYNeighbor(double newCornerTopRightY) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY, newCornerTopRightY);

        database.update(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY + "=" + newCornerTopRightY,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                columns_Neighbor, DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY + "=" + newCornerTopRightY,
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
    public double updateCornerTopLeftXNeighbor(double newCornerTopLeftX) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX, newCornerTopLeftX);

        database.update(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX + "=" + newCornerTopLeftX,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                columns_Neighbor, DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX + "=" + newCornerTopLeftX,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerTopLeftX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX));
        cursor.close();

        return cornerTopLeftX;
    }

    public double updateCornerTopLeftYNeighbor(double newCornerTopLeftY) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY, newCornerTopLeftY);

        database.update(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY + "=" + newCornerTopLeftY,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                columns_Neighbor, DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY + "=" + newCornerTopLeftY,
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
    public double updateCornerBottomRightXNeighbor(double newCornerBottomRightX) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX, newCornerBottomRightX);

        database.update(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX + "=" + newCornerBottomRightX,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                columns_Neighbor, DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX + "=" + newCornerBottomRightX,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerBottomRightX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX));
        cursor.close();

        return cornerBottomRightX;
    }

    public double updateCornerBottomRightYNeighbor(double newCornerBottomRightY) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTY, newCornerBottomRightY);

        database.update(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTY + "=" + newCornerBottomRightY,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                columns_Neighbor, DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTY + "=" + newCornerBottomRightY,
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
    public double updateCornerBottomLeftXNeighbor(double newCornerBottomLeftX) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX, newCornerBottomLeftX);

        database.update(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX + "=" + newCornerBottomLeftX,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                columns_Neighbor, DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX + "=" + newCornerBottomLeftX,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerBottomLeftX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX));
        cursor.close();

        return cornerBottomLeftX;
    }

    public double updateCornerBottomLeftYNeighbor(double newCornerBottomLeftY) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY, newCornerBottomLeftY);

        database.update(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY + "=" + newCornerBottomLeftY,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                columns_Neighbor, DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY + "=" + newCornerBottomLeftY,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerBottomLeftY = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY));
        cursor.close();

        return cornerBottomLeftY;
    }
    /*
    *  ================================================================================================================================
    */

    /*
    *               Update Round Trip Time
    *
    *
    *
    *
    *
    *
    * */
        public double udpateRTT(double newRTT) {
            ContentValues values = new ContentValues();
            values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX, newRTT);

            database.update(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                    values,
                    DateiMemoDbHelper.COLUMN_RTT + "=" + newRTT,
                    null);

            Cursor cursor = database.query(DateiMemoDbHelper.TABLE_NEIGHBOR_LIST,
                    columns_Neighbor, DateiMemoDbHelper.COLUMN_RTT + "=" + newRTT,
                    null, null, null, null);

            cursor.moveToFirst();
            double RTT = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_RTT));
            cursor.close();

            return RTT;
        }
    /*
    *  ================================================================================================================================
    */

    /*
    *
    *
    *               Hilfklasse für Update Methode und Insert Methode
    *
    * */
//    private NeighborMemo cursorToNeighborMemo(Cursor cursor) {
//        int idIndex = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_UID);
//        int idChecked = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CHECKED);
//        int idTopRightX = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX);
//        int idTopRightY = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY);
//        int idTopLeftX = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX);
//        int idTopLeftY = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY);
//        int idBottomRightX = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX);
//        int idBottomRightY = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTY);
//        int idBottomLeftX = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY);
//        int idBottomLeftY = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY);
//        int idPunktX = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PUNKTX);
//        int idPunktY = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PUNKTY);
//        int idUIP = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_UIP);
//        int idRtt = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_RTT);
//
//
//
//        long uid = cursor.getLong(idIndex);
//
//        int intValueChecked = cursor.getInt(idChecked);
//        boolean isChecked = (intValueChecked != 0);
//
//        double cornerTopRightX = cursor.getDouble(idTopRightX);
//        double cornerTopRightY = cursor.getDouble(idTopRightY);
//        double cornerTopLeftX = cursor.getDouble(idTopLeftX);
//        double cornerTopLeftY = cursor.getDouble(idTopLeftY);
//        double cornerBottomRightX = cursor.getDouble(idBottomRightX);
//        double cornerBottomRightY = cursor.getDouble(idBottomRightY);
//        double cornerBottomLeftX= cursor.getDouble(idBottomLeftX);
//        double cornerBottomLeftY = cursor.getDouble(idBottomLeftY);
//        double punktX = cursor.getDouble(idPunktX);
//        double punktY = cursor.getDouble(idPunktY);
//        String UIP = cursor.getString(idUIP);
//        double RTT = cursor.getDouble(idRtt);
//
//
//        NeighborMemo neighborMemo = new NeighborMemo(uid, isChecked,
//                cornerTopRightX, cornerTopRightY, cornerTopLeftX, cornerTopLeftY,
//                cornerBottomRightX, cornerBottomRightY, cornerBottomLeftX, cornerBottomLeftY,
//                punktX, punktY, UIP, RTT);
//
//        return neighborMemo;
//    }

    /*
    *           Get
    *
    *
    *           Corner Bottom Right X und Y
    *
    *
    *
    * */
    public double getCornerBottomRightXNeighbor(int index) {
        database = DatabaseManager.getInstance().openDatabase();
        //List<Double> CornerBottomRightList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX +" FROM " + DateiMemoDbHelper.TABLE_NEIGHBOR_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_NID + " = " + index;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        double cornerBottomRightX;
        cornerBottomRightX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX));



//        while(!cursor.isAfterLast()) {
//            CornerBottomRightX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX));
//            //CornerBottomRightList.add(CornerBottomRight);
//            Log.d(LOG_TAG, selectQuery);
//            cursor.moveToNext();
//        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase();

        return cornerBottomRightX;
    }

    public double getCornerBottomRightYNeighbor(int index) {
        database = DatabaseManager.getInstance().openDatabase();
        //List<Double> CornerBottomRightList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTY +" FROM " + DateiMemoDbHelper.TABLE_NEIGHBOR_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_NID + " = " + index;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        double cornerBottomRightY;
        cornerBottomRightY = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTY));

//        while(!cursor.isAfterLast()) {
//            CornerBottomRightX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX));
//            //CornerBottomRightList.add(CornerBottomRight);
//            Log.d(LOG_TAG, selectQuery);
//            cursor.moveToNext();
//        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase();

        return cornerBottomRightY;
    }
    //
    // ================================================================================================================================
    //


    /*
    *           Get
    *
    *
    *           Corner Bottom Left X und Y
    *
    *
    *
    * */
    public double getCornerBottomLeftXNeighbor(int index) {
        database = DatabaseManager.getInstance().openDatabase();
        //List<Double> CornerBottomLeftList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX +" FROM " + DateiMemoDbHelper.TABLE_NEIGHBOR_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_NID + " = " + index;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        double cornerBottomLeftX;
        cornerBottomLeftX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX));

//        while(!cursor.isAfterLast()) {
//            CornerBottomLeftX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX));
//            //CornerBottomLeftList.add(CornerBottomLeft);
//            Log.d(LOG_TAG, selectQuery);
//            cursor.moveToNext();
//        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase();

        return cornerBottomLeftX;
    }

    public double getCornerBottomLeftYNeighbor(int index) {
        database = DatabaseManager.getInstance().openDatabase();
        //List<Double> CornerBottomLeftList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY +" FROM " + DateiMemoDbHelper.TABLE_NEIGHBOR_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_NID + " = " + index;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        double cornerBottomLeftY;
        cornerBottomLeftY = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY));

//        while(!cursor.isAfterLast()) {
//            CornerBottomLeftX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX));
//            //CornerBottomLeftList.add(CornerBottomLeft);
//            Log.d(LOG_TAG, selectQuery);
//            cursor.moveToNext();
//        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase();

        return cornerBottomLeftY;
    }
    //
    // ================================================================================================================================
    //


    /*
    *           Get
    *
    *
    *           Corner Top Right X und Y
    *
    *
    * */
    public double getCornerTopRightXNeighbor(int index) {
        database = DatabaseManager.getInstance().openDatabase();
        //List<Double> CornerTopRightList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX +" FROM " + DateiMemoDbHelper.TABLE_NEIGHBOR_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_NID + " = " + index;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        double cornerTopRightX;
        cornerTopRightX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX));


//        while(!cursor.isAfterLast()) {
//            CornerTopRightX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX));
//            //CornerTopRightList.add(CornerTopRight);
//            Log.d(LOG_TAG, selectQuery);
//            cursor.moveToNext();
//        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase();

        return cornerTopRightX;
    }

    public double getCornerTopRightYNeighbor(int index) {
        database = DatabaseManager.getInstance().openDatabase();
        //List<Double> CornerTopRightList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY +" FROM " + DateiMemoDbHelper.TABLE_NEIGHBOR_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_NID + " = " + index;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        double cornerTopRightY;
        cornerTopRightY = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY));


//        while(!cursor.isAfterLast()) {
//            CornerTopRightX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX));
//            //CornerTopRightList.add(CornerTopRight);
//            Log.d(LOG_TAG, selectQuery);
//            cursor.moveToNext();
//        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase();

        return cornerTopRightY;
    }

    //
    // ================================================================================================================================
    //


    /*
    *           Get
    *
    *
    *           Corner Top Left X und Y
    *
    *
    * */
    public double getCornerTopLeftXNeighbor(int index) {
        database = DatabaseManager.getInstance().openDatabase();
        //List<Double> CornerTopRightList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX +" FROM " + DateiMemoDbHelper.TABLE_NEIGHBOR_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_NID + " = " + index;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        double cornerTopLeftX;
        cornerTopLeftX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX));


//        while(!cursor.isAfterLast()) {
//            CornerTopRightX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX));
//            //CornerTopRightList.add(CornerTopRight);
//            Log.d(LOG_TAG, selectQuery);
//            cursor.moveToNext();
//        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase();

        return cornerTopLeftX;
    }

    public double getCornerTopLeftYNeighbor(int index) {
        database = DatabaseManager.getInstance().openDatabase();
        //List<Double> CornerTopRightList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY +" FROM " + DateiMemoDbHelper.TABLE_NEIGHBOR_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_NID + " = " + index;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        double cornerTopLeftY;
        cornerTopLeftY = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY));


//        while(!cursor.isAfterLast()) {
//            CornerTopRightX = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX));
//            //CornerTopRightList.add(CornerTopRight);
//            Log.d(LOG_TAG, selectQuery);
//            cursor.moveToNext();
//        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase();

        return cornerTopLeftY;
    }
    //
    // ================================================================================================================================
    //

    /*
    *           Get
    *
    *
    *           Punkt X
    *
    *
    * */
    public double getPunktXNeighbor(int index) {
        database = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_PUNKTX +" FROM " + DateiMemoDbHelper.TABLE_NEIGHBOR_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_UID + " = " + index;

        Log.e(LOG_TAG, selectQuery);

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
        double punktXNeighbor;
        punktXNeighbor = c.getDouble(c.getColumnIndex(DateiMemoDbHelper.COLUMN_PUNKTX));

        c.close();

        DatabaseManager.getInstance().closeDatabase();

        return punktXNeighbor;
    }
    //
    // ================================================================================================================================
    //



    /*
    *           Get
    *
    *
    *           Punkt Y
    *
    *
    * */
    public double getPunktYNeighbor(int index) {
        database = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_PUNKTY +" FROM " + DateiMemoDbHelper.TABLE_NEIGHBOR_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_UID + " = " + index;

        Log.e(LOG_TAG, selectQuery);

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
        double punktYNeighbor;
        punktYNeighbor = c.getDouble(c.getColumnIndex(DateiMemoDbHelper.COLUMN_PUNKTY));

        c.close();

        DatabaseManager.getInstance().closeDatabase();

        return punktYNeighbor;
    }
    //
    // ================================================================================================================================
    //


    /*
    *           Get
    *
    *
    *           IP
    *
    *
    * */
    public String getUip(int index) {
        database = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_IP +" FROM " + DateiMemoDbHelper.TABLE_NEIGHBOR_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_UID + " = " + index;

        Log.e(LOG_TAG, selectQuery);

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
        String Uip;
        Uip = c.getString(c.getColumnIndex(DateiMemoDbHelper.COLUMN_IP));

        c.close();

        DatabaseManager.getInstance().closeDatabase();

        return Uip;
    }
    //
    // ================================================================================================================================
    //

    /*
   *           Get
   *
   *
   *           RTT
   *
   *
   * */
    public double getRTT(int index) {
        database = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_RTT +" FROM " + DateiMemoDbHelper.TABLE_NEIGHBOR_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_UID + " = " + index;

        Log.e(LOG_TAG, selectQuery);

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
        double RTT;
        RTT = c.getDouble(c.getColumnIndex(DateiMemoDbHelper.COLUMN_RTT));

        c.close();

        DatabaseManager.getInstance().closeDatabase();

        return RTT;
    }
    //
    // ================================================================================================================================
    //

    public List<NeighborMemo> getAllNeighborMemo() {
        List<NeighborMemo> NeighborMemoList = new LinkedList<NeighborMemo>();

        //1. query
        String query = "SELECT * FROM " + dbHelper.TABLE_NEIGHBOR_LIST;

        //2. open Database
        database = DatabaseManager.getInstance().openDatabase();

        Cursor cursor = database.rawQuery(query, null);

//        int idChecked = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CHECKED);
//        int intValueChecked = cursor.getInt(idChecked);
//        boolean isChecked = (intValueChecked != 0);


        //3. Durchführen Zeile und füge in List hinzu
        NeighborMemo neighborMemo = null;
        if (cursor.moveToFirst()) {
            do {
                neighborMemo = new NeighborMemo();
                neighborMemo.setUid(cursor.getLong(cursor.getColumnIndex(dbHelper.COLUMN_NID)));
                //neighborMemo.setChecked(isChecked);
                neighborMemo.setCornerTopLeftX(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_CORNERTOPLEFTX)));
                neighborMemo.setCornerTopLeftY(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_CORNERTOPLEFTY)));
                neighborMemo.setCornerTopRightX(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_CORNERTOPRIGHTX)));
                neighborMemo.setCornerTopRightY(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_CORNERTOPRIGHTY)));
                neighborMemo.setCornerBottomLeftX(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_CORNERBOTTOMLEFTX)));
                neighborMemo.setCornerBottomLeftY(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_CORNERBOTTOMLEFTY)));
                neighborMemo.setCornerBottomRightX(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_CORNERBOTTOMRIGHTX)));
                neighborMemo.setCornerBottomRightY(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_CORNERBOTTOMRIGHTY)));
                neighborMemo.setPunktX(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_PUNKTX)));
                neighborMemo.setPunktY(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_PUNKTY)));
                neighborMemo.setUIP(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_IP)));
                neighborMemo.setRTT(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_RTT)));


                // Add book to books
                NeighborMemoList.add(neighborMemo);
            } while (cursor.moveToNext());
        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase();

        return NeighborMemoList;
    }
}
