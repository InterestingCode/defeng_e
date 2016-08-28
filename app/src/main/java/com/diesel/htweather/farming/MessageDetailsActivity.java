package com.diesel.htweather.farming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
