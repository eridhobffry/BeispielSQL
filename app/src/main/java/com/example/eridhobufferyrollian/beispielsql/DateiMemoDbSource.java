package com.example.eridhobufferyrollian.beispielsql;

/**
 * Created by en on 15.06.17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DateiMemoDbSource {

    private static final String LOG_TAG = DateiMemoDbSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DateiMemoDbHelper dbHelper;


    public DateiMemoDbSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new DateiMemoDbHelper(context);
    }
}