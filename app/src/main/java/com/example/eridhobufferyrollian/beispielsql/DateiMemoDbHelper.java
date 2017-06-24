package com.example.eridhobufferyrollian.beispielsql;

/**
 * Created by en on 15.06.17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DateiMemoDbHelper extends SQLiteOpenHelper{

    private static final String LOG_TAG = DateiMemoDbHelper.class.getSimpleName();



//-------------Wie man eine verbindung zu dem SQL macht-------------------------------

    //######################    neue Database   #######################################
    public static final String DB_NAME = "datei_list_db";
    public static final int DB_VERSION = 1;
    //##################################################################################



    //------------------------- neue tabelle    -------------------------------------
    public static final String TABLE_DATEI_LIST  = "datei_list";

    public static final String COLUMN_NID = "_nid";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PEERID = "peerId";
    public static final String COLUMN_NEIGHID = "neighId";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_DATEI_LIST +
                    "(" + COLUMN_NID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT NOT NULL, " +
                    COLUMN_PASSWORD + " TEXT NOT NULL);" +
                    COLUMN_PEERID + " INTEGER NOT NULL" +
                    COLUMN_NEIGHID + " INTEGER NOT NULL";
    //-------------------------------------------------------------------------------



    //SUPER verwendet man, weil unsere "helper" ist eine Ableitung von SQLiteOpenHelper
    public DateiMemoDbHelper(Context context) {
        //super(context, "PLATZHALTER_DATENBANKNAME", null, 1); ----- ursprüngliche CODE
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    // Die onCreate-Methode wird nur aufgerufen, falls die Datenbank noch nicht existiert
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}