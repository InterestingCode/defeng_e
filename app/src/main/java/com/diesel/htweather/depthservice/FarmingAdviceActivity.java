package com.diesel.htweather.depthservice;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 农事建议
 */
public class FarmingAdviceActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farming_advice);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
