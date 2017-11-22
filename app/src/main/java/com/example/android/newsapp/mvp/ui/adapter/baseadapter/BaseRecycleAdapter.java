package com.example.android.newsapp.mvp.ui.adapter.baseadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.newsapp.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by kevinsun on 11/14/17.
 */

public class BaseRecycleAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> mList;

    protected OnItemClickListener onItemClickListener;

    public BaseRecycleAdapter(List<T> list){
        mList = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    protected View getView(ViewGroup parent, int layoutId){
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    @Override
    public int getItemCount() {
       if(mList == null){
           return  0;
       }else{
           return  mList.size();
       }
    }

    public void add(int position, T item){
        mList.add(position, item);
        notifyItemInserted(position);
    }

    public void delete(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }
}
