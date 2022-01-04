package com.chilitech.lebapp.service;

import android.app.Service;
import androidx.lifecycle.Observer;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

import android.widget.Toast;

import com.chilitech.lebapp.LiveEventBusDemo;
import com.chilitech.livedatabus.LiveDataBus;


public class IpcService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_BROADCAST, String.class)
                .observeForever(observer);
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_BROADCAST_IN_APP, String.class)
                .observeForever(observer);
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_BROADCAST_GLOBAL, String.class)
                .observeForever(observer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_BROADCAST, String.class)
                .removeObserver(observer);
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_BROADCAST_IN_APP, String.class)
                .removeObserver(observer);
        LiveDataBus
                .get(LiveEventBusDemo.KEY_TEST_BROADCAST_GLOBAL, String.class)
                .removeObserver(observer);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Observer<String> observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            Toast.makeText(IpcService.this, s, Toast.LENGTH_SHORT).show();
        }
    };
}
