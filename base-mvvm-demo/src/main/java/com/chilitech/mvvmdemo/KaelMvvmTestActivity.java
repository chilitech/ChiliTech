package com.chilitech.mvvmdemo;

import android.os.Bundle;
import android.view.View;

import com.chilitech.mvvm.base.BaseActivity;
import com.chilitech.mvvmdemo.databinding.ActivityKaelMvvmTestBinding;

public class KaelMvvmTestActivity extends BaseActivity<ActivityKaelMvvmTestBinding, KaelMvvmViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_kael_mvvm_test;
    }

    @Override
    public int initVariableId() {
        return BR.kaelViewModel;
    }

    public void close(View view) {
        this.finish();
    }
}