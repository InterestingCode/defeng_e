package com.diesel.htweather.user;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.widget.EditUserInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemSettingActivity extends BaseActivity {

    @BindView(R.id.back_btn)
    ImageView mBackBtn;

    @BindView(R.id.clear_cache_view)
    EditUserInfoView mClearCacheView;

    @BindView(R.id.modify_password_view)
    EditUserInfoView mModifyPasswordView;

    @BindView(R.id.feedback_advise_view)
    EditUserInfoView mFeedbackAdviseView;

    @BindView(R.id.help_center_view)
    EditUserInfoView mHelpCenterView;

    @BindView(R.id.about_defeng_view)
    EditUserInfoView mAboutDefengView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.clear_cache_view, R.id.modify_password_view,
            R.id.feedback_advise_view, R.id.help_center_view, R.id.about_defeng_view,
            R.id.exit_app_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.clear_cache_view:
                break;
            case R.id.modify_password_view:
                break;
            case R.id.feedback_advise_view:
                break;
            case R.id.help_center_view:
                break;
            case R.id.about_defeng_view:
                break;
            case R.id.exit_app_btn:
                break;
        }
    }
}
