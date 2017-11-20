package com.example.android.newsapp.mvp.ui.adapter.baseadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by kevinsun on 11/14/17.
 */

public class BaseRecycleAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> mList;

    public BaseRecycleAdapter(List<T> list){
        mList = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
       if(mList == null){
           return  0;
       }else{
           return  mList.size();
       }
    }
}
