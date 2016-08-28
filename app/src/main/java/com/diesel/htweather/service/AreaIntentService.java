package com.diesel.htweather.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.diesel.htweather.db.AreaDao;
import com.diesel.htweather.model.RegionObject;

import java.util.ArrayList;
import java.util.List;

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
        List<RegionObject> provinces = mAreaDao.loadLevel01Data();
        List<List<RegionObject>> cities = new ArrayList<>();
        List<List<List<RegionObject>>> countries = new ArrayList<>();

        for (RegionObject province :provinces) {
            List<RegionObject> city = mAreaDao.loadLevel02Data(province);
            cities.add(city);

            List<List<RegionObject>> country = new ArrayList<>();
            for (RegionObject c : city) {
                country.add(mAreaDao.loadLevel03Data(c));
            }
            countries.add(country);
        }
        Log.i("AreaDao", "----------- 获取地区数据结束 --------------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAreaDao.release();
    }
}
