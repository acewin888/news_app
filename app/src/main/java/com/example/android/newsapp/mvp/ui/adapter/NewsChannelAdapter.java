package com.example.android.newsapp.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.newsapp.R;
import com.example.android.newsapp.event.ChannelItemMoveEvent;
import com.example.android.newsapp.listener.OnItemClickListener;
import com.example.android.newsapp.mvp.ui.adapter.baseadapter.BaseRecycleAdapter;
import com.example.android.newsapp.util.RxBus;
import com.example.android.newsapp.widget.ItemDragHelperCallback;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kevinsun on 11/21/17.
 */

public class NewsChannelAdapter extends BaseRecycleAdapter<String>  implements ItemDragHelperCallback.OnItemMoveListener {

    private OnItemClickListener onItemClickListener;

    private ItemDragHelperCallback itemDragHelperCallback;

    public void setItemDragHelperCallback(ItemDragHelperCallback itemDragHelperCallback) {
        this.itemDragHelperCallback = itemDragHelperCallback;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public NewsChannelAdapter(List<String> list) {
        super(list);
    }

    public List<String> getData(){
        return  mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_channel, parent, false);
        NewsChannerViewHolder newsChannerViewHolder = new NewsChannerViewHolder(view);
        handleLongPress(newsChannerViewHolder);
        handleOnCLick(newsChannerViewHolder);
        return newsChannerViewHolder;
    }

    private void handleLongPress(final NewsChannerViewHolder newsChannerViewHolder){
        if(itemDragHelperCallback != null){
            newsChannerViewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    itemDragHelperCallback.setmIsLongPressEnabled(true);
                    return false;
                }
            });
        }
    }

    private void handleOnCLick(final NewsChannerViewHolder newsChannerViewHolder) {
        if (onItemClickListener != null) {
            newsChannerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemSelect(v, newsChannerViewHolder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String title = mList.get(position);
        NewsChannerViewHolder newsChannerViewHolder = (NewsChannerViewHolder) holder;
        newsChannerViewHolder.newsChannelTitle.setText(title);
    }

    @Override
    public boolean onItemMove(int fromPostion, int toPosition) {
        Collections.swap(mList, fromPostion, toPosition);
        notifyItemMoved(fromPostion, toPosition);
        RxBus.getInstance().post(new ChannelItemMoveEvent(fromPostion, toPosition));

        return  true;
    }


    class NewsChannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_channel_tv)
        TextView newsChannelTitle;

        public NewsChannerViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
