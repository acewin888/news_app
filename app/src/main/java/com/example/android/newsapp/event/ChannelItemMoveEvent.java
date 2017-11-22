package com.example.android.newsapp.event;

/**
 * Created by kevinsun on 11/21/17.
 */

public class ChannelItemMoveEvent {

    private int fromposotion;
    private int toposition;

    public ChannelItemMoveEvent(int fromposotion, int toposition) {
        this.fromposotion = fromposotion;
        this.toposition = toposition;
    }

    public int getFromposotion() {
        return fromposotion;
    }

    public int getToposition() {
        return toposition;
    }
}
