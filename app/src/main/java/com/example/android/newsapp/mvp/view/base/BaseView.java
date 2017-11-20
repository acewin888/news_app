package com.example.android.newsapp.mvp.view.base;

/**
 * Created by kevinsun on 11/13/17.
 */

public interface BaseView {

    void showProgress();

    void hideProgress();

    void showMessage(String message);
}
