package com.example.eridhobufferyrollian.beispielsql.source;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import android.content.ContentValues;
import android.database.Cursor;

import com.example.eridhobufferyrollian.beispielsql.DateiMemoDbHelper;
import com.example.eridhobufferyrollian.beispielsql.model.DateiMemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DateiMemoDbSource {

    private static final String LOG_TAG = DateiMemoDbSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DateiMemoDbHelper dbHelper;
    private PeerDbSource peerDbSource;

    //neue Array String für Datei
    private String[] columns = {
            DateiMemoDbHelper.COLUMN_UID, //------------------------ Table Datei
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
            DateiMemoDbHelper.COLUMN_IP,
            DateiMemoDbHelper.COLUMN_COUNTPEERS,
            DateiMemoDbHelper.COLUMN_CHECKED
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
    // String username, String password, long uid, boolean checked,
    // double cornerTopRight, double cornerTopLeft, double cornerBottomRight,
    // double cornerBottomLeft, double punktX, double punktY, double IP, int countPeers
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
    public DateiMemo createDateiMemo(DateiMemo dateiMemo) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_UID, dateiMemo.getUid());
        values.put(DateiMemoDbHelper.COLUMN_CHECKED, dateiMemo.isChecked());
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX, dateiMemo.getCornerTopLeftX());
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY, dateiMemo.getCornerTopLeftY());
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX, dateiMemo.getCornerTopRightX());
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY, dateiMemo.getCornerTopRightY());
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX, dateiMemo.getCornerBottomLeftX());
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY, dateiMemo.getCornerBottomLeftY());
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX, dateiMemo.getCornerBottomRightX());
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX, dateiMemo.getCornerBottomRightY());
        values.put(DateiMemoDbHelper.COLUMN_PUNKTX, dateiMemo.getPunktX());
        values.put(DateiMemoDbHelper.COLUMN_PUNKTY, dateiMemo.getPunktY());
        values.put(DateiMemoDbHelper.COLUMN_IP, dateiMemo.getIP());
        values.put(DateiMemoDbHelper.COLUMN_COUNTPEERS, peerDbSource.getPeersCount());

        //
        //insert row
        //
        long data_Id = database.insert(DateiMemoDbHelper.TABLE_DATEI_LIST, null, values);

        //
        //dataId
        //insert data in Array
        //
        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                        columns, DateiMemoDbHelper.COLUMN_UID + "=" + data_Id ,
                        null, null, null, null);

        cursor.moveToFirst();
        dateiMemo = cursorToDateiMemo(cursor);
        cursor.close();

        return dateiMemo;
    }



    /*
    *
    *
    *                                           Delete data
    *
    *
    *
    * */
    public void deleteDateiMemo(DateiMemo dateiMemo) {
        long id = dateiMemo.getUid();

        database.delete(DateiMemoDbHelper.TABLE_DATEI_LIST,
                DateiMemoDbHelper.COLUMN_UID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + dateiMemo.toString());
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

    public DateiMemo updateDateiMemo(long uid, boolean newChecked,
                                     double newCornerTopRightX, double newCornerTopRightY, double newCornerTopLeftX, double newCornerTopLeftY,
                                     double newCornerBottomRightX, double newCornerBottomRightY, double newCornerBottomLeftX, double newCornerBottomLeftY,
                                     double newPunktX, double newPunktY, double newIP, int newCountPeers) {
        int intValueChecked = (newChecked)? 1 : 0;
        newCountPeers = peerDbSource.getPeersCount();
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CHECKED, intValueChecked);

        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX, newCornerTopLeftX);
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTY, newCornerTopLeftY);
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX, newCornerTopRightX);
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTY, newCornerTopRightY);
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTX, newCornerBottomLeftX);
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFTY, newCornerBottomLeftY);
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX, newCornerBottomRightX);
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHTX, newCornerBottomRightY);
        values.put(DateiMemoDbHelper.COLUMN_PUNKTX, newPunktX);
        values.put(DateiMemoDbHelper.COLUMN_PUNKTY, newPunktY);
        values.put(DateiMemoDbHelper.COLUMN_IP, newIP);
        values.put(DateiMemoDbHelper.COLUMN_COUNTPEERS, newCountPeers);
        values.put(DateiMemoDbHelper.COLUMN_UID, uid);


        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_UID + "=" + uid,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_UID + "=" + uid,
                null, null, null, null);

        cursor.moveToFirst();
        DateiMemo dateiMemo = cursorToDateiMemo(cursor);
        cursor.close();

        return dateiMemo;
    }

    /*
    *
    *               Update Corner Top Right X und Y
    *
    * */
    public double updateCornerTopRightX(double newCornerTopRightX) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX, newCornerTopRightX);

        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX + "=" + newCornerTopRightX,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_CORNERTOPRIGHTX + "=" + newCornerTopRightX,
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
   *
   *               Update Corner Top Left X und Y
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
        values.put(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX, newCornerTopLeftY);

        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX + "=" + newCornerTopLeftY,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX + "=" + newCornerTopLeftY,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerTopLeftY = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPLEFTX));
        cursor.close();

        return cornerTopLeftY;
    }


   public double updateCornerBottomRight(double newCornerBottomRight) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHT, newCornerBottomRight);

        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHT + "=" + newCornerBottomRight,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHT + "=" + newCornerBottomRight,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerBottomRight = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHT));
        cursor.close();

        return cornerBottomRight;
    }

    public double updateCornerBottomLeft(double newCornerBottomLeft) {
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFT, newCornerBottomLeft);

        database.update(DateiMemoDbHelper.TABLE_DATEI_LIST,
                values,
                DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFT + "=" + newCornerBottomLeft,
                null);

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFT + "=" + newCornerBottomLeft,
                null, null, null, null);

        cursor.moveToFirst();
        double cornerBottomLeft = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFT));
        cursor.close();

        return cornerBottomLeft;
    }
    /*
    *  ================================================================================================================================
    */




    private DateiMemo cursorToDateiMemo(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_UID);
        int idUsername = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_USERNAME);
        int idPassword = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PASSWORD);
        int idChecked = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CHECKED);
        int idTopRight = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHT);
        int idTopLeft = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPLEFT);
        int idBottomRight = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHT);
        int idBottomLeft = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFT);
        int idPunktX = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PUNKTX);
        int idPunktY = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PUNKTY);
        int idIP = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_IP);
        int idCountPeers = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_COUNTPEERS);


        String username = cursor.getString(idUsername);
        String password = cursor.getString(idPassword);
        long uid = cursor.getLong(idIndex);

        int intValueChecked = cursor.getInt(idChecked);
        boolean isChecked = (intValueChecked != 0);

        double cornerTopRight = cursor.getDouble(idTopRight);
        double cornerTopLeft = cursor.getDouble(idTopLeft);
        double cornerBottomRight = cursor.getDouble(idBottomRight);
        double cornerBottomLeft = cursor.getDouble(idBottomLeft);
        double punktX = cursor.getDouble(idPunktX);
        double punktY = cursor.getDouble(idPunktY);
        double IP = cursor.getDouble(idIP);

        int countPeers = cursor.getInt(idCountPeers);


        DateiMemo dateiMemo = new DateiMemo(username, password, uid, isChecked, cornerTopRight,
                cornerTopLeft, cornerBottomRight, cornerBottomLeft, punktX, punktY, IP, countPeers);

        return dateiMemo;
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
    public List<DateiMemo> getAllDateiMemos() {
        List<DateiMemo> DateiMemoList = new ArrayList<>();

        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_DATEI_LIST,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        DateiMemo dateiMemo;

        while(!cursor.isAfterLast()) {
            dateiMemo = cursorToDateiMemo(cursor);
            DateiMemoList.add(dateiMemo);
            Log.d(LOG_TAG, "ID: " + dateiMemo.getUid() + ", Inhalt: " + dateiMemo.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return DateiMemoList;
    }


    /*
    *           Get
    *
    *
    *           Corner Bottom Right
    *
    *
    *
    * */
    public List<Double> getCornerBottomRight() {
        List<Double> CornerBottomRightList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHT + " FROM " + DateiMemoDbHelper.TABLE_DATEI_LIST;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        double CornerBottomRight;

        while(!cursor.isAfterLast()) {
            CornerBottomRight = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHT));
            CornerBottomRightList.add(CornerBottomRight);
            Log.d(LOG_TAG, selectQuery);
            cursor.moveToNext();
        }

        cursor.close();

        return CornerBottomRightList;
    }


    /*
    *           Get
    *
    *
    *           Corner Bottom Left
    *
    *
    *
    * */
    public List<Double> getCornerBottomLeft() {
        List<Double> CornerBottomLeftList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFT + " FROM " + DateiMemoDbHelper.TABLE_DATEI_LIST;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        double CornerBottomLeft;

        while(!cursor.isAfterLast()) {
            CornerBottomLeft = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFT));
            CornerBottomLeftList.add(CornerBottomLeft);
            Log.d(LOG_TAG, selectQuery);
            cursor.moveToNext();
        }

        cursor.close();

        return CornerBottomLeftList;
    }


    /*
    *           Get
    *
    *
    *           Corner Top Right
    *
    *
    * */
    public List<Double> getCornerTopRight() {
        List<Double> CornerTopRightList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERTOPRIGHT + " FROM " + DateiMemoDbHelper.TABLE_DATEI_LIST;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        double CornerTopRight;

        while(!cursor.isAfterLast()) {
            CornerTopRight = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHT));
            CornerTopRightList.add(CornerTopRight);
            Log.d(LOG_TAG, selectQuery);
            cursor.moveToNext();
        }

        cursor.close();

        return CornerTopRightList;
    }


    /*
    *           Get
    *
    *
    *           Corner Top Left
    *
    *
    * */
    public List<Double> getCornerTopLeft() {
        List<Double> CornerTopLeftList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERTOPLEFT + " FROM " + DateiMemoDbHelper.TABLE_DATEI_LIST;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        double CornerTopLeft;

        while(!cursor.isAfterLast()) {
            CornerTopLeft = cursor.getDouble(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPLEFT));
            CornerTopLeftList.add(CornerTopLeft);
            Log.d(LOG_TAG, selectQuery);
            cursor.moveToNext();
        }

        cursor.close();

        return CornerTopLeftList;
    }



    public List<Long> getUid() {
        List<Long> UidList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_UID + " FROM " + DateiMemoDbHelper.TABLE_DATEI_LIST;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        long UID;

        while(!cursor.isAfterLast()) {
            UID = cursor.getInt(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_UID));
            UidList.add(UID);
            Log.d(LOG_TAG, selectQuery);
            cursor.moveToNext();
        }

        cursor.close();

        return UidList;
    }


    /*
    *           Get
    *
    *
    *           Punkt X
    *
    *
    * */
    public Cursor getPunktX(long dateiMemo_Id) {

        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_PUNKTX +" FROM " + DateiMemoDbHelper.TABLE_DATEI_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_UID + " = " + dateiMemo_Id;

        Log.e(LOG_TAG, selectQuery);

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();


        return c;
    }



    /*
    *           Get
    *
    *
    *           Punkt Y
    *
    *
    * */
    public Cursor getPunktY(long dateiMemo_Id) {

        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_PUNKTY +" FROM " + DateiMemoDbHelper.TABLE_DATEI_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_UID + " = " + dateiMemo_Id;

        Log.e(LOG_TAG, selectQuery);

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();


        return c;
    }

    public Cursor getCorner(long dateiMemo_Id) {

        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_CORNERTOPLEFT +", "+ DateiMemoDbHelper.COLUMN_CORNERTOPRIGHT +
                            ", "+ DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFT + ", "+ DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHT
                            +" FROM " + DateiMemoDbHelper.TABLE_DATEI_LIST + " WHERE "
                            + DateiMemoDbHelper.COLUMN_UID + " = " + dateiMemo_Id;

        Log.e(LOG_TAG, selectQuery);

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();


        return c;
    }


