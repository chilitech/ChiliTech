package com.chilitech.app.architecture.di.component;

import android.content.Context;

import com.chilitech.app.architecture.di.modules.ApplicationModule;
import com.chilitech.base.BaseActivity;
import com.chilitech.base.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context context();

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);
}
