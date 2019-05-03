package com.example.lgw.mqtt1.remote;

import android.content.Context;


public class RemoteContext {
    private static RemoteContext instance;

    private Context applicationContext;

    public static RemoteContext getInstance() {
        if (instance == null){
            throw new RuntimeException(RemoteContext.class.getSimpleName() + "has not been initialized!");
        }
        return instance;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public RemoteContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 全局信息 只能调用一次
     */
    public static void init(Context applicationContext) {
        if (instance != null) {
            throw new RuntimeException(RemoteContext.class.getSimpleName() + " can not be initialized multiple times!");
        }
        instance = new RemoteContext(applicationContext);
    }

    public static boolean isInitialized() {
        return (instance != null);
    }
}
