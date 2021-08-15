package com.chilitech.base.manager.net;


public interface NetHandler<P, B> {

    void doBack(RequestParams<P> requestValues, RequestCallback<ResponseData<B>> callback);

    void doSync(RequestParams<P> requestValues, RequestCallback<ResponseData<B>> callback);

}
