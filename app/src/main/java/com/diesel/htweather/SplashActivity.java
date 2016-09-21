package com.diesel.htweather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.model.UserInfoBean;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.SharedPreferencesUtils;
import com.diesel.htweather.webapi.UserWebService;

public class SplashActivity extends BaseActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                ActivityNav.getInstance().startMainActivity(mActivity);
            } else if (msg.what == 1) {
                ActivityNav.getInstance().startGuideActivity(mActivity);
            }
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        boolean needEnterGuidePage = SharedPreferencesUtils.getInstance(this).needEnterGuidePage();
        mHandler.sendEmptyMessageDelayed(needEnterGuidePage ? 1 : 0, 3000);

        UserInfoBean userInfo = SharedPreferencesUtils.getInstance(mContext).getUserInfo();
        if (!TextUtils.isEmpty(userInfo.userMobile) && !TextUtils.isEmpty(userInfo.password)) {
            Log.d(TAG, "onCreate() " + userInfo.toString());
            UserWebService.getInstance().login(userInfo.userMobile, userInfo.password, null);
        }
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
