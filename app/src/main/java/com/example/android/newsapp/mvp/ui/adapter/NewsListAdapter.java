package com.example.android.newsapp.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.newsapp.R;
import com.example.android.newsapp.mvp.entity.News;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.schedulers.NewThreadScheduler;

import static android.R.attr.description;

/**
 * Created by kevinsun on 11/14/17.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListHolder> {



    @Inject
    public NewsListAdapter(){

    }

    private List<News.Articles> articlesList;

    public void setArticlesList(List<News.Articles> articlesList){
        this.articlesList = articlesList;
    }





    @Override
    public NewsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.news_item_list, parent, false );


        return new NewsListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsListHolder holder, int position) {

        holder.titleView.setText(articlesList.get(position).getTitle());

        holder.descriptionView.setText(articlesList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return  articlesList.size();
    }

    class NewsListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_icon)
        ImageView iconView;

        @BindView(R.id.news_title)
        TextView titleView;

        @BindView(R.id.news_description)
        TextView descriptionView;

        @BindView(R.id.news_published_time)
        TextView published_time_view;

        public NewsListHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }
}
