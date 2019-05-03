package com.example.lgw.mqtt1.remote;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.lgw.mqtt1.gen.DaoMaster;
import com.example.lgw.mqtt1.gen.DaoSession;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;


public class RemoteApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        if (!RemoteContext.isInitialized()) {
            RemoteContext.init(getApplicationContext());
        }
        ZXingLibrary.initDisplayOpinion(this);
        initGreenDao();
    }
    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "aserbao.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
