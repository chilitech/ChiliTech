package com.chilitech.data.base;



public class BaseRepository {

    protected boolean mCacheIsDirty = false;

    public void refresh() {
        mCacheIsDirty = true;
    }

}
