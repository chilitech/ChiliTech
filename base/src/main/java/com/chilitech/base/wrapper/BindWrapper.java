package com.chilitech.base.wrapper;


public interface BindWrapper {

    /**
     * activity中：onSetContentView()后调用
     * fragment中：onFragmentViewCreated()后调用
     */
    void onBind(Object target);

    /**
     * activity中：onDestroy()后调用
     * fragment中：onDestroyView()后调用
     */
    void onUnbind();
}
