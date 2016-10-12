package com.diesel.htweather.user;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.SharedPreferencesUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.UserWebService;
import com.diesel.htweather.widget.EditUserInfoView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class SystemSettingActivity extends BaseActivity {

    @BindView(R.id.back_btn)
    ImageView mBackBtn;

    @BindView(R.id.message_notify_view)
    EditUserInfoView mMsgNotifyView;

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

        boolean enable = SharedPreferencesUtils.getInstance(mContext).messageNotifyEnable();
        final CheckBox msgNotifyCheckBox = mMsgNotifyView.getColumnCheckBox();
        msgNotifyCheckBox.setChecked(enable);
        msgNotifyCheckBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, final boolean b) {
                        UserWebService.getInstance().uploadPushMessageSwitch(b ? "1" : "2",
                                new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        Log.e(TAG, "onCheckedChanged#onError() " + e.getMessage());
                                        ToastUtils.show(getString(R.string.tips_request_failure));
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Log.d(TAG, "onCheckedChanged#onResponse() " + response);
                                        try {
                                            BaseResJO resJO = FastJsonUtils
                                                    .getSingleBean(response, BaseResJO.class);
                                            if (null == resJO) {
                                                return;
                                            }
                                            if (resJO.status != 0) {
                                                ToastUtils.show(resJO.msg);
                                            } else {
                                                SharedPreferencesUtils.getInstance(mContext)
                                                        .enableMessageNotify(b);
                                            }
                                        } catch (Exception e) {
                                            Log.e(TAG, "onCheckedChanged#onResponse() " + e
                                                    .getMessage());
                                        }
                                    }
                                });
                    }
                });
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
                Fresco.getImagePipeline().clearCaches();
                Fresco.getImagePipeline().clearDiskCaches();
                Fresco.getImagePipeline().clearMemoryCaches();
//                SharedPreferencesUtils.getInstance(mContext).clearCaches();
                ToastUtils.show(getString(R.string.tips_cache_had_cleared));
//                ActivityNav.getInstance().startLoginActivity(mActivity, true);
//                finish();
                break;
            case R.id.modify_password_view:
                ActivityNav.getInstance().startModifyPasswordActivity(this);
                break;
            case R.id.feedback_advise_view:
                ActivityNav.getInstance().startFeedbackActivity(this);
                break;
            case R.id.help_center_view:
                ActivityNav.getInstance().startHelpCenterActivity(this);
                break;
            case R.id.about_defeng_view:
                ActivityNav.getInstance().startAboutAppActivity(this);
                break;
            case R.id.exit_app_btn:
                ActivityNav.getInstance().startLoginActivity(mActivity, true);
                finish();
                break;
        }
    }
}
