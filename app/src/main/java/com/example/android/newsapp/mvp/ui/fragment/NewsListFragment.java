package com.example.android.newsapp.mvp.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.newsapp.R;
import com.example.android.newsapp.event.ScrolltoTopEvent;
import com.example.android.newsapp.listener.OnItemClickListener;
import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.mvp.presenter.impl.NewsPresenterImpl;
import com.example.android.newsapp.mvp.ui.activity.AddChannelActivity;
import com.example.android.newsapp.mvp.ui.activity.MainActivity;
import com.example.android.newsapp.mvp.ui.activity.base.NewsDetailActivity;
import com.example.android.newsapp.mvp.ui.adapter.NewsListAdapter;
import com.example.android.newsapp.mvp.ui.fragment.base.BaseFragment;
import com.example.android.newsapp.mvp.view.base.BaseView;
import com.example.android.newsapp.util.NetUtil;
import com.example.android.newsapp.util.RxBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by kevinsun on 11/14/17.
 */

public class NewsListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseView, OnItemClickListener {

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

    private List<News.Articles> list;



    @Override
    public void initInjector() {
        fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {

        list = getArguments().getParcelableArrayList("article");
        newsListAdapter.setArticlesList(list);
        newsListAdapter.setOnItemClickListener(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(newsListAdapter);

        mPresenter = newsPresenter;
        mPresenter.attachView(this);

        swipeRefreshLayout.setOnRefreshListener(this);
        progressBar.setVisibility(View.INVISIBLE);


        scrollToTopEvent();


    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        NetUtil.isNetWorkErrShowMsg();
//        mPresenter.onCreate();

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
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {

    }


    @Override
    public void onItemSelect(View view, int position) {
        Intent intent = new Intent(this.getActivity(), NewsDetailActivity.class);
        intent.putExtra("detail",list.get(position) );
        startActivity(intent);
    }
    private void scrollToTopEvent(){
        mSubscription = RxBus.getInstance().toObservable(ScrolltoTopEvent.class)
                .subscribe(new Action1<ScrolltoTopEvent>() {
                    @Override
                    public void call(ScrolltoTopEvent scrolltoTopEvent) {
                        recyclerView.getLayoutManager().scrollToPosition(0);
                    }
                });
    }
}
