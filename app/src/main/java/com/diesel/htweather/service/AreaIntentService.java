package com.diesel.htweather.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.diesel.htweather.base.DFApplication;
import com.diesel.htweather.response.AreaResJO;
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

    public AreaIntentService() {
        super("AreaIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("AreaDao", "----------- 开始获取地区数据 --------------");

        AreaWebService.getInstance().getAllArea(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("AreaIntentService", "onError() " + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("AreaIntentService", "onResponse() " + response);
                try {
                    AreaResJO resJo = FastJsonUtils.getSingleBean(response, AreaResJO.class);
                    if (null != resJo && resJo.status == 0 && null != resJo.data && !resJo.data.isEmpty()) {
                        ArrayList<AreaResJO.ProvinceEntity> provinces = new ArrayList<>();
                        ArrayList<ArrayList<AreaResJO.ProvinceEntity.CityEntity>> cities = new ArrayList<>();
                        ArrayList<ArrayList<ArrayList<AreaResJO.ProvinceEntity.CityEntity.CountryEntity>>> countries = new ArrayList<>();
                        for (int i = 0; i < resJo.data.size(); i ++) {
                            AreaResJO.ProvinceEntity province = resJo.data.get(i);
                            // city
                            ArrayList<AreaResJO.ProvinceEntity.CityEntity> city = new ArrayList<>();
                            ArrayList<ArrayList<AreaResJO.ProvinceEntity.CityEntity.CountryEntity>> country1 = new ArrayList<>();
                            for (int j = 0; j < province.faCityList.size(); j ++) {
                                AreaResJO.ProvinceEntity.CityEntity c = province.faCityList.get(j);
                                city.add(c);
                                // country
                                ArrayList<AreaResJO.ProvinceEntity.CityEntity.CountryEntity> country2 = new ArrayList<>();
                                for (int k = 0; k < c.faAreaList.size(); k ++) {
                                    country2.add(c.faAreaList.get(k));
                                }
                                country1.add(country2);
                            }
                            provinces.add(province);
                            cities.add(city);
                            countries.add(country1);
                        }
                        DFApplication.provinces = provinces;
                        DFApplication.cities = cities;
                        DFApplication.countries = countries;
                    }
                } catch (Exception e) {
                    Log.e("AreaIntentService", "#Exception# " + e.getMessage());
                }
            }
        });
    }

}
