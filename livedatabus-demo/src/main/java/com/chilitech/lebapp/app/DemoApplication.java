package com.chilitech.lebapp.app;

import android.app.Application;
import android.util.Log;

import com.chilitech.livedatabus.LiveDataBus;


public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("LiveEventBus", "DemoApplication.this: " + DemoApplication.this);
        LiveDataBus
                .config()
                .lifecycleObserverAlwaysActive(true);
    }
}
