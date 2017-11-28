package com.example.android.newsapp.mvp.presenter.base;

import com.example.android.newsapp.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by kevinsun on 11/13/17.
 */

public interface BasePresenter {

    void onCreate();

    void attachView(BaseView baseView);

    void onDestory();

    void setChannel(List<String> list);
}
