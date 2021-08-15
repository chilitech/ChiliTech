package com.chilitech.app.architecture.di.modules;

import androidx.fragment.app.Fragment;

import com.chilitech.app.architecture.di.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class FragmentModule {

    private final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerActivity
    Fragment provideFragment() {
        return fragment;
    }
}
