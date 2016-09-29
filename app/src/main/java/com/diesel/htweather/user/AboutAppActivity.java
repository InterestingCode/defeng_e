package com.diesel.htweather.user;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.response.CheckVersionResJO;
import com.diesel.htweather.util.AppUtils;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.UserWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AboutAppActivity extends BaseActivity {

    @BindView(R.id.current_version_tv)
    TextView mCurrentVersionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        ButterKnife.bind(this);

        mCurrentVersionTv
                .setText(getString(R.string.current_defeng_version, AppUtils.getVersionName(this)));
    }

    @OnClick({R.id.back_btn, R.id.check_version_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.check_version_tv:
                checkVersion();
                break;
        }
    }

    private void checkVersion() {
        showDialog();
        UserWebService.getInstance().checkVersion(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "checkVersion#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "checkVersion#onResponse() " + response);
                dismissDialog();
                try {
                    CheckVersionResJO resJo = FastJsonUtils
                            .getSingleBean(response, CheckVersionResJO.class);
                    if (null == resJo || null == resJo.obj) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    if (resJo.status == 0) {
                        if (resJo.obj.versionCode <= AppUtils.getVersionCode(mContext)) {
                            ToastUtils.show(getString(R.string.current_version_is_latest));
                        } else {
                            ToastUtils.show(getString(R.string.current_version_is_older));
                        }
                    } else {
                        ToastUtils.show(resJo.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "checkVersion#onResponse() " + e.getMessage());
                }
            }
        });
    }
}
