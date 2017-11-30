package com.example.android.newsapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.newsapp.mvp.entity.Channel;
import com.example.android.newsapp.mvp.entity.Constant;
import com.example.android.newsapp.util.MyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinsun on 11/29/17.
 */

public class ChannelManager {

    public static void initDB(Context context){
        if(!MyUtil.getSharePreferences().getBoolean(Constant.INIT_DB, false)){
            ChannelOpenHelper channelOpenHelper = new ChannelOpenHelper(context);
            SQLiteDatabase db = channelOpenHelper.getWritableDatabase();


        }
    }

    public static void saveMine(String name, Context context, int numberFixed){
        ChannelOpenHelper channelOpenHelper = new ChannelOpenHelper(context);
        SQLiteDatabase db = channelOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TableContract.FeedEntry.MINE_CHANNEL_FIX, numberFixed);
        contentValues.put(TableContract.FeedEntry.MINE_CHANNEL_LIST, name);

        long rowInsert = db.insert(TableContract.FeedEntry.MINE_TABLE, null,contentValues);
    }


    public static List<Channel> loadMineChannel(Context context){
        ChannelOpenHelper channelOpenHelper = new ChannelOpenHelper(context);
        SQLiteDatabase db = channelOpenHelper.getReadableDatabase();

        String[] projection = {
                TableContract.FeedEntry._ID,
                TableContract.FeedEntry.MINE_CHANNEL_FIX,
                TableContract.FeedEntry.MINE_CHANNEL_LIST

        };

        Cursor cursor = db.query(TableContract.FeedEntry.MINE_TABLE,
                projection, null, null, null,null, null);

        List<Channel> list = new ArrayList<>();

        while ((cursor.moveToNext())){
            String channelName = cursor.getString(cursor.getColumnIndex(TableContract.FeedEntry.MINE_CHANNEL_LIST));
            int number = cursor.getInt(cursor.getColumnIndex(TableContract.FeedEntry.MINE_CHANNEL_FIX));

            Channel channel  = new Channel(channelName, number);

            list.add(channel);
        }
        return  list;
    }

    public static void deleteMine(String name, Context context){

        ChannelOpenHelper channelOpenHelper = new ChannelOpenHelper(context);
        SQLiteDatabase db = channelOpenHelper.getWritableDatabase();

        String selection = TableContract.FeedEntry.MINE_CHANNEL_LIST + "=?";

        String[] args = {name};

        db.delete(TableContract.FeedEntry.MINE_TABLE, selection, args);

    }


    public static void saveMore(String name, Context context){
        ChannelOpenHelper channelOpenHelper = new ChannelOpenHelper(context);
        SQLiteDatabase db = channelOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TableContract.FeedEntry.MORE_CHANNEL_LIST, name);

        long rowInsert = db.insert(TableContract.FeedEntry.MORE_TABLE, null,contentValues);
    }


    public static List<String> loadMoreChannel(Context context){
        ChannelOpenHelper channelOpenHelper = new ChannelOpenHelper(context);
        SQLiteDatabase db = channelOpenHelper.getReadableDatabase();

        String[] projection = {
                TableContract.FeedEntry._ID,
                TableContract.FeedEntry.MORE_TABLE
        };

        Cursor cursor = db.query(TableContract.FeedEntry.MORE_TABLE,
                projection, null, null, null,null, null);

        List<String> list = new ArrayList<>();

        while ((cursor.moveToNext())){
            String channel = cursor.getString(cursor.getColumnIndex(TableContract.FeedEntry.MORE_CHANNEL_LIST));

            list.add(channel);
        }
        return  list;
    }

    public static void deleteMore(String name, Context context){

        ChannelOpenHelper channelOpenHelper = new ChannelOpenHelper(context);
        SQLiteDatabase db = channelOpenHelper.getWritableDatabase();

        String selection = TableContract.FeedEntry.MORE_CHANNEL_LIST + "=?";

        String[] args = {name};

        db.delete(TableContract.FeedEntry.MORE_TABLE, selection, args);

    }
}
