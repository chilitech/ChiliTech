package com.chilitech.app.architecture.di.modules;

import androidx.appcompat.app.AppCompatActivity;

import com.chilitech.app.architecture.di.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity provideActivity() {
        return this.activity;
    }
}
