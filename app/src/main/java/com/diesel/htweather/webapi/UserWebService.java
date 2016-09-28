package com.diesel.htweather.webapi;

import android.support.annotation.NonNull;

import com.diesel.htweather.constant.Api;
import com.diesel.htweather.model.UserInfoBean;
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
                .url(Api.VERIFY_AUTH_CODE_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("mobile", mobile)
                .addParams("smsCode", authCode)
                .build()
                .execute(callback);
    }

    /**
     * 登录
     */
    public void login(@NonNull String userName, @NonNull String password, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.LOGIN_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("username", userName)
                .addParams("pwd", password)
                .build()
                .execute(callback);
    }

    /**
     * 注册
     */
    public void register(@NonNull String password, @NonNull String mobile, @NonNull String authCode,
            Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.REGISTER_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("mobile", mobile)
                .addParams("smsCode", authCode)
                .addParams("pwd", password)
                .build()
                .execute(callback);
    }

    /**
     * 获取个人信息
     */
    public void getUserInfo(Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_USER_INFO_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 重置密码
     */
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

    /**
     * 修改密码
     */
    public void modifyPassword(@NonNull String oldPsw, @NonNull String newPsw, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.MODIFY_PASSWORD_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("oldPwd", oldPsw)
                .addParams("newPwd", newPsw)
                .build()
                .execute(callback);
    }

    /**
     * 实名认证申请
     *
     * @param cardPath 证件图片，分号分割，格式：图片1;图片2;图片3;
     */
    public void realNameAuth(@NonNull String name, @NonNull String idNumber, String cardPath,
            Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.REAL_NAME_AUTH_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("realName", name)
                .addParams("cardId", idNumber)
                .addParams("verifyImgPath", cardPath)
                .build()
                .execute(callback);
    }

    /**
     * 查询认证情况
     */
    public void getRealNameAuthInfo(Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_REAL_NAME_AUTH_INFO_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 消息提醒开关设置
     *
     * @param switchStatus 1：开启消息推送 2：关闭消息推送
     */
    public void uploadPushMessageSwitch(String switchStatus, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.UPLOAD_PUSH_MESSAGE_SWITCH_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("type", switchStatus)
                .build()
                .execute(callback);
    }

    /**
     * 留言反馈
     */
    public void feedbackAdvise(String content, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.FEEDBACK_ADVISE_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("content", content)
                .build()
                .execute(callback);
    }

    /**
     * 版本检测
     */
    public void checkVersion(Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.CHECK_VERSION_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 修改用户个人信息
     */
    public void modifyUserInfo(UserInfoBean bean, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.MODIFY_USER_INFO_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("userFace", bean.userFace)
                .addParams("userNickname", bean.userNickname)
                .addParams("userSex", String.valueOf(bean.userSex))
                .addParams("userBirthday", bean.birthday)
                .addParams("arId", String.valueOf(bean.arId))
                .addParams("jobId", String.valueOf(bean.jobId))
                .addParams("address", bean.address)
                .addParams("userMobile", bean.userMobile)
                .build()
                .execute(callback);
    }

    /**
     * 所有职业信息
     */
    public void getJobList(Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_JOB_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .build()
                .execute(callback);
    }

    /**
     * 获取系统消息
     */
    public void getSystemMessage(int page, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_SYSTEM_MESSAGE_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("page", String.valueOf(page))
                .addParams("rows", "20")
                .build()
                .execute(callback);
    }

    /**
     * 获取提醒消息
     */
    public void getNotifyMessage(int page, Callback callback) {
        OkHttpUtils
                .get()
                .url(Api.GET_NOTIFY_MESSAGE_LIST_URL)
                .addParams("drivenType", getDriveType())
                .addParams("appkey", getAppKey())
                .addParams("page", String.valueOf(page))
                .addParams("rows", "20")
                .build()
                .execute(callback);
    }

//    public void uploadPhoto()

}
