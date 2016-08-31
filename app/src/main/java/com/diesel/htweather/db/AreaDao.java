/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with FEINNO.
 */
package com.diesel.htweather.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.diesel.htweather.model.RegionObject;

import java.util.ArrayList;

/**
 * 区域数据dao，用于操作区域SQLite数据库
 */
public class AreaDao {

    private static final String TAG = "AreaDao";

    private SQLiteDatabase mRegionDb; // 地区数据库

    public AreaDao(Context context) {
        mRegionDb = RegionDBHelper.getInstance(context);
    }

    /**
     * Title:释放资源
     * <p>
     * Description:
     * </p>
     */
    public void release() {
        if (mRegionDb != null && mRegionDb.isOpen()) {
            mRegionDb.close();
        }
    }

    /**
     * 使用名称或全名查询1级编码
     */
    public RegionObject queryLevel01RegionId(String regionName) {
        if (TextUtils.isEmpty(regionName)) {
            return null;
        }
        Cursor cursor = null;
        String selection = RegionDBHelper.COL_NAME + "=? or " + RegionDBHelper.COL_FULL_NAME + "=?";
        cursor = mRegionDb.query(RegionDBHelper.TABLE_NAME, null, selection,
                new String[]{regionName, regionName}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String parentId = cursor.getString(cursor.getColumnIndex(RegionDBHelper.COL_PARENT_ID));
            RegionObject obj = new RegionObject();
            obj.id = cursor.getString(cursor.getColumnIndex(RegionDBHelper.COL_ID));
            obj.name = cursor.getString(cursor.getColumnIndex(RegionDBHelper.COL_NAME));
            obj.fullName = cursor.getString(cursor.getColumnIndex(RegionDBHelper.COL_FULL_NAME));
            obj.showName = cursor.getString(cursor.getColumnIndex(RegionDBHelper.COL_SHOW_NAME));
            cursor.close();
            if (TextUtils.isEmpty(parentId)) { // 父级id为空的表示一级地区
                return obj;
            } else {
                return queryLevel01RegionId(queryNameById(parentId));
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    public String queryNameById(String id) {
        Cursor cursor = null;
        String selection = RegionDBHelper.COL_ID + "=?";
        cursor = mRegionDb
                .query(RegionDBHelper.TABLE_NAME, null, selection, new String[]{id}, null, null,
                        null);
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(RegionDBHelper.COL_NAME));
            cursor.close();
            return name;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    /**
     * 加载第一级(省份列表)数据
     */
    public ArrayList<RegionObject> loadLevel01Data() {
        ArrayList<RegionObject> listData = new ArrayList<RegionObject>();
        Cursor cur = null;

        try {
            String selection = RegionDBHelper.COL_PARENT_ID + "=''"; // 父级id为空的表示一级地区
            String orderBy = RegionDBHelper.COL_SPELL + " asc";
            cur = mRegionDb
                    .query(RegionDBHelper.TABLE_NAME, null, selection, null, null, null, orderBy);
            while (cur.moveToNext()) {
                RegionObject obj = new RegionObject();
                obj.id = cur.getString(cur.getColumnIndex(RegionDBHelper.COL_ID));
                obj.name = cur.getString(cur.getColumnIndex(RegionDBHelper.COL_NAME));
                obj.fullName = cur.getString(cur.getColumnIndex(RegionDBHelper.COL_FULL_NAME));
                obj.showName = cur.getString(cur.getColumnIndex(RegionDBHelper.COL_SHOW_NAME));
                listData.add(obj);
                Log.v(TAG, "---省--- " + obj.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cur != null) {
                cur.close();
            }
        }
        return listData;
    }

    /**
     * 加载第二级(城市列表)数据
     *
     * @param cityObj 父级地区
     */
    public ArrayList<RegionObject> loadLevel02Data(RegionObject cityObj) {
        ArrayList<RegionObject> listData = new ArrayList<RegionObject>();
        Cursor cur = null;

        try {
            String selection = RegionDBHelper.COL_PARENT_ID + "='" + cityObj.id + "'";
            String orderBy = RegionDBHelper.COL_IS_CAPITAL + "," + RegionDBHelper.COL_SPELL
                    + " asc";
            cur = mRegionDb
                    .query(RegionDBHelper.TABLE_NAME, null, selection, null, null, null, orderBy);
            while (cur.moveToNext()) {
                RegionObject obj = new RegionObject();
                obj.id = cur.getString(cur.getColumnIndex(RegionDBHelper.COL_ID));
                obj.name = cur.getString(cur.getColumnIndex(RegionDBHelper.COL_NAME));
                obj.fullName = cur.getString(cur.getColumnIndex(RegionDBHelper.COL_FULL_NAME));
                obj.showName = cur.getString(cur.getColumnIndex(RegionDBHelper.COL_SHOW_NAME));
                listData.add(obj);
                Log.v(TAG, "---市--- " + obj.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cur != null) {
                cur.close();
            }
        }

        return listData;
    }

    /**
     * 加载第三级(区县列表)数据
     *
     * @param cityObj 父级地区
     */
    public ArrayList<RegionObject> loadLevel03Data(RegionObject cityObj) {
        ArrayList<RegionObject> listData = new ArrayList<RegionObject>();

        // 三级地区第一个数据是父级（但需要排除 海南省、湖北省、河南、新疆 的二级数据）
        if (!"3890".equals(cityObj.id) && !"4190".equals(cityObj.id) && !"3718".equals(cityObj.id)
                && !"5290".equals(cityObj.id)) {
            cityObj.parentName = "";
            listData.add(cityObj);
        }

        Cursor cur = null;
        try {
            String selection = RegionDBHelper.COL_PARENT_ID + "='" + cityObj.id + "'";
            String orderBy = RegionDBHelper.COL_SPELL + " asc";
            cur = mRegionDb
                    .query(RegionDBHelper.TABLE_NAME, null, selection, null, null, null, orderBy);
            while (cur.moveToNext()) {
                RegionObject obj = new RegionObject();
                obj.id = cur.getString(cur.getColumnIndex(RegionDBHelper.COL_ID));
                obj.name = cur.getString(cur.getColumnIndex(RegionDBHelper.COL_NAME));
                obj.showName = cur.getString(cur.getColumnIndex(RegionDBHelper.COL_SHOW_NAME));
                obj.parentName = cityObj.fullName;
                listData.add(obj);
                Log.v(TAG, "---区县--- " + obj.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cur != null) {
                cur.close();
            }
        }

        return listData;
    }
}
