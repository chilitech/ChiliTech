package com.chilitech.app.architecture.di.component;

import androidx.fragment.app.Fragment;

import com.chilitech.app.architecture.di.modules.ActivityModule;
import com.chilitech.app.architecture.di.modules.FragmentModule;
import com.chilitech.app.architecture.di.scopes.PerActivity;
import com.chilitech.app.architecture.ui.fragment.SimpleFragment;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, FragmentModule.class})
public interface FragmentComponent extends ActivityComponent{

    Fragment fragment();

    void inject(SimpleFragment fragment);
}
