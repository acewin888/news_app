package com.example.android.newsapp.mvp.entity;

/**
 * Created by kevinsun on 11/29/17.
 */

public class Channel {

    private String channelName;

    private int fixNumber;

    public Channel(String channelName, int fixNumber) {
        this.channelName = channelName;
        this.fixNumber = fixNumber;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getFixNumber() {
        return fixNumber;
    }

    public void setFixNumber(int fixNumber) {
        this.fixNumber = fixNumber;
    }
}
