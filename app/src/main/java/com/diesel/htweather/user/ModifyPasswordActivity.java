package com.diesel.htweather.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        ToastUtils.show(getString(R.string.tips_modify_password_success));
        finish();
    }
}
