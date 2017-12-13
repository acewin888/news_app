package com.example.android.newsapp.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.newsapp.R;
import com.example.android.newsapp.listener.OnItemClickListener;
import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.util.MyUtil;
import com.example.android.newsapp.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.schedulers.NewThreadScheduler;

import static android.R.attr.description;
import static android.R.attr.theme;

/**
 * Created by kevinsun on 11/14/17.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListHolder> {



    @Inject
    public NewsListAdapter(){

    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    private List<News.Articles> articlesList;

    public void setArticlesList(List<News.Articles> articlesList){
        this.articlesList = articlesList;
    }

    private void setupImage(Context context, List<News.Articles> articles, int position, NewsListHolder holder){
        if(articles.get(position).getUrlToImage() != null){
            Picasso.with(context).load(articles.get(position).getUrlToImage()).resize(100,100).into(holder.circleImageView);
        }else {
            Picasso.with(context).load(R.drawable.notavailalbe).resize(100,100).into(holder.circleImageView);
        }
    }





    @Override
    public NewsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.news_item_list, parent, false );

        final NewsListHolder newsListHolder = new NewsListHolder(itemView);

        setItemOnCLickEvent(newsListHolder);
        return newsListHolder;
    }

    private void setItemOnCLickEvent(final NewsListHolder holder){
        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemSelect(v, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(NewsListHolder holder, int position) {

        holder.titleView.setText(articlesList.get(position).getTitle());

        holder.descriptionView.setText(articlesList.get(position).getDescription());

        holder.published_time_view.setText(articlesList.get(position).getPublishedAt());

        setupImage(holder.circleImageView.getContext(), articlesList, position, holder);

    }

    @Override
    public int getItemCount() {
        return  articlesList.size();
    }

    class NewsListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_icon)
        CircleImageView circleImageView;

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
