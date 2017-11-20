package com.example.android.newsapp.mvp.ui.activity;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.android.newsapp.R;
import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.mvp.presenter.impl.NewsPresenterImpl;
import com.example.android.newsapp.mvp.ui.activity.base.BaseActivity;
import com.example.android.newsapp.mvp.ui.fragment.NewsListFragment;
import com.example.android.newsapp.mvp.view.NewsView;
import com.example.android.newsapp.mvp.view.base.BaseView;
import com.example.android.newsapp.network.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.R.attr.data;
import static com.example.android.newsapp.R.id.textView;
import static com.example.android.newsapp.network.RetrofitManager.getNewInstance;

public class MainActivity extends BaseActivity implements NewsView {

    @Inject
    NewsPresenterImpl newsPresenterImpl;


    @BindView(R.id.view_pager)
    ViewPager viewPager;



    private List<NewsListFragment> fragmentList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initInjector() {
        activityComponent.inject(this);
        // call the inject method
    }

    @Override
    public void initViews() {

        mPresenter = newsPresenterImpl;
        mPresenter.attachView(this);
        // attach presenter
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter.onCreate();


    }

    @Override
    public void initViewpager(List<News> newsList) {
        final List<String> titleList = new ArrayList<>();
        if( newsList != null){
            setNewsList(newsList, titleList);
            setViewPager(titleList);

        }
    }

    public static class ScrollAdapter extends FragmentStatePagerAdapter {

        private List<String> title;

        private List<NewsListFragment> fragmentList;


        public ScrollAdapter(FragmentManager fm, List<String> title, List<NewsListFragment> fragmentList) {
            super(fm);
            this.title = title;
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
           return fragmentList.size();
        }
    }


//    @Override
//    public void showProgress(News data) {
//
//
//        Log.d("xuyang========== sun", data.getStatus());
//
//        List<Fragment> list = createFragment(data);
//
//
//        ScrollAdapter scrollAdapter = new ScrollAdapter(getSupportFragmentManager());
//
//        scrollAdapter.setFragmentList(list);
//
//        viewPager.setAdapter(scrollAdapter);
//
//
//    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(String message) {

        Log.d("xuyang========== sun", message);


    }

    private NewsListFragment createFragment(News news){
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("article", (ArrayList)news.getArticles());

        return  fragment;
    }

    private void setNewsList(List<News> newsList, List<String> titlename){
        fragmentList.clear();
        for(News news  : newsList){
            NewsListFragment newsListFragment = createFragment(news);
            fragmentList.add(newsListFragment);
            titlename.add(news.getStatus());
        }
    }

    private void setViewPager(List<String> newsTitle){
        ScrollAdapter scrollAdapter = new ScrollAdapter(getSupportFragmentManager(), newsTitle, fragmentList);
        viewPager.setAdapter(scrollAdapter);

    }


}
