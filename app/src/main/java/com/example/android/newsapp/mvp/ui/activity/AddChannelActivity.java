package com.example.android.newsapp.mvp.ui.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.android.newsapp.R;
import com.example.android.newsapp.event.ChannelItemMoveEvent;
import com.example.android.newsapp.listener.OnItemClickListener;
import com.example.android.newsapp.mvp.entity.News;
import com.example.android.newsapp.mvp.ui.activity.base.BaseActivity;
import com.example.android.newsapp.mvp.ui.adapter.NewsChannelAdapter;
import com.example.android.newsapp.util.RxBus;
import com.example.android.newsapp.widget.ItemDragHelperCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.functions.Action1;

public class AddChannelActivity extends BaseActivity {

    @BindView(R.id.news_channel_mine_rv)
    RecyclerView myChannelRecycleView;

    @BindView(R.id.news_channel_more_rv)
    RecyclerView moreChannelRecycleView;

    private List<String> mineChannel = new ArrayList<>();

    private List<String> moreChannel = new ArrayList<>();


    private NewsChannelAdapter mineAdapter;
    private NewsChannelAdapter moreAdapter;



    @Override
    public int getLayoutId() {
        return R.layout.activity_add_channel;
    }

    @Override
    public void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    public void initViews() {

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSubscription = RxBus.getInstance().toObservable(ChannelItemMoveEvent.class)
                .subscribe(new Action1<ChannelItemMoveEvent>() {
                    @Override
                    public void call(ChannelItemMoveEvent channelItemMoveEvent) {
                        int fromPosition = channelItemMoveEvent.getFromposotion();
                        int toPosition = channelItemMoveEvent.getToposition();
                    }
                });


        mineChannel.add("kevin");
        mineChannel.add("jose");
        mineChannel.add("dfee");
        mineChannel.add("kack");
        mineChannel.add("jose");
        mineChannel.add("dfee");
        mineChannel.add("kack");

        moreChannel.add("aaa");
        moreChannel.add("bbb");
        moreChannel.add("ccc");
        moreChannel.add("ddd");
        moreChannel.add("eee");
        moreChannel.add("fff");
        moreChannel.add("ggg");

        initRecycleViewChannel(mineChannel, moreChannel);


    }
    private void initRecycleViewChannel(List<String> myChannel, List<String> moreChannel){
        initRecycleViewMineAndMore(myChannel,moreChannel);
    }
    private void initRecycleViewMineAndMore(List<String> mineChannel, List<String> moreChannel){
        initRecyclerView(myChannelRecycleView, mineChannel, true);
        initRecyclerView(moreChannelRecycleView, moreChannel, false);
    }
    private void initRecyclerView(RecyclerView recyclerView, List<String> channel, final boolean isChannelMine){
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (isChannelMine) {
            mineAdapter = new NewsChannelAdapter(channel);
            recyclerView.setAdapter(mineAdapter);
              setChannelMineOnItemClick();

            initItemDragHelper();
        } else {
           moreAdapter = new NewsChannelAdapter(channel);
            recyclerView.setAdapter(moreAdapter);
        //    setChannelMoreOnItemClick();
        }
    }

    private void setChannelMineOnItemClick(){
        mineAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemSelect(View view, int position) {
                String title = mineAdapter.getData().get(position);
                moreAdapter.add(1, title);
                mineAdapter.delete(position);
            }
        });
    }

    private void  initItemDragHelper(){
        ItemDragHelperCallback itemDragHelperCallback = new ItemDragHelperCallback();
        itemDragHelperCallback.setOnItemMoveListener(mineAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragHelperCallback);
        itemTouchHelper.attachToRecyclerView(myChannelRecycleView);

        mineAdapter.setItemDragHelperCallback(itemDragHelperCallback);

    }
}
