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

public class NeighborDbSource {
    private static final String LOG_TAG = NeighborDbSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DateiMemoDbHelper dbHelper;

    //neue Array String für Neighbor
    private String[] columns_Neighbor = {
            DateiMemoDbHelper.COLUMN_CORNERBOTTOMRIGHT,
            DateiMemoDbHelper.COLUMN_CORNERBOTTOMLEFT,
            DateiMemoDbHelper.COLUMN_CORNERTOPLEFT,
            DateiMemoDbHelper.COLUMN_CORNERTOPRIGHT,
            DateiMemoDbHelper.COLUMN_PUNKTX,
            DateiMemoDbHelper.COLUMN_PUNKTY,
            DateiMemoDbHelper.COLUMN_UIP,
            DateiMemoDbHelper.COLUMN_RTT,
            DateiMemoDbHelper.COLUMN_UID,
            DateiMemoDbHelper.COLUMN_CHECKED
    };
}
