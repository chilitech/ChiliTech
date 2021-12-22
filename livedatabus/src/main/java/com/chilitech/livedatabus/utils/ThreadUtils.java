package com.chilitech.livedatabus.utils;

import android.os.Looper;


public final class ThreadUtils {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
