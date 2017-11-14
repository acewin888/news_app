package com.example.android.newsapp.mvp.interactor.impl;

import android.support.annotation.MainThread;

import com.example.android.newsapp.listener.RequestCallBack;
import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.mvp.interactor.NewsInteractor;
import com.example.android.newsapp.network.RetrofitManager;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevinsun on 11/13/17.
 */

public class NewsInteractorImpl implements NewsInteractor<News> {

    @Inject
    public NewsInteractorImpl() {

    }

    @Override
    public Subscription loadNews(final RequestCallBack<News> callBack, String source, String sortBy, String apiKey) {
        return RetrofitManager.getNewInstance().getNewsService().getNews(source, sortBy, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<News>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        callBack.onError(e.toString());

                    }

                    @Override
                    public void onNext(News news) {

                        callBack.success(news);

                    }
                });
    }


}
