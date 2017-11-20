package com.example.android.newsapp.mvp.view;

import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by kevinsun on 11/20/17.
 */

public interface NewsView extends BaseView {

    void initViewpager(List<News> newsList);
}
