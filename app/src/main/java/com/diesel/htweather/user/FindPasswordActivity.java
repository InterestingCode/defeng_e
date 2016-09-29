package com.diesel.htweather.user;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class FindPasswordActivity extends BaseActivity {

    @BindView(R.id.step_one_dot_iv)
    ImageView mStepOneDotIv;

    @BindView(R.id.step_one_line_view)
    View mStepOneLineView;

    @BindView(R.id.step_two_dot_iv)
    ImageView mStepTwoDotIv;

    @BindView(R.id.step_two_line_view)
    View mStepTwoLineView;

    @BindView(R.id.step_three_dot_iv)
    ImageView mStepThreeDotIv;

    @BindView(R.id.verify_telephone_tv)
    TextView mVerifyTelephoneTv;

    @BindView(R.id.setting_password_tv)
    TextView mSettingPasswordTv;

    @BindView(R.id.setting_password_success_tv)
    TextView mSettingPasswordSuccessTv;

    @BindView(R.id.account_et)
    EditText mAccountEt;

    @BindView(R.id.telephone_layout)
    LinearLayout mTelephoneLayout;

    @BindView(R.id.auth_code_et)
    EditText mAuthCodeEt;

    @BindView(R.id.auth_code_layout)
    LinearLayout mAuthCodeLayout;

    @BindView(R.id.new_password_et)
    EditText mNewPasswordEt;

    @BindView(R.id.new_password_again_et)
    EditText mNewPasswordAgainEt;

    @BindView(R.id.step_one_layout)
    LinearLayout mStepOneLayout;

    @BindView(R.id.step_two_layout)
    LinearLayout mStepTwoLayout;

    @BindView(R.id.step_three_layout)
    LinearLayout mStepThreeLayout;

    @BindView(R.id.next_step_btn)
    Button mNextStepBtn;

    @BindView(R.id.get_auth_code_btn)
    Button mAuthBtn;

    private String mMobile;

    private int mCurrentStep = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.get_auth_code_btn, R.id.next_step_btn})
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
            case R.id.next_step_btn:
                if (mCurrentStep == 1) {
                    String authCode = mAuthCodeEt.getText().toString();
                    if (TextUtils.isEmpty(authCode)) {
                        ToastUtils.show(getString(R.string.tips_input_auth_code));
                        return;
                    }
                    verifyMobile(authCode);
                } else if (mCurrentStep == 2) {
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
                    resetPassword(newPsw);
                } else if (mCurrentStep == 3) {
                    finish();
                }
                break;
        }
    }

    private void changeToResetPassword() {
        mCurrentStep = 2;
        mNextStepBtn.setText(R.string.commit);
        mStepOneLineView.setBackgroundResource(R.drawable.icon_line_green);
        mStepTwoDotIv.setImageResource(R.drawable.icon_dot_green);
        mSettingPasswordTv.setTextColor(ContextCompat.getColor(this, R.color.bg_top_header));
        ViewUtils.visible(mStepTwoLayout);
        ViewUtils.gone(mStepOneLayout, mStepThreeLayout);
    }

    private void changeToSettingSuccess() {
        mCurrentStep = 3;
        mNextStepBtn.setText(R.string.back_to_login);
        mStepTwoLineView.setBackgroundResource(R.drawable.icon_line_green);
        mStepThreeDotIv.setImageResource(R.drawable.icon_dot_green);
        mSettingPasswordSuccessTv.setTextColor(ContextCompat.getColor(this, R.color.bg_top_header));
        ViewUtils.visible(mStepThreeLayout);
        ViewUtils.gone(mStepOneLayout, mStepTwoLayout);
    }

    private void getAuthCode() {
        mTimer.start();
        mAuthBtn.setEnabled(false);
        UserWebService.getInstance().getAuthCode(3, mMobile, new StringCallback() {
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
                    Log.e(TAG, "verifyMobile#onResponse() " + e.getMessage());
                }
            }
        });
    }

    private void verifyMobile(String authCode) {
        showDialog();
        UserWebService.getInstance().verifyAuthCode(mMobile, authCode, new StringCallback() {
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
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null == resJO) {
                        return;
                    }
                    if (resJO.status != 0) {
                        ToastUtils.show(resJO.msg);
                    } else {
                        changeToResetPassword();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "verifyMobile#onResponse() " + e.getMessage());
                }
            }
        });
    }

    private void resetPassword(String password) {
        showDialog();
        UserWebService.getInstance().resetPassword(password, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "resetPassword#onError() " + e.getMessage());
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "resetPassword#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null == resJO) {
                        return;
                    }
                    if (resJO.status != 0) {
                        ToastUtils.show(resJO.msg);
                    } else {
                        changeToSettingSuccess();
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
