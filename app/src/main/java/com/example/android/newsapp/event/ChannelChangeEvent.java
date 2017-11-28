package com.example.android.newsapp.event;

import java.util.List;

/**
 * Created by kevinsun on 11/22/17.
 */

public class ChannelChangeEvent {
    private List<String> list;

    public ChannelChangeEvent(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }
}
