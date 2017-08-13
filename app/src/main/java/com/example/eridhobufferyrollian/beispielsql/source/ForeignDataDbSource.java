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

import java.util.ArrayList;
import java.util.List;

public class ForeignDataDbSource {
    private static final String LOG_TAG = ForeignDataDbSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DateiMemoDbHelper dbHelper;

    //Array
    private String[] columns_ForeignData = {
            DateiMemoDbHelper.COLUMN_FOTOID,
            DateiMemoDbHelper.COLUMN_UID,
            DateiMemoDbHelper.COLUMN_CHECKED
    };
}
