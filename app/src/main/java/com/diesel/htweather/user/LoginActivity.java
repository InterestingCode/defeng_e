package com.diesel.htweather.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.model.UserInfoBean;
import com.diesel.htweather.response.LoginResJO;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.SharedPreferencesUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.UserWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.account_et)
    EditText mAccountEt;

    @BindView(R.id.password_et)
    EditText mPasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.register_tv, R.id.forget_password_tv, R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_tv:
                ActivityNav.getInstance().startRegisterActivity(this);
                break;
            case R.id.forget_password_tv:
                ActivityNav.getInstance().startFindPasswordActivity(this);
                break;
            case R.id.login_btn:
                String userName = mAccountEt.getText().toString();
                if (TextUtils.isEmpty(userName)) {
                    ToastUtils.show(getString(R.string.tips_input_username));
                    return;
                }
                String password = mPasswordEt.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.show(getString(R.string.tips_input_password));
                    return;
                }
                login(userName, password);
                break;
        }
    }

    private void login(String userName, final String password) {
        showDialog();
        UserWebService.getInstance().login(userName, password, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "login#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "login#onResponse() " + response);
                dismissDialog();
                try {
                    LoginResJO resJO = FastJsonUtils.getSingleBean(response, LoginResJO.class);
                    if (null != resJO && null != resJO.obj && resJO.status == 0) {
                        UserInfoBean userInfo = SharedPreferencesUtils.getInstance(mContext)
                                .getUserInfo();
                        userInfo.password = password;
                        userInfo.userMobile = resJO.obj.userMobile;
                        userInfo.userNickname = resJO.obj.userNickname;
                        userInfo.userId = resJO.obj.userId;
                        userInfo.userFace = resJO.obj.userFace;
                        userInfo.birthday = resJO.obj.birthday;
                        userInfo.arId = resJO.obj.arId;
                        userInfo.userSex = resJO.obj.userSex;
                        userInfo.address = resJO.obj.address;
                        userInfo.isTrue = resJO.obj.isTrue;
                        userInfo.jobId = resJO.obj.jobId;
                        userInfo.realName = resJO.obj.realName;
                        userInfo.cardId = resJO.obj.cardId;
                        userInfo.pushWarning = resJO.obj.pushWarning;
                        userInfo.areaAddr = resJO.obj.areaAddr;
                        userInfo.userType = resJO.obj.userType;
                        userInfo.userFace = resJO.obj.userFace;
                        SharedPreferencesUtils.getInstance(mContext).updateUserInfo(userInfo);
                        SharedPreferencesUtils.getInstance(mContext)
                                .enableMessageNotify(resJO.obj.pushWarning == 1);

                        ActivityNav.getInstance().startMainActivity(mActivity);
                        finish();
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "login#onResponse() " + e.getMessage());
                }
            }
        });
    }
}
