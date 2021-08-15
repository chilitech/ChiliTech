package com.chilitech.app.architecture.di.component;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.chilitech.app.architecture.di.modules.ActivityModule;
import com.chilitech.app.architecture.di.scopes.PerActivity;
import com.chilitech.app.architecture.ui.activity.LoginActivity;
import com.chilitech.app.architecture.ui.activity.MainActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    AppCompatActivity activity();

    void inject(Activity activity);
}
