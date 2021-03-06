package com.chilitech.livedatabus.logger;

import java.util.logging.Level;


public interface Logger {

    void log(Level level, String msg);

    void log(Level level, String msg, Throwable th);
}
