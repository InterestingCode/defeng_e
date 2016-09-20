package com.diesel.htweather.webapi;

import com.diesel.htweather.constant.Api;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/9/14
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */
public class AreaWebService extends WebService {

    private static AreaWebService sInstance;

    public static AreaWebService getInstance() {
        if (sInstance == null) {
            sInstance = new AreaWebService();
        }
        return sInstance;
    }

    private AreaWebService() {
        super();
    }

    public void getOpenArea(Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_OPEN_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    public void getHotArea(Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_HOT_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    public void getAreaByName(String areaName, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_HOT_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("areaName", areaName)
                .build()
                .execute(callback);
    }

    public void getAreaById(String areaId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_HOT_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("areaId", areaId)
                .build()
                .execute(callback);
    }

}
