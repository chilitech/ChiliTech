package com.chilitech.base.manager.database;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class DBTaskManager {

    private static ExecutorService cachedExecutor = Executors.newCachedThreadPool();
    private static ExecutorService singleExecutor = Executors.newSingleThreadExecutor();

    private static class SingletonHolder {
        private static DBTaskManager instance = new DBTaskManager();
    }

    public static DBTaskManager getInstance() {
        return SingletonHolder.instance;
    }

    private DBTaskManager() {
    }

    public void getData(Runnable runnable) {
        cachedExecutor.execute(runnable);
    }

    public void putData(Runnable runnable) {
        singleExecutor.execute(runnable);
    }
}
