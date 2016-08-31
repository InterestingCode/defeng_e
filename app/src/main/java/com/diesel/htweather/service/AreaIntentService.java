package com.diesel.htweather.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.diesel.htweather.base.HTApplication;
import com.diesel.htweather.db.AreaDao;
import com.diesel.htweather.model.RegionObject;

import java.util.ArrayList;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/28
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class AreaIntentService extends IntentService {

    private AreaDao mAreaDao;

    public AreaIntentService() {
        super("AreaIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAreaDao = new AreaDao(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("AreaDao", "----------- 开始获取地区数据 --------------");
        long curr = System.currentTimeMillis();
        ArrayList<RegionObject> provinces = mAreaDao.loadLevel01Data();
        ArrayList<ArrayList<RegionObject>> cities = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<RegionObject>>> countries = new ArrayList<>();

        for (RegionObject province : provinces) {
            ArrayList<RegionObject> city = mAreaDao.loadLevel02Data(province);
            cities.add(city);

            ArrayList<ArrayList<RegionObject>> country = new ArrayList<>();
            for (RegionObject c : city) {
                country.add(mAreaDao.loadLevel03Data(c));
            }
            countries.add(country);
        }
        HTApplication.provinces = provinces;
        HTApplication.cities = cities;
        HTApplication.countries = countries;
        Log.i("AreaDao", "----------- 获取地区数据结束 --------------cost time:"
                + (System.currentTimeMillis() - curr) + " ms");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAreaDao.release();
    }
}
