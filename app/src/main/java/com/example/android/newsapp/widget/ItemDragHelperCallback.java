package com.example.android.newsapp.widget;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by kevinsun on 11/21/17.
 */

public class ItemDragHelperCallback extends ItemTouchHelper.Callback {

    public interface OnItemMoveListener{
        boolean onItemMove(int fromPostion, int toPosition);
    }


    private OnItemMoveListener onItemMoveListener;
    private boolean mIsLongPressEnabled;

    public void setOnItemMoveListener(OnItemMoveListener onItemMoveListener) {
        this.onItemMoveListener = onItemMoveListener;
    }

    public void setmIsLongPressEnabled(boolean mIsLongPressEnabled) {
        this.mIsLongPressEnabled = mIsLongPressEnabled;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return mIsLongPressEnabled;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFalgs = setDragFlags(recyclerView);
        int swipeFlags = 0;
        return makeMovementFlags(dragFalgs, swipeFlags);
    }

    private int setDragFlags(RecyclerView recyclerView){
        int dragFlags;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager || layoutManager instanceof StaggeredGridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        return dragFlags;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
     return !isDifferentItemViewType(viewHolder, target) &&
             onItemMoveListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());

    }

    private boolean isDifferentItemViewType(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
        return  viewHolder.getItemViewType() != target.getItemViewType();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }
}
