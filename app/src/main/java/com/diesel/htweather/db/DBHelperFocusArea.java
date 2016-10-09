package com.diesel.htweather.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.diesel.htweather.db.model.FocusArea;
import com.diesel.htweather.db.table.FocusAreaTable;
import com.diesel.htweather.db.util.DbUtils;

import java.util.ArrayList;
import java.util.List;

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

public final class DBHelperFocusArea {

    public static List<FocusArea> getFocusArea() {
        List<FocusArea> focusAreas = new ArrayList<>();

        SQLiteDatabase db = DBHelper.getReadableDb();
        String sql = "select * from " + FocusAreaTable.TABLE_NAME;

        Cursor cursor = db.rawQuery(sql, null);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                final  FocusArea area = new FocusArea();
                area.provinceCode = cursor.getString(cursor.getColumnIndex(FocusAreaTable.PROVINCE_CODE));
                area.provinceName = cursor.getString(cursor.getColumnIndex(FocusAreaTable.PROVINCE_NAME));
                area.cityCode = cursor.getString(cursor.getColumnIndex(FocusAreaTable.CITY_CODE));
                area.cityName = cursor.getString(cursor.getColumnIndex(FocusAreaTable.CITY_NAME));
                area.countryCode = cursor.getString(cursor.getColumnIndex(FocusAreaTable.COUNTRY_CODE));
                area.countryName = cursor.getString(cursor.getColumnIndex(FocusAreaTable.COUNTRY_NAME));
                area.data = cursor.getString(cursor.getColumnIndex(FocusAreaTable.DATA));
                Log.d("DBHelperFocusArea", "getFocusArea() " + area);
                focusAreas.add(area);
            }
            cursor.close();
        }

        return focusAreas;
    }

    public static void insertArea(FocusArea area) {
        SQLiteDatabase db = DBHelper.getWritableDb();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FocusAreaTable.PROVINCE_CODE, area.provinceCode);
        contentValues.put(FocusAreaTable.PROVINCE_NAME, area.provinceName);
        contentValues.put(FocusAreaTable.CITY_CODE, area.cityCode);
        contentValues.put(FocusAreaTable.CITY_NAME, area.cityName);
        contentValues.put(FocusAreaTable.COUNTRY_CODE, area.countryCode);
        contentValues.put(FocusAreaTable.COUNTRY_NAME, area.countryName);
        contentValues.put(FocusAreaTable.DATA, area.data);
        boolean isSuccess = DbUtils.insert(db, FocusAreaTable.TABLE_NAME, contentValues);
        Log.d("DBHelperFocusArea", "insertArea() insert " + area.countryName + " " + isSuccess);
    }

    public static void deleteArea(FocusArea area) {
        SQLiteDatabase db = DBHelper.getWritableDb();
        String whereClause = FocusAreaTable.COUNTRY_CODE + " = ? and " + FocusAreaTable.COUNTRY_NAME + " = ? ";
        String[] whereArgs = {area.countryCode, area.countryName};
        db.delete(FocusAreaTable.TABLE_NAME, whereClause, whereArgs);
    }

}
