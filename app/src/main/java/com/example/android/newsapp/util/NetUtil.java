package com.example.android.newsapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.android.newsapp.App;

/**
 * Created by kevinsun on 11/27/17.
 */

public class NetUtil {


    public static boolean isNetWorkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getAppContext().
                getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean isNetWorkErrShowMsg() {
        if (!isNetWorkAvailable()) {

            Toast.makeText(App.getAppContext(), "Network errorm Please try agagin", Toast.LENGTH_SHORT)
                    .show();

            return true;
        }

        return false;
    }
}
