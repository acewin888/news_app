package com.example.android.newsapp.network;

import com.example.android.newsapp.App;
import com.example.android.newsapp.mvp.entity.News;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by kevinsun on 11/13/17.
 */

public class RetrofitManager {

    private final String BASEURL = "https://newsapi.org/v2/";

    private NewsService newsService;

    private static RetrofitManager retrofitManager;

    private static volatile OkHttpClient okHttpClient;

    private RetrofitManager() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkhttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        newsService = retrofit.create(NewsService.class);

    }

    private OkHttpClient getOkhttpClient() {
        if (okHttpClient == null) {
            synchronized (RetrofitManager.class) {
                Cache cache = new Cache(new File(App.getAppContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder().cache(cache)
                            .connectTimeout(6, TimeUnit.SECONDS)
                            .readTimeout(6, TimeUnit.SECONDS)
                            .writeTimeout(6, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    public static RetrofitManager getNewInstance() {
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager();
        }
        return retrofitManager;
    }

    public NewsService getNewsService() {
        return newsService;
    }

}
