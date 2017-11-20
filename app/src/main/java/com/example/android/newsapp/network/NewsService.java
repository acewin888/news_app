package com.example.android.newsapp.network;

import com.example.android.newsapp.mvp.entity.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kevinsun on 11/13/17.
 */

public interface NewsService {


    @GET("top-headlines")
    Observable<News> getNews(@Query("sources") String source,
                             @Query("apiKey") String apiKey);


    @GET("articles?source=abc-news-au&sortBy=top&apiKey=c10a1af09bbc4567844a7f5c7ffd289c")
    Call<News> fetchNews();

    @GET("articles?source=abc-news-au&sortBy=top&apiKey=c10a1af09bbc4567844a7f5c7ffd289c")
    Observable<News> getNews2();


}
