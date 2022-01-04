package com.chilitech.livedatabus.ipc.annotation;

import com.chilitech.livedatabus.ipc.core.Processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IpcConfig {

    Class<? extends Processor> processor();
}
