package com.example.android.newsapp.mvp.presenter.impl;

import com.example.android.newsapp.mvp.entity.Constant;
import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.mvp.interactor.impl.NewsInteractorImpl;
import com.example.android.newsapp.mvp.presenter.base.BasePresenterImpl;
import com.example.android.newsapp.mvp.view.NewsView;
import com.example.android.newsapp.mvp.view.base.BaseView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.R.attr.data;
import static android.R.id.list;

/**
 * Created by kevinsun on 11/13/17.
 */

public class NewsPresenterImpl extends BasePresenterImpl<NewsView, News> {

    private NewsInteractorImpl newsInteractor;

    private List<News> newsList = new ArrayList<>();

    private List<String> channel;


    @Inject
    public NewsPresenterImpl(NewsInteractorImpl newsInteractor) {
        this.newsInteractor = newsInteractor;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (channel != null && channel.size() > 0) {
            for (int x = 0; x < channel.size(); x++) {
                newsInteractor.loadNews(this, channel.get(x), Constant.API_KEY);
            }
        } else {
            newsInteractor.loadNews(this, Constant.ABC_NEWS, Constant.API_KEY);
            newsInteractor.loadNews(this, Constant.BBC_NEWS, Constant.API_KEY);
        }

    }


    @Override
    public void success(News data) {
            newsList.add(data);
            mView.initViewpager(newsList);
            mView.hideProgress();


    }

    @Override
    public void setChannel(List<String> list) {
        channel = list;
    }
}
