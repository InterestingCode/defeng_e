/*
 * Copyright (C) 2012 重庆新媒农信科技有限公司
 * 版权所有
 *
 * 功能描述：地区数据库帮助类
 *
 *
 * 创建标识：duxl 20130723
 */
package com.diesel.htweather.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.location.BDLocation;

/**
 * 地区数据库帮助类
 */
public class RegionDBHelper {

    private static final String TAG = "AreaDao";

    private Context mContext;

    private static final String mAssetDbFileName = "area.db";

    private static final String mDbFileName = "area_v1.db";// 更新区域表后更改名字

    private static final long mDbFileLength = 252928; // 数据库文件大小（用作校验：如果修改了数据库，被忘了更改这个值）

    private MyDBHelper myDBHelper;

    public static final String TABLE_NAME = "region"; // 表名

    public static final String COL_ID = "id";

    public static final String COL_PARENT_ID = "parentId";

    public static final String COL_NAME = "name";

    public static final String COL_FULL_NAME = "fullName";

    public static final String COL_IS_CAPITAL = "isCapital"; // 1省会、2其它

    public static final String COL_CODE = "code";

    public static final String COL_SPELL = "spell";

    public static final String COL_SHORTSPELL = "shortSpell";

    public static final String COL_SHOW_NAME = "showName";

    public static final String COL_X_NAME = "xName";

    /**
     * 私有构造函数
     */
    private RegionDBHelper(Context c) {
        mContext = c;
        initDbFile(c);
        myDBHelper = new MyDBHelper(mContext);
    }

    /**
     * 获取数据库实例
     */
    public static SQLiteDatabase getInstance(Context c) {
        return new RegionDBHelper(c).myDBHelper.getReadableDatabase();
    }

    /**
     * 初始化数据库文件 如果databases目录不存在地区数据库文件， 将会把asset目录下的数据库文件拷贝到databases目录下
     */
    public static void initDbFile(Context context) {
        Log.i(TAG, "----------- 开始初始化数据库文件 --------------");
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            String pkgName = context.getPackageName();
            File dbFile = new File("data/data/" + pkgName + "/databases/"
                    + mDbFileName);

            // 过滤旧的地区数据表文件
            File[] oldFiles = dbFile.getParentFile().listFiles(
                    new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (mDbFileName.equals(filename)
                                    || DBHelper.DATABASE_NAME.equals(filename)
                                    || !filename.startsWith("area")) {
                                return false;
                            }
                            return true;
                        }
                    });
            if (null != oldFiles) {
                // 删除旧的地区数据库文件
                for (File f : oldFiles) {
                    f.delete();
                }
            }

            if (!dbFile.exists() || dbFile.length() < mDbFileLength) {
                File dir = new File("data/data/" + pkgName + "/databases");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                AssetManager am = context.getAssets();
                // 得到数据库的输入流
                is = am.open(mAssetDbFileName);
                fos = new FileOutputStream(dbFile);
                byte[] data = new byte[1024];
                int len;
                while ((len = is.read(data)) != -1) {
                    fos.write(data, 0, len);
                }
                fos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Log.i(TAG, "----------- 初始化数据库文件结束 --------------");
        }
    }

    private static class MyDBHelper extends SQLiteOpenHelper {

        /**
         * 构造方法
         */
        public MyDBHelper(Context c) {
            // 调用父类的构造方法来创建数据库
            super(c, mDbFileName, null, 2);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    /**
     * 根据定位获取地区id
     */
    public static String getRegionIdByLoction(Context c, BDLocation location) {

        String id = null;

        // sql = select * from region where fullName like '江北%' and parentId =
        // (select id from region where fullName like '宁波%' and length(id) = 4)
        if (location != null) {
            String district = location.getDistrict();
            if (!TextUtils.isEmpty(district) && district.length() > 2) {
                district = district.substring(0, 2);
            }

            String city = location.getCity();
            if (!TextUtils.isEmpty(city) && city.length() > 2) {
                city = city.substring(0, 2);
            }

            if (!TextUtils.isEmpty(district) && !TextUtils.isEmpty(city)) {

                initDbFile(c);

                MyDBHelper dbHelper = new MyDBHelper(c);
                SQLiteDatabase db = null;
                Cursor cur = null;

                try {
                    String table = "region where fullName like '"
                            + district
                            + "%' and parentId = (select id from region where fullName like '"
                            + city + "%' and length(id) = 4)";
                    db = dbHelper.getReadableDatabase();
                    cur = db.query(table, null, null, null, null, null, null);
                    if (cur != null && cur.moveToFirst()) {
                        id = cur.getString(cur.getColumnIndex(COL_ID));
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    if (cur != null) {
                        cur.close();
                    }

                    if (db != null && db.isOpen()) {
                        db.close();
                    }

                    if (dbHelper != null) {
                        dbHelper.close();
                    }
                }
            }
        }
        return id;
    }

    /**
     * 根据定位获取地区showName
     */
    public static String getRegionShowNameByLoction(Context c,
            BDLocation location) {

        String showName = null;

        // sql = select * from region where fullName like '江北%' and parentId =
        // (select id from region where fullName like '宁波%' and length(id) = 4)
        if (location != null) {
            String district = location.getDistrict();
            if (!TextUtils.isEmpty(district) && district.length() > 2) {
                district = district.substring(0, 2);
            }

            String city = location.getCity();
            if (!TextUtils.isEmpty(city) && city.length() > 2) {
                city = city.substring(0, 2);
            }

            if (!TextUtils.isEmpty(district) && !TextUtils.isEmpty(city)) {

                initDbFile(c);

                MyDBHelper dbHelper = new MyDBHelper(c);
                SQLiteDatabase db = null;
                Cursor cur = null;

                try {
                    String table = "region where fullName like '"
                            + district
                            + "%' and parentId = (select id from region where fullName like '"
                            + city + "%' and length(id) = 4)";
                    db = dbHelper.getReadableDatabase();
                    cur = db.query(table, null, null, null, null, null, null);
                    if (cur != null && cur.moveToFirst()) {
                        showName = cur.getString(cur
                                .getColumnIndex(COL_SHOW_NAME));
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    if (cur != null) {
                        cur.close();
                    }

                    if (db != null && db.isOpen()) {
                        db.close();
                    }

                    if (dbHelper != null) {
                        dbHelper.close();
                    }
                }
            }
        }
        return showName;
    }

}
