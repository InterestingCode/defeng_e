package com.diesel.htweather.farming;

import android.os.Bundle;
import android.view.View;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FarmingDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farming_details);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.feedback_layout, R.id.share_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.feedback_layout:
                break;
            case R.id.share_layout:
                break;
        }
    }
}
