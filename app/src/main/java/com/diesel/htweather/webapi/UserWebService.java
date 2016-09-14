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
public class UserWebService extends WebService {

    private static UserWebService sInstance;

    public static UserWebService getInstance() {
        if (sInstance == null) {
            sInstance = new UserWebService();
        }
        return sInstance;
    }

    private UserWebService() {
        super();
    }

    /**
     * @param smsType 1：注册账号验证码发送
     *                2：快速登录验证码发送
     *                3：找回密码验证码发送
     */
    public void getAuthCode(int smsType, @NonNull String mobile, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_AUTH_CODE_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("mobile", mobile)
                .addParams("smsType", String.valueOf(smsType))
                .build()
                .execute(callback);
    }

    public void verifyAuthCode(@NonNull String mobile, @NonNull String authCode,
            Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.RESET_PASSWORD_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("mobile", mobile)
                .addParams("smsCode", mobile)
                .build()
                .execute(callback);
    }

    public void resetPassword(@NonNull String password, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.RESET_PASSWORD_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("newPwd", password)
                .build()
                .execute(callback);
    }

}
