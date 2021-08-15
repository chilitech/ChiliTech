package com.chilitech.app.architecture.di;

import android.app.Application;

import com.chilitech.app.architecture.di.component.ApplicationComponent;
import com.chilitech.app.architecture.di.component.DaggerApplicationComponent;
import com.chilitech.app.architecture.di.modules.ApplicationModule;


public class DaggerApplication extends Application {

    private static DaggerApplication instance;

    public static DaggerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule
                (getApplicationContext())).build();
    }

    ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
