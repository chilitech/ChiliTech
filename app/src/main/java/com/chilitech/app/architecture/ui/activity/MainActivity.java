package com.chilitech.app.architecture.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.chilitech.app.architecture.R;
import com.chilitech.app.architecture.di.DaggerBaseActivity;
import com.chilitech.base.utils.ToolBarUtil;

public class MainActivity extends DaggerBaseActivity {

    @Override
    public void onInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        ToolBarUtil.setToolBarTitle(getToolbar(), "Main");
    }

    @Override
    protected void initData() {

    }

    public void openLogin(View view) {
        LoginActivity.goActivity(this);
    }
}
