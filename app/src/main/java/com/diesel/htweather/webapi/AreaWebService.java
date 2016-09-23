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

    /**
     * 所有区域信息
     */
    public void getAllArea(Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 已开通服务区域
     */
    public void getOpenArea(Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_OPEN_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 热门推荐区域
     */
    public void getHotArea(Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_HOT_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    public void getAreaByName(@NonNull String areaName, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_HOT_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("areaName", areaName)
                .build()
                .execute(callback);
    }

    public void getAreaById(@NonNull String areaId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_HOT_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("areaId", areaId)
                .build()
                .execute(callback);
    }

    /**
     * 获取用户已关注区域
     */
    public void getFocusArea(Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_FOCUS_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 新增关注区域
     */
    public void focusArea(@NonNull String areaId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.ADD_FOCUS_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("arId", areaId)
                .build()
                .execute(callback);
    }

    /**
     * 取消关注区域
     */
    public void cancelFocusArea(@NonNull String areaId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.CANCEL_FOCUS_AREA_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("arId", areaId)
                .build()
                .execute(callback);
    }

}
