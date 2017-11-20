package com.example.android.newsapp.mvp.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.newsapp.R;
import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.mvp.presenter.impl.NewsPresenterImpl;
import com.example.android.newsapp.mvp.ui.activity.MainActivity;
import com.example.android.newsapp.mvp.ui.adapter.NewsListAdapter;
import com.example.android.newsapp.mvp.ui.fragment.base.BaseFragment;
import com.example.android.newsapp.mvp.view.base.BaseView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by kevinsun on 11/14/17.
 */

public class NewsListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseView {

    @BindView(R.id.news_recycle_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;



    @Inject
    NewsListAdapter newsListAdapter;

    @Inject
    NewsPresenterImpl newsPresenter;

    @Inject
    Activity mActivity;



    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {


        List<News.Articles> list = getArguments().getParcelableArrayList("article");
        newsListAdapter.setArticlesList(list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(newsListAdapter);

        mPresenter = newsPresenter;
        mPresenter.attachView(this);
        mPresenter.onCreate();

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        mPresenter.onCreate();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {

    }


}
