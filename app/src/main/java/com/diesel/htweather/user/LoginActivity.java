package com.diesel.htweather.user;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.util.ActivityNav;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                ActivityNav.getInstance().startMainActivity(this);
                break;
        }
    }
}
