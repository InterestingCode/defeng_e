package com.diesel.htweather.depthservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.depthservice.model.DoctorBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgricultureDoctorActivity extends BaseActivity {

    @BindView(R.id.tvDoctorTitle)
    TextView tvDoctorTitle;

    @BindView(R.id.tvDoctorTime)
    TextView tvAgricultureTime;

    @BindView(R.id.tvDoctorContent)
    TextView tvDoctorContent;

    DoctorBean doctorBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_doctor);
        ButterKnife.bind(this);
        doctorBean = (DoctorBean) getIntent().getSerializableExtra("doctorBean");
        tvDoctorTitle.setText(doctorBean.getTitle());
        tvAgricultureTime.setText(doctorBean.getSendTime());
        tvDoctorContent.setText(doctorBean.getDesc());
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
