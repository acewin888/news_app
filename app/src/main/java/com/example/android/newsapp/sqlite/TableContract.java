package com.example.android.newsapp.sqlite;

import android.provider.BaseColumns;

/**
 * Created by kevinsun on 11/29/17.
 */

public class TableContract {

    private TableContract() {

    }

    public static class FeedEntry implements BaseColumns {




        public static final String MINE_TABLE = "mine_table";
        public static final String MINE_CHANNEL_LIST = "mine_channel_list";

        /**
         *  1 means is fixed
         *  0 measn is not fixed
         */
        public static final String MINE_CHANNEL_FIX = "mine_channel_fix";









        public static final String MORE_TABLE = "more_table";
        public static final String MORE_CHANNEL_LIST = "more_channel_list";
    }

}
