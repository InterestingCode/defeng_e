package com.diesel.htweather.webapi;

import com.diesel.htweather.constant.Api;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

public class FarmingWebService extends WebService {

    private static FarmingWebService sInstance;

    public static FarmingWebService getInstance() {
        if (sInstance == null) {
            sInstance = new FarmingWebService();
        }
        return sInstance;
    }

    private FarmingWebService() {
        super();
    }

    public void getFarmingPolicyList(int areaId, int page, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.FARMING_POLICY_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("arId", String.valueOf(areaId))
                .addParams("page", String.valueOf(page))
                .addParams("rows", "20")
                .build()
                .execute(callback);
    }

    public void getFarmingPolicyDetail(int contentId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.FARMING_POLICY_DETAIL_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("newsId", String.valueOf(contentId))
                .build()
                .execute(callback);
    }

    public void getFarmingInfoList(int areaId, int page, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.FARMING_INFO_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("arId", String.valueOf(areaId))
                .addParams("page", String.valueOf(page))
                .addParams("rows", "20")
                .build()
                .execute(callback);
    }

    public void getFarmingInfoDetail(int contentId, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.FARMING_INFO_DETAIL_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("newsId", String.valueOf(contentId))
                .build()
                .execute(callback);
    }

}
