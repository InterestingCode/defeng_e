package com.diesel.htweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.diesel.htweather.db.table.FocusAreaTable;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/8/12
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "defeng_e.db";

    private final static int DATABASE_VERSION = 1;

    private static final String TAG = DBHelper.class.getSimpleName();

    //================================
    // 初始化&单例
    //================================
    private static volatile Context sContext;

    private static volatile DBHelper sInstance;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized void init(Context context) {
        if (sContext == null) {
            sContext = context.getApplicationContext();
        }
    }

    private static synchronized DBHelper getInstance() {
        if (sContext == null) {
            throw new RuntimeException("必须先调用init方法");
        }

        if (sInstance == null) {
            sInstance = new DBHelper(sContext);
        }

        return sInstance;
    }

    public static SQLiteDatabase getWritableDb() {
        try {
            return getInstance().getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SQLiteDatabase getReadableDb() {
        try {
            return getInstance().getReadableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        FocusAreaTable.createTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
