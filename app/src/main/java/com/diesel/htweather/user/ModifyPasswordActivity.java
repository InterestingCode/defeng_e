package com.diesel.htweather.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.UserWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ModifyPasswordActivity extends BaseActivity {

    @BindView(R.id.old_password_et)
    EditText mOldPasswordEt;

    @BindView(R.id.new_password_et)
    EditText mNewPasswordEt;

    @BindView(R.id.new_password_again_et)
    EditText mNewPasswordAgainEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pasword);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.commit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.commit_btn:
                checkPassword();
                break;
        }
    }

    private void checkPassword() {
        String oldPsw = mOldPasswordEt.getText().toString();
        if (TextUtils.isEmpty(oldPsw)) {
            ToastUtils.show(getString(R.string.tips_input_old_password));
            return;
        }
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
        modifyPassword(oldPsw, newPsw);
    }

    private void modifyPassword(String oldPsw, String newPsw) {
        showDialog();
        UserWebService.getInstance().modifyPassword(oldPsw, newPsw, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "modifyPassword#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "modifyPassword#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    if (resJO.status != 0) {
                        ToastUtils.show(resJO.msg);
                    } else {
                        ToastUtils.show(getString(R.string.tips_modify_password_success));
                        finish();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "feedbackAdvice#onResponse() " + e.getMessage());
                }
            }
        });
    }
}
