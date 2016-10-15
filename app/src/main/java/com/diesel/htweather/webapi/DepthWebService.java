package com.diesel.htweather.webapi;

import com.diesel.htweather.constant.Api;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Created by zhoujiangsen on 2016/10/14.
 */

public class DepthWebService extends WebService {

    private static DepthWebService sInstance;

    public static DepthWebService getInstance() {
        if (sInstance == null) {
            sInstance = new DepthWebService();
        }
        return sInstance;
    }

    private DepthWebService() {
        super();
    }

    /**
     * 获取在线咨询列表
     *
     * @param contentType 1：所有数据； 2：我的咨询
     * @param callback    回调
     */
    public void getOnlineConsultationMessages(String contentType, Callback callback) {
        OkHttpUtils
                .post()
                .url(Api.GET_ONLINE_CONSULTATION_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("contentType", contentType)
                .addParams("rows", "20")
                .build()
                .execute(callback);
    }


}
