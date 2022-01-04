package com.chilitech.livedatabus.core;


public final class Console {

    private Console() {
    }

    /**
     * 获取控制台信息
     *
     * @return 调试信息
     */
    public static String getInfo() {
        return LiveEventBusCore.get().console.getConsoleInfo();
    }
}
