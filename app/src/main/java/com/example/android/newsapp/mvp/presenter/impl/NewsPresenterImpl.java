package com.example.android.newsapp.mvp.presenter.impl;

import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.mvp.interactor.impl.NewsInteractorImpl;
import com.example.android.newsapp.mvp.presenter.base.BasePresenterImpl;
import com.example.android.newsapp.mvp.view.base.BaseView;

import javax.inject.Inject;

/**
 * Created by kevinsun on 11/13/17.
 */

public class NewsPresenterImpl extends BasePresenterImpl<BaseView, News>{

    private NewsInteractorImpl newsInteractor;

    @Inject
    public NewsPresenterImpl(NewsInteractorImpl newsInteractor){
        this.newsInteractor = newsInteractor;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        newsInteractor.loadNews(this, "abc-news-au","top", "c10a1af09bbc4567844a7f5c7ffd289c" );
    }
}
