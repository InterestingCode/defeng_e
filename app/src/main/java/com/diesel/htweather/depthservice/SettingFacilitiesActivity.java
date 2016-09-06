package com.diesel.htweather.depthservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingFacilitiesActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_facilities);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.btnCrop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.btnCrop:
                Intent intent = new Intent(this, EditInputActivity.class);
                startActivity(intent);
                break;
        }
    }
}
