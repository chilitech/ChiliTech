package com.chilitech.app.architecture.di.component;


import com.chilitech.app.architecture.di.modules.TaskModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {TaskModule.class})
public interface TaskComponent {

//    AlertGetRemoteTask alertGetRemoteTask();
}
