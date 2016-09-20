package com.diesel.htweather.webapi;

import android.support.annotation.NonNull;

import com.diesel.htweather.constant.Api;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/9/13
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */
public class PlantWebService extends WebService {

    private static PlantWebService sInstance;

    public static PlantWebService getInstance() {
        if (sInstance == null) {
            sInstance = new PlantWebService();
        }
        return sInstance;
    }

    private PlantWebService() {
        super();
    }

    public void getPlantCategory(Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_PLANT_CATEGORY_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    public void getPlantList(String areaId, String categoryId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_PLANT_CATEGORY_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("arId", areaId)
                .addParams("cropParentId", categoryId)
                .build()
                .execute(callback);
    }

    /**
     * 关注农作物
     */
    public void focusPlant(String areaId, String categoryId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_PLANT_CATEGORY_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("uaId", areaId)
                .addParams("cropIds", categoryId)
                .build()
                .execute(callback);
    }

}
