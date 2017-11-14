package com.example.android.newsapp.mvp.view.base;

/**
 * Created by kevinsun on 11/13/17.
 */

public interface BaseView<T> {

    void showProgress(T data);

    void hideProgress();

    void showMessage(String message);
}
