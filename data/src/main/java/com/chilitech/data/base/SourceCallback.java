package com.chilitech.data.base;



public interface SourceCallback<T> {

    void onLoaded(T t);

    void onDataNotAvailable();
}
