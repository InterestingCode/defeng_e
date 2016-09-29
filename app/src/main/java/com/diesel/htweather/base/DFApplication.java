package com.diesel.htweather.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import com.diesel.htweather.constant.Consts;
import com.diesel.htweather.db.DBHelper;
import com.diesel.htweather.response.AreaResJO;
import com.diesel.htweather.response.JobResJO;
import com.diesel.htweather.service.AreaIntentService;
import com.diesel.htweather.util.CrashHandler;
import com.diesel.htweather.util.Drawables;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/16
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class DFApplication extends Application {

    private static DFApplication mInstance = null;

//    public static ArrayList<RegionObject> provinces;
//
//    public static ArrayList<ArrayList<RegionObject>> cities;
//
//    public static ArrayList<ArrayList<ArrayList<RegionObject>>> countries;

    public static ArrayList<AreaResJO.ProvinceEntity> provinces;

    public static ArrayList<ArrayList<AreaResJO.ProvinceEntity.CityEntity>> cities;

    public static ArrayList<ArrayList<ArrayList<AreaResJO.ProvinceEntity.CityEntity.CountryEntity>>> countries;

    public static ArrayList<JobResJO.JobEntity> jobs;

    public static DFApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        DBHelper.init(this);
        CrashHandler.getInstance().init(this);
        Consts.sDeviceToken = getDrivenToken(this);
        initOkHttp();
        Drawables.init(this);
        Fresco.initialize(this);
        startService(new Intent(this, AreaIntentService.class));
    }

    private void initOkHttp() {
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(this)); // 持久化cookie
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .cookieJar(cookieJar)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new LoggerInterceptor("defeng_e"))
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    public static String getDrivenToken(Context context) {
        String drivenToken = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        if (drivenToken == null) {
            drivenToken = android.provider.Settings.System.getString(
                    context.getContentResolver(),
                    android.provider.Settings.System.ANDROID_ID);
            if (drivenToken == null) {
                return "AAAAAAAAAAAAA";
            }
        }
        return drivenToken;
    }

}
