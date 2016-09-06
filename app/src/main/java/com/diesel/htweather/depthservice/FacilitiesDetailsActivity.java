package com.diesel.htweather.depthservice;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mac14 on 16/9/6.
 */
public class FacilitiesDetailsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_details);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
