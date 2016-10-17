package com.diesel.htweather.webapi;

import com.diesel.htweather.constant.Api;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
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

    /**
     * 获取网络图片
     *
     * @param imagePath 文件路径
     * @param callback
     */
    public void getNetworkBitmap(String imagePath, BitmapCallback callback) {
        OkHttpUtils
                .get()
                .url(imagePath)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 在线咨询发布信息
     *
     * @param imagePath 图片地址；多张图片分号分割
     */
    public void publishProblem(String content, String imagePath, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.RELEASE_ONLINE_CONSULTATION_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("content", content)
                .addParams("faTitleimg", imagePath)
                .build()
                .execute(callback);
    }


}
