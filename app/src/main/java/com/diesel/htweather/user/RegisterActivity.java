package com.diesel.htweather.user;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.account_et)
    EditText mAccountEt;

    @BindView(R.id.auth_code_et)
    EditText mAuthCodeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.get_auth_code_btn, R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.get_auth_code_btn:
                break;
            case R.id.login_btn:
                break;
        }
    }
}
