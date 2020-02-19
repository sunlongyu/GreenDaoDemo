package com.example.greendaodemo;

import android.app.Application;

import com.example.greendaodemo.Model.DaoMaster;
import com.example.greendaodemo.Model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class MyApplication extends Application {

    public static DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化db
        initDb();
    }

    private void initDb() {
        //获取SQLiteOpenHelper对象devOpenHelper
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "hua.db");
        //取SQLiteDatabase,不加密的时候用这个
        //SQLiteDatabase sqLiteDatabase = devOpenHelper.getWritableDatabase();
        Database database = devOpenHelper.getEncryptedReadableDb("huazige");
        //生成DaoMaster
        DaoMaster daoMaster = new DaoMaster(database);
        //创建会话
        session = daoMaster.newSession();
    }
}
