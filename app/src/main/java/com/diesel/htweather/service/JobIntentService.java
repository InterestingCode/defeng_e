package com.diesel.htweather.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.diesel.htweather.base.DFApplication;
import com.diesel.htweather.response.JobResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.webapi.UserWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/9/29
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class JobIntentService extends IntentService {

    private static final String TAG = JobIntentService.class.getSimpleName();

    public JobIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        UserWebService.getInstance().getJobList(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "onError() " + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "onResponse() " + response);
                try {
                    JobResJO resJO = FastJsonUtils.getSingleBean(response, JobResJO.class);
                    if (resJO.status == 0 && null != resJO.data && !resJO.data.isEmpty()) {
                        DFApplication.jobs = resJO.data;
                    }
                } catch (Exception e) {
                    Log.e(TAG, "checkVersion#onResponse() " + e.getMessage());
                }
            }
        });
    }
}
