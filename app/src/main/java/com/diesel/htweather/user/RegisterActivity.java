package com.diesel.htweather.user;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.constant.Api;
import com.diesel.htweather.response.BaseResJo;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.StringUtils;
import com.diesel.htweather.util.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.account_et)
    EditText mAccountEt;

    @BindView(R.id.auth_code_et)
    EditText mAuthCodeEt;

    @BindView(R.id.get_auth_code_btn)
    Button mAuthBtn;

    private String mMobile, mAuthCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.get_auth_code_btn, R.id.register_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.get_auth_code_btn:
                mMobile = mAccountEt.getText().toString();
                if (TextUtils.isEmpty(mMobile)) {
                    ToastUtils.show(getString(R.string.tips_input_telephone));
                    return;
                }
                if (!StringUtils.mobileVerify(mMobile)) {
                    ToastUtils.show(getString(R.string.tips_input_correct_telephone));
                    return;
                }
                getAuthCode();
                break;
            case R.id.register_btn:
                mAuthCode = mAuthCodeEt.getText().toString();
                if (TextUtils.isEmpty(mAuthCode)) {
                    ToastUtils.show(getString(R.string.tips_input_auth_code));
                    return;
                }
                verifyMobile();
                break;
        }
    }

    private void getAuthCode() {
        mTimer.start();
        mAuthBtn.setEnabled(false);
        OkHttpUtils
                .get()
                .url(Api.GET_AUTH_CODE_URL)
                .addParams("drivenType", "02")
                .addParams("appkey", "b66a5c46acf46c10a601bc8cabe4c074")
                .addParams("mobile", mMobile)
                .addParams("smsType", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "getAuthCode#onError() " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "getAuthCode#onResponse() " + response);
                        try {
                            BaseResJo resJO = FastJsonUtils
                                    .getSingleBean(response, BaseResJo.class);
                            if (null == resJO) {
                                return;
                            }
                            if (resJO.status != 0) {
                                ToastUtils.show(resJO.msg);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "verifyMobile#onResponse() " + e.getMessage());
                        }
                    }
                });
    }

    private void verifyMobile() {
        showDialog();
        OkHttpUtils
                .get()
                .url(Api.VERIFY_MOBILE_URL)
                .addParams("drivenType", "02")
                .addParams("appkey", "b66a5c46acf46c10a601bc8cabe4c074")
                .addParams("mobile", mMobile)
                .addParams("smsCode", mAuthCode)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "verifyMobile#onError() " + e.getMessage());
                        dismissDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "verifyMobile#onResponse() " + response);
                        dismissDialog();
                        try {
                            BaseResJo resJO = FastJsonUtils
                                    .getSingleBean(response, BaseResJo.class);
                            if (null == resJO) {
                                return;
                            }
                            if (resJO.status != 0) {
                                ToastUtils.show(resJO.msg);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "verifyMobile#onResponse() " + e.getMessage());
                        }
                    }
                });
    }

    private CountDownTimer mTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mAuthBtn.setText(getString(R.string.count_down_timer, millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            mAuthBtn.setEnabled(true);
            mAuthBtn.setText(R.string.get_auth_code);
        }
    };

    @Override
    public void finish() {
        super.finish();
        mTimer.cancel();
    }
}
