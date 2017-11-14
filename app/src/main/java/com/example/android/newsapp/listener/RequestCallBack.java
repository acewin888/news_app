package com.example.android.newsapp.listener;

/**
 * Created by kevinsun on 11/13/17.
 */

public interface RequestCallBack<T> {

    void success(T data);

    void onError(String message);
}
