package com.chilitech.base.manager.net;

import android.annotation.SuppressLint;


public class NetManager {

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static NetManager instance = new NetManager();
    }

    public static NetManager getInstance() {
        return SingletonHolder.instance;
    }

    protected NetManager() {
    }

    protected <P, T> void doBack(NetHandler<P, T> handler,
                                 RequestParams<P> request, RequestCallback<ResponseData<T>> callback) {
        handler.doBack(request, callback);
    }

    protected <P, T> void doSync(NetHandler<P, T> handler,
                                 RequestParams<P> request, RequestCallback<ResponseData<T>> callback) {
        handler.doSync(request, callback);
    }
}
