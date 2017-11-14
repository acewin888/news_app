package com.example.android.newsapp.mvp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.newsapp.R;
import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.mvp.presenter.impl.NewsPresenterImpl;
import com.example.android.newsapp.mvp.ui.activity.base.BaseActivity;
import com.example.android.newsapp.mvp.view.base.BaseView;
import com.example.android.newsapp.network.RetrofitManager;

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
import static com.example.android.newsapp.network.RetrofitManager.getNewInstance;

public class MainActivity extends BaseActivity implements BaseView<News> {

    @Inject
    NewsPresenterImpl newsPresenterImpl;

    TextView textView;


    @Override
    public int getLayoutId() {
        return  R.layout.activity_main;
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

        textView = (TextView) findViewById(R.id.text_view);


//        RetrofitManager.getNewInstance().getNewsService().getNews("abc-news-au","top", "c10a1af09bbc4567844a7f5c7ffd289c")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<News>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d("xuyang sun", e.toString());
//                    }
//
//                    @Override
//                    public void onNext(News news) {
//                        String name = news.getStatus();
//                        textView.setText(news.getSource());
//                    }
//                });


//        RetrofitManager.getNewInstance().getNewsService().fetchNews()
//                .enqueue(new Callback<News>() {
//                    @Override
//                    public void onResponse(Call<News> call, Response<News> response) {
//                        textView.setText(response.body().getSource());
//                    }
//
//                    @Override
//                    public void onFailure(Call<News> call, Throwable t) {
//                        Log.d("xuyang sun", t.toString());
//
//                    }
//                });






    }


    @Override
    public void showProgress(News data) {

        String name = data.getStatus();
        textView.setText(data.getSource());

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(String message) {

        Log.d("xuyang sun", message);


    }
}
