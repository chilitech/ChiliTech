package com.chilitech.app.architecture.event;



public class MessageEvent {

    public static final int EVENT_CODE = 0x001;


    public int code;
    public Object obj;

    public MessageEvent(int code) {
        this.code = code;
    }

    public MessageEvent(int code, Object obj) {
        this.code = code;
        this.obj = obj;
    }
}
