package com.chilitech.lebapp.event;

import com.chilitech.livedatabus.core.LiveEvent;


public class DemoEvent implements LiveEvent {
    public final String content;

    public DemoEvent(String content) {
        this.content = content;
    }
}
