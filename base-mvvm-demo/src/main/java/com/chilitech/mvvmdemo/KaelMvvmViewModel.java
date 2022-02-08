package com.chilitech.mvvmdemo;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.chilitech.mvvm.base.BaseViewModel;

public class KaelMvvmViewModel extends BaseViewModel {

    public MutableLiveData<String> mutableLiveData = new MutableLiveData<>();

    public KaelMvvmViewModel(@NonNull final Application application) {
        super(application);
        mutableLiveData.setValue("k");


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //可以主线程调用，也可以子线程调用
                    mutableLiveData.postValue("Kael Wong : " + i);
                }
            }
        }).start();

//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
        //主线程中调用
//                mutableLiveData.setValue("Kael Wong");
//            }
//        }, 2_000);
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
    }

    @Override
    public void onCreate() {
        Log.e("wangxiaoqi", "onCreate");
        mutableLiveData.setValue("onCreate");
    }

    @Override
    public void onDestroy() {
        Log.e("wangxiaoqi", "onDestroy");
        mutableLiveData.setValue("onDestroy");
    }

    @Override
    public void onStart() {
        Log.e("wangxiaoqi", "onStart");
        mutableLiveData.setValue("onStart");
    }

    @Override
    public void onStop() {
        Log.e("wangxiaoqi", "onStop");
        mutableLiveData.setValue("onStop");
    }

    @Override
    public void onResume() {
        Log.e("wangxiaoqi", "onResume");
        mutableLiveData.setValue("onResume");
    }

    @Override
    public void onPause() {
        Log.e("wangxiaoqi", "onPause");
        mutableLiveData.setValue("onPause");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e("wangxiaoqi", "onCleared");
        mutableLiveData.setValue("onCleared");
        if (model != null) {
            model.onCleared();
        }
    }

    public void onClickMe() {
        Toast.makeText(getApplication(), "asdf", Toast.LENGTH_SHORT).show();
    }

    public void onClickMe(View view) {
        Toast.makeText(getApplication(), "view", Toast.LENGTH_SHORT).show();
    }
}
