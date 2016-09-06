package com.diesel.htweather.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.util.AppUtils;
import com.diesel.htweather.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutAppActivity extends BaseActivity {

    @BindView(R.id.current_version_tv)
    TextView mCurrentVersionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        ButterKnife.bind(this);

        mCurrentVersionTv
                .setText(getString(R.string.current_defeng_version, AppUtils.getVersionName(this)));
    }

    @OnClick({R.id.back_btn, R.id.check_version_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.check_version_tv:
                ToastUtils.show(getString(R.string.current_version_is_latest));
                break;
        }
    }
}
