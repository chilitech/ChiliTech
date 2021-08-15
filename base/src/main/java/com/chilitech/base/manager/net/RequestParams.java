package com.chilitech.base.manager.net;


public class RequestParams<P> {

    private P params;

    public RequestParams(P body) {
        this.params = body;
    }

    public P getParams() {
        return params;
    }
}
