package com.diesel.htweather.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @BindView(R.id.setting_success_layout)
    LinearLayout mSettingSuccessLayout;

    @BindView(R.id.next_step_btn)
    Button mNextStepBtn;

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
                break;
            case R.id.next_step_btn:
                break;
        }
    }
}
