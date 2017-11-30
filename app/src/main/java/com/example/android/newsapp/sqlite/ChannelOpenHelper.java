package com.example.android.newsapp.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kevinsun on 11/29/17.
 */

public class ChannelOpenHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "channel.db";

    private static final String CREATE_MINE_TABLE =
            "CREATE TABLE " + TableContract.FeedEntry.MINE_TABLE + " (" +
                    TableContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    TableContract.FeedEntry.MINE_CHANNEL_FIX + " INTEGER," +
                    TableContract.FeedEntry.MINE_CHANNEL_LIST + " TEXT)";

    private static final String CREATE_MORE_TABLE =
            "CREATE TABLE " + TableContract.FeedEntry.MORE_TABLE + " (" +
                    TableContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    TableContract.FeedEntry.MINE_CHANNEL_LIST + " TEXT)";

    private static final String DELETE_MINE_TABLE =
            "DROP TABLE IF EXISTS " + TableContract.FeedEntry.MINE_TABLE;

    private static final String DELETE_MORE_TABLE =
            "DROP TABLE IF EXISTS " + TableContract.FeedEntry.MORE_TABLE;

    public ChannelOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MINE_TABLE);
        db.execSQL(CREATE_MORE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DELETE_MINE_TABLE);
        db.execSQL(DELETE_MORE_TABLE);
        onCreate(db);
    }
}
