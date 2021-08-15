package com.chilitech.base.wrapper;


public interface DiWrapper {

    /**
     * activity中：onSetContentView()后调用
     * fragment中：onFragmentCreate()后调用
     */
    void onInject();
}
