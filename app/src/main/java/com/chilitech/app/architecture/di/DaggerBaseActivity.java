package com.chilitech.app.architecture.di;

import com.chilitech.app.architecture.di.component.ActivityComponent;
import com.chilitech.app.architecture.di.component.ApplicationComponent;
import com.chilitech.app.architecture.di.component.DaggerActivityComponent;
import com.chilitech.app.architecture.di.modules.ActivityModule;
import com.chilitech.base.BaseActivity;
import com.chilitech.base.wrapper.DiWrapper;


public abstract class DaggerBaseActivity extends BaseActivity implements DiWrapper {

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    private ApplicationComponent getApplicationComponent() {
        return ((DaggerApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder().applicationComponent(getApplicationComponent()).activityModule
                (getActivityModule()).build();
    }

    @Override
    protected void onSetContentView() {
        onInject();
    }

    @Override
    public void onInject() {
        getApplicationComponent().inject(this);
    }
}