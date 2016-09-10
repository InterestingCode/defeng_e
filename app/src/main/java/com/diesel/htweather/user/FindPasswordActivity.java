package com.diesel.htweather.user;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.util.ViewUtils;

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

    @BindView(R.id.step_one_layout)
    LinearLayout mStepOneLayout;

    @BindView(R.id.step_two_layout)
    LinearLayout mStepTwoLayout;

    @BindView(R.id.step_three_layout)
    LinearLayout mStepThreeLayout;

    @BindView(R.id.next_step_btn)
    Button mNextStepBtn;

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
                break;
            case R.id.next_step_btn:
                if (mCurrentStep == 1) {
                    changeToResetPassword();
                } else if (mCurrentStep == 2) {
                    changeToSettingSuccess();
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
}
