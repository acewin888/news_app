package com.example.android.newsapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.android.newsapp.R;
import com.example.android.newsapp.annotation.BindValue;
import com.example.android.newsapp.event.ChannelChangeEvent;
import com.example.android.newsapp.event.ScrolltoTopEvent;
import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.mvp.presenter.impl.NewsPresenterImpl;
import com.example.android.newsapp.mvp.ui.activity.base.BaseActivity;
import com.example.android.newsapp.mvp.ui.fragment.NewsListFragment;
import com.example.android.newsapp.mvp.view.NewsView;
import com.example.android.newsapp.mvp.view.base.BaseView;
import com.example.android.newsapp.network.RetrofitManager;
import com.example.android.newsapp.util.MyUtil;
import com.example.android.newsapp.util.RxBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.R.attr.data;
import static android.R.attr.maxItemsPerRow;
import static android.R.attr.tickMarkTint;
import static com.example.android.newsapp.R.id.textView;
import static com.example.android.newsapp.network.RetrofitManager.getNewInstance;

@BindValue(mHasNavigationView = true)
public class MainActivity extends BaseActivity implements NewsView {

    @Inject
    NewsPresenterImpl newsPresenterImpl;


    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout myTabs;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private  List<String> titleList;


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
        mBaseNavView = navigationView;

        mPresenter = newsPresenterImpl;
        mPresenter.attachView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSubscription = RxBus.getInstance().toObservable(ChannelChangeEvent.class)
                .subscribe(new Action1<ChannelChangeEvent>() {
                    @Override
                    public void call(ChannelChangeEvent channelChangeEvent) {
                        titleList = channelChangeEvent.getList();
                        mPresenter.setChannel(titleList);
                        mPresenter.onCreate();
                    }
                });


        mPresenter.onCreate();

    }

    @OnClick({R.id.fab, R.id.add_channel_iv})
    public void onClick(View view){
        switch(view.getId()){
            case R.id.fab:
                RxBus.getInstance().post(new ScrolltoTopEvent());
                break;
            case R.id.add_channel_iv:
                Intent intent = new Intent(this, AddChannelActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initViewpager(List<News> newsList) {
        final List<String> titleList = new ArrayList<>();
        if (newsList != null) {
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

        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }

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

    private NewsListFragment createFragment(News news) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("article", (ArrayList) news.getArticles());
        fragment.setArguments(bundle);

        return fragment;
    }

    private void setNewsList(List<News> newsList, List<String> titlename) {
        fragmentList.clear();
        for (News news : newsList) {
            NewsListFragment newsListFragment = createFragment(news);
            fragmentList.add(newsListFragment);
            titlename.add(news.getArticles().get(0).getSource().getName());
        }
    }

    private void setViewPager(List<String> newsTitle) {
        ScrollAdapter scrollAdapter = new ScrollAdapter(getSupportFragmentManager(), newsTitle, fragmentList);
        viewPager.setAdapter(scrollAdapter);
        myTabs.setupWithViewPager(viewPager);
        MyUtil.dynamicSetTabLayoutMode(myTabs);




    }


}
