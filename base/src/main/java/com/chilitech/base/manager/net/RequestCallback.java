package com.chilitech.base.manager.net;


public interface RequestCallback<R> {

    void onSuccess(R response);

    void onError(int code, String msg);
}
