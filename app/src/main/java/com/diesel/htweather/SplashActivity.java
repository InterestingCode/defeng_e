package com.diesel.htweather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.SharedPreferencesUtils;
import com.diesel.htweather.webapi.UserWebService;

public class SplashActivity extends BaseActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                ActivityNav.getInstance().startMainActivity(SplashActivity.this);
            } else if (msg.what == 1) {
                ActivityNav.getInstance().startGuideActivity(SplashActivity.this);
            }
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        boolean needEnterGuidePage = SharedPreferencesUtils.getInstance(this).needEnterGuidePage();
        mHandler.sendEmptyMessageDelayed(needEnterGuidePage ? 1 : 0, 2000);

        UserWebService.getInstance().login("18782941024", "123456", null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mHandler) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }
}
