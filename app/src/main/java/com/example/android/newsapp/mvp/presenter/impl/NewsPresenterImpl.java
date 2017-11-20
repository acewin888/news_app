package com.example.android.newsapp.mvp.presenter.impl;

import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.mvp.interactor.impl.NewsInteractorImpl;
import com.example.android.newsapp.mvp.presenter.base.BasePresenterImpl;
import com.example.android.newsapp.mvp.view.NewsView;
import com.example.android.newsapp.mvp.view.base.BaseView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by kevinsun on 11/13/17.
 */

public class NewsPresenterImpl extends BasePresenterImpl<NewsView, News>{

    private NewsInteractorImpl newsInteractor;

    private List<News> newsList = new ArrayList<>();

    @Inject
    public NewsPresenterImpl(NewsInteractorImpl newsInteractor){
        this.newsInteractor = newsInteractor;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        newsInteractor.loadNews(this, "associated-press", "c10a1af09bbc4567844a7f5c7ffd289c" );

        newsInteractor.loadNews(this, "abc-news", "c10a1af09bbc4567844a7f5c7ffd289c" );

        newsInteractor.loadNews(this, "bbc-news", "c10a1af09bbc4567844a7f5c7ffd289c" );

        newsInteractor.loadNews(this, "bloomberg", "c10a1af09bbc4567844a7f5c7ffd289c" );

    }

    @Override
    public void success(News data) {


        newsList.add(data);




        mView.initViewpager(newsList);

        mView.hideProgress();

    }
}
