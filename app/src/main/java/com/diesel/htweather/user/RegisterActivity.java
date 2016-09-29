package com.diesel.htweather.user;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.StringUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.util.ViewUtils;
import com.diesel.htweather.webapi.UserWebService;
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

    @BindView(R.id.telephone_layout)
    LinearLayout mTelephoneLayout;

    @BindView(R.id.auth_code_layout)
    LinearLayout mAuthCodeLayout;

    @BindView(R.id.new_password_et)
    EditText mNewPasswordEt;

    @BindView(R.id.new_password_again_et)
    EditText mNewPasswordAgainEt;

    @BindView(R.id.password_layout)
    LinearLayout mPasswordLayout;

    @BindView(R.id.register_btn)
    Button mRegisterBtn;

    private String mMobile, mAuthCode;

    private int mCurrStep;

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
                if (mCurrStep == 0) {
                    mAuthCode = mAuthCodeEt.getText().toString();
                    if (TextUtils.isEmpty(mAuthCode)) {
                        ToastUtils.show(getString(R.string.tips_input_auth_code));
                        return;
                    }
                    verifyAuthCode();
                } else if (mCurrStep == 1) {
                    String newPsw = mNewPasswordEt.getText().toString();
                    if (TextUtils.isEmpty(newPsw)) {
                        ToastUtils.show(getString(R.string.tips_input_new_password));
                        return;
                    }
                    String newPswAgain = mNewPasswordAgainEt.getText().toString();
                    if (TextUtils.isEmpty(newPswAgain)) {
                        ToastUtils.show(getString(R.string.tips_input_new_password_again));
                        return;
                    }
                    if (!newPsw.equals(newPswAgain)) {
                        ToastUtils.show(getString(R.string.tips_new_password_mistake));
                        mNewPasswordEt.setText("");
                        mNewPasswordAgainEt.setText("");
                        mNewPasswordEt.requestFocus();
                        return;
                    }
                    register(newPsw);
                }
                break;
        }
    }

    private void changeToConfirmPassword() {
        ViewUtils.gone(mTelephoneLayout, mAuthCodeLayout);
        ViewUtils.visible(mPasswordLayout);
        mRegisterBtn.setText(R.string.save);
        mCurrStep = 1;
        mTimer.cancel();
    }

    private void getAuthCode() {
        mTimer.start();
        mAuthBtn.setEnabled(false);
        UserWebService.getInstance().getAuthCode(1, mMobile, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getAuthCode#onError() " + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getAuthCode#onResponse() " + response);
                try {
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null == resJO) {
                        return;
                    }
                    if (resJO.status != 0) {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "verifyAuthCode#onResponse() " + e.getMessage());
                }
            }
        });
    }

    private void verifyAuthCode() {
        showDialog();
        UserWebService.getInstance().verifyAuthCode(mMobile, mAuthCode, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "verifyAuthCode#onError() " + e.getMessage());
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "verifyAuthCode#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null == resJO) {
                        return;
                    }
                    if (resJO.status != 0) {
                        ToastUtils.show(resJO.msg);
                    } else {
                        changeToConfirmPassword();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "verifyAuthCode#onResponse() " + e.getMessage());
                }
            }
        });
    }

    private void register(String password) {
        showDialog();
        UserWebService.getInstance().register(password, mMobile, mAuthCode, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "register#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "register#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    ToastUtils.show(resJO.msg);
                    if (resJO.status == 0) {
                        finish();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "register#onResponse() " + e.getMessage());
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
