package com.example.android.newsapp.mvp.presenter.base;

import com.example.android.newsapp.listener.RequestCallBack;
import com.example.android.newsapp.mvp.view.base.BaseView;

import java.util.List;

import rx.Subscription;

/**
 * Created by kevinsun on 11/13/17.
 */

public class BasePresenterImpl<T extends BaseView, E> implements BasePresenter, RequestCallBack<E>{

    protected T mView;
    protected Subscription mSubscription;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(BaseView baseView) {

        mView =(T) baseView;

    }

    @Override
    public void onDestory() {
        // need to override to cancel mSubscription
    }

    @Override
    public void setChannel(List<String> list) {

    }

    @Override
    public void success(E data) {
        mView.showProgress();
        mView.hideProgress();

    }

    @Override
    public void onError(String message) {
        mView.hideProgress();
        mView.showMessage(message);
    }
}
