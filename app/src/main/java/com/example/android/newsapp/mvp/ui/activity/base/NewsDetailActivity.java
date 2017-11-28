package com.example.android.newsapp.mvp.ui.activity.base;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.newsapp.R;
import com.example.android.newsapp.annotation.BindValue;
import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.mvp.ui.activity.AboutActivity;
import com.example.android.newsapp.util.MyUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class NewsDetailActivity extends BaseActivity {

    private News.Articles article;


    @BindView(R.id.news_detail_photo_iv)
    ImageView imageView;

    @BindView(R.id.news_detail_from_tv)
    TextView titleView;

    @BindView(R.id.news_detail_body_tv)
    TextView bodyView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {

        progressBar.setVisibility(View.VISIBLE);

        article = getIntent().getExtras().getParcelable("detail");

        setupDetail(article);


    }

    private void setupDetail(News.Articles article) {
        String author = article.getAuthor();
        String title = article.getTitle();
        String description = article.getDescription();
        String url = article.getUrl();
        String urltoImage = article.getUrlToImage();
        String publishDate = article.getPublishedAt();

        setTitle(title);

        titleView.setText(publishDate);
        bodyView.setText(description);
        setUpTitleImage(urltoImage, this);

    }

    private void setUpTitleImage(String url, Context context) {
        if (url != null) {
            Picasso.with(context).load(url).into(imageView);
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.action_open_brower:
                Intent openIntent = new Intent(Intent.ACTION_VIEW);
                openIntent.setData(Uri.parse(article.getUrl()));
                startActivity(openIntent);

        }

        return super.onOptionsItemSelected(item);
    }
}
