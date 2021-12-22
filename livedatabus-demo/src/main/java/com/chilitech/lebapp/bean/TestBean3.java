package com.chilitech.lebapp.bean;

import com.chilitech.livedatabus.ipc.annotation.IpcConfig;
import com.chilitech.livedatabus.processor.gson.GsonProcessor;

@IpcConfig(processor = GsonProcessor.class)
public class TestBean3 {
    public String content;

    @Override
    public String toString() {
        return content;
    }
}
