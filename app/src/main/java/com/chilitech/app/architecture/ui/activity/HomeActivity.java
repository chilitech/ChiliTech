package com.chilitech.app.architecture.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chilitech.app.architecture.R;
import com.chilitech.app.architecture.di.DaggerBaseActivity;
import com.chilitech.base.utils.ToolBarUtil;
import com.chilitech.domain.features.home.HomeContract;
import com.chilitech.domain.features.home.HomePresenter;

import java.util.List;

import javax.inject.Inject;


public class HomeActivity extends DaggerBaseActivity implements HomeContract.View {

    @Inject
    HomePresenter homePresenter;

    @Override
    public void onInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        ToolBarUtil.setToolBarTitle(getToolbar(), "Main");
    }

    @Override
    protected void initData() {

    }

    public void gotoHomeActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void requestCategorySuccess(List<String> list) {
        if (null != list) {
            //RecyclerView
        }
    }

    @Override
    public void requestCategoryFail() {

    }

    @Override
    public void requestCategoryDetailSuccess(List<String> list) {
        if (null != list) {
            //RecyclerView
        }
    }

    @Override
    public void requestCategoryDetailFail() {

    }

    @Override
    public void requestBannerSuccess(String url) {

    }

    @Override
    public void requestBannerFail() {

    }

    @Override
    public void requestUpdateStrategySuccess(boolean needToBeUpdated) {

    }

    @Override
    public void requestUpdateStrategyFail() {

    }
}
