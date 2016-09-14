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
                .url(Api.GET_AUTH_CODE_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

}
