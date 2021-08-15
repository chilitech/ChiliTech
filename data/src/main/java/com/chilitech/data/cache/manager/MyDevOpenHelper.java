package com.chilitech.data.cache.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chilitech.data.cache.database.dao.DaoMaster;
import com.chilitech.data.cache.database.dao.UserModelDao;

import org.greenrobot.greendao.database.Database;


public class MyDevOpenHelper extends DaoMaster.DevOpenHelper {

    public MyDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by migrating all tables data");
        MigrationHelper.getInstance().migrate(db, UserModelDao.class);
    }
}
