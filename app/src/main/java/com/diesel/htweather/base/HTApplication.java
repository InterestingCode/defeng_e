package com.diesel.htweather.base;

import android.app.Application;
import android.content.Intent;

import com.diesel.htweather.db.DBManager;
import com.diesel.htweather.db.RegionDBHelper;
import com.diesel.htweather.service.AreaIntentService;
import com.diesel.htweather.util.CrashHandler;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

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
public class HTApplication extends Application {

    private static HTApplication mInstance = null;

    public static HTApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        CrashHandler.getInstance().init(this);
        initOkHttp();
        Fresco.initialize(this);
//        RegionDBHelper.initDbFile(this);
        startService(new Intent(this, AreaIntentService.class));
        DBManager dbHelper = new DBManager(this);
        dbHelper.openDatabase();
    }

    private void initOkHttp() {
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(this)); // 持久化cookie
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .cookieJar(cookieJar)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new LoggerInterceptor("DFWeather"))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

}
