package com.diesel.htweather.db.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/9/26
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public final class FocusAreaTable {

    public static final String TABLE_NAME = "focus_area";

    public static final String ID = "_id";
    public static final String PROVINCE_CODE = "province_code";
    public static final String CITY_CODE = "city_code";
    public static final String COUNTRY_CODE = "country_code";
    public static final String AREA_NAME = "area_name";
    public static final String COUNTRY_NAME = "country_name";
    public static final String DATA = "data";

    public static void createTable(SQLiteDatabase db) {
        String createString = " ("
                + ID + " integer primary key autoincrement, "
                + PROVINCE_CODE + " text not null unique, "
                + CITY_CODE + " text, "
                + COUNTRY_CODE + " text, "
                + AREA_NAME + " text, "
                + COUNTRY_NAME + " text, "
                + DATA + " text"
                + ");";

        db.execSQL("create table if not exists " + TABLE_NAME + createString);
    }

}
