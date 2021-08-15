package com.chilitech.app.architecture.di.component;


import com.chilitech.app.architecture.di.modules.PresenterModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {PresenterModule.class})
public interface PresenterComponent {

//    WeatherInfoGetTask weatherInfoGetTask();

//    void inject(WeatherPresenter presenter);
//
//    void inject(CityPresenter presenter);
//
//    void inject(CityEditPresenter presenter);
//
//    void inject(AlertPresenter presenter);
}
