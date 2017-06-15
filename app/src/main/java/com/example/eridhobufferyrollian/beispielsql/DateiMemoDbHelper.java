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

    //SUPER verwendet man, weil unsere "helper" ist eine Ableitung von SQLiteOpenHelper
    public DateiMemoDbHelper(Context context) {
        super(context, "PLATZHALTER_DATENBANKNAME", null, 1);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}