//    /*
//    *
//    *
//    *                               Get einzelne Data
//    *
//    *
//    *
//    * */
//    public DateiMemo getDataEinzelneRow(long dateiMemo_Id) {
//
//
//
//        String selectQuery = "SELECT  * FROM " + DateiMemoDbHelper.TABLE_DATEI_LIST + " WHERE "
//                + DateiMemoDbHelper.COLUMN_UID + " = " + dateiMemo_Id;
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
//        DateiMemo dateiMemo = new DateiMemo();
//        dateiMemo.setUid(c.getLong(c.getColumnIndex(DateiMemoDbHelper.COLUMN_UID)));
//        dateiMemo.setUsername((c.getString(c.getColumnIndex(DateiMemoDbHelper.COLUMN_USERNAME))));
//        dateiMemo.setPassword(c.getString(c.getColumnIndex(DateiMemoDbHelper.COLUMN_PASSWORD)));
//        dateiMemo.setChecked(isChecked);
//        dateiMemo.setCornerBottomLeft(c.getDouble(c.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFT)));
//        dateiMemo.setCornerBottomRight(c.getDouble(c.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHT)));
//        dateiMemo.setCornerTopLeft(c.getDouble(c.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPLEFT)));
//        dateiMemo.setCornerTopRight(c.getDouble(c.getColumnIndex(DateiMemoDbHelper.COLUMN_CORNERTOPRIGHT)));
//        dateiMemo.setPunktX(c.getDouble(c.getColumnIndex(DateiMemoDbHelper.COLUMN_PUNKTX)));
//        dateiMemo.setPunktY(c.getDouble(c.getColumnIndex(DateiMemoDbHelper.COLUMN_PUNKTY)));
//        dateiMemo.setIP(c.getDouble(c.getColumnIndex(DateiMemoDbHelper.COLUMN_IP)));
//        dateiMemo.setCountPeers(c.getInt(c.getColumnIndex(DateiMemoDbHelper.COLUMN_COUNTPEERS)));
//
//        c.close();
//
//        return dateiMemo;
//    }
}