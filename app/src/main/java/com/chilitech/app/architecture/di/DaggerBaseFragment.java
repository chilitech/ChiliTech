package com.chilitech.app.architecture.di;

import androidx.appcompat.app.AppCompatActivity;

import com.chilitech.app.architecture.di.component.ApplicationComponent;
import com.chilitech.app.architecture.di.component.DaggerFragmentComponent;
import com.chilitech.app.architecture.di.component.FragmentComponent;
import com.chilitech.app.architecture.di.modules.ActivityModule;
import com.chilitech.app.architecture.di.modules.FragmentModule;
import com.chilitech.base.BaseFragment;
import com.chilitech.base.wrapper.DiWrapper;



public abstract class DaggerBaseFragment extends BaseFragment implements DiWrapper {

    private FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule((AppCompatActivity) getActivity());
    }

    private ApplicationComponent getApplicationComponent() {
        return ((DaggerApplication) getContext().getApplicationContext()).getApplicationComponent();
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder().applicationComponent(getApplicationComponent()).activityModule
                (getActivityModule()).fragmentModule(getFragmentModule()).build();
    }

    @Override
    protected void onFragmentCreate() {
//        super.onFragmentCreate();
        onInject();
    }

    @Override
    public void onInject() {
        getApplicationComponent().inject(this);
    }
}
