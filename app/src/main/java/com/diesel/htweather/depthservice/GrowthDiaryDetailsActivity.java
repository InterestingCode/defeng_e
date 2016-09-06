package com.diesel.htweather.depthservice;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class GrowthDiaryDetailsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_growth_diary_details);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
