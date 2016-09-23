package com.diesel.htweather.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.diesel.htweather.base.HTApplication;
import com.diesel.htweather.db.AreaDao;
import com.diesel.htweather.model.RegionObject;
import com.diesel.htweather.response.AreaResJo;
import com.diesel.htweather.response.CheckVersionResJo;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.webapi.AreaWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

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
//        Log.i("AreaDao", "----------- 开始获取地区数据 --------------");
//        long curr = System.currentTimeMillis();
//        ArrayList<RegionObject> provinces = mAreaDao.loadLevel01Data();
//        ArrayList<ArrayList<RegionObject>> cities = new ArrayList<>();
//        ArrayList<ArrayList<ArrayList<RegionObject>>> countries = new ArrayList<>();
//
//        for (RegionObject province : provinces) {
//            ArrayList<RegionObject> city = mAreaDao.loadLevel02Data(province);
//            cities.add(city);
//
//            ArrayList<ArrayList<RegionObject>> country = new ArrayList<>();
//            for (RegionObject c : city) {
//                country.add(mAreaDao.loadLevel03Data(c));
//            }
//            countries.add(country);
//        }
//        HTApplication.provinces = provinces;
//        HTApplication.cities = cities;
//        HTApplication.countries = countries;
//        Log.i("AreaDao", "----------- 获取地区数据结束 --------------cost time:"
//                + (System.currentTimeMillis() - curr) + " ms");

        AreaWebService.getInstance().getAllArea(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("AreaDao", "onError() " + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("AreaDao", "onResponse() " + response);
                try {
                    AreaResJo resJo = FastJsonUtils.getSingleBean(response, AreaResJo.class);
                    if (null != resJo && resJo.status == 0 && null != resJo.data && !resJo.data.isEmpty()) {
                        ArrayList<AreaResJo.ProvinceEntity> provinces = new ArrayList<>();
                        ArrayList<ArrayList<AreaResJo.ProvinceEntity.CityEntity>> cities = new ArrayList<>();
                        ArrayList<ArrayList<ArrayList<AreaResJo.ProvinceEntity.CityEntity.CountryEntity>>> countries = new ArrayList<>();
                        for (int i = 0; i < resJo.data.size(); i ++) {
                            AreaResJo.ProvinceEntity province = resJo.data.get(i);
                            // city
                            ArrayList<AreaResJo.ProvinceEntity.CityEntity> city = new ArrayList<>();
                            ArrayList<ArrayList<AreaResJo.ProvinceEntity.CityEntity.CountryEntity>> country1 = new ArrayList<>();
                            for (int j = 0; j < province.faCityList.size(); j ++) {
                                AreaResJo.ProvinceEntity.CityEntity c = province.faCityList.get(j);
                                city.add(c);
                                // country
                                ArrayList<AreaResJo.ProvinceEntity.CityEntity.CountryEntity> country2 = new ArrayList<>();
                                for (int k = 0; k < c.faAreaList.size(); k ++) {
                                    country2.add(c.faAreaList.get(k));
                                }
                                country1.add(country2);
                            }
                            provinces.add(province);
                            cities.add(city);
                            countries.add(country1);
                        }
                        HTApplication.provinces = provinces;
                        HTApplication.cities = cities;
                        HTApplication.countries = countries;
                    }
                } catch (Exception e) {
                    Log.e("AreaDao", "checkVersion#onResponse() " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAreaDao.release();
        Log.d("AreaDao", "onDestroy()...............");
    }
}
