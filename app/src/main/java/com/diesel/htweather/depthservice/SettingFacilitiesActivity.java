package com.diesel.htweather.depthservice;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.base.HTApplication;
import com.diesel.htweather.util.DialogUtils;
import com.diesel.pickerview.OptionsPickerView;
import com.diesel.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingFacilitiesActivity extends BaseActivity {
    @BindView(R.id.tvCrop)
    TextView tvCrop;
    @BindView(R.id.tvGetTime)
    TextView tvGetTime;
    @BindView(R.id.tvAddress)
    TextView tvAddress;

    private TimePickerView mTimePickerView;

    private OptionsPickerView mCityPickerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_facilities);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.btnCrop, R.id.btnGetTime, R.id.btnAddress, R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.btnCrop:
                DialogUtils.showInputDialog(this, "种植作物",
                        new DialogUtils.DialogOnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                String inputContent) {
                                tvCrop.setText(inputContent);
                            }
                        });
                break;
            case R.id.btnGetTime:
                showTimePickerView();
                break;
            case R.id.btnAddress:
                showAreaPickerView();
                break;
            case R.id.btnCommit:
                DialogUtils.showCommitDialog(this);
                break;

        }
    }

    private void showTimePickerView() {
        if (null == mTimePickerView) {
            mTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
            mTimePickerView.setTitle(getString(R.string.choose_birth));
            mTimePickerView.setTime(new Date());
            mTimePickerView.setCyclic(true);
            mTimePickerView.setCancelable(true);
            mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String time = format.format(date);
                    tvGetTime.setText(time);
                }
            });
        }
        mTimePickerView.show();
    }

    private void showAreaPickerView() {
        if (null == mCityPickerView) {
            mCityPickerView = new OptionsPickerView(this);
            mCityPickerView.setTitle(getString(R.string.choose_area));
            mCityPickerView
                    .setPicker(HTApplication.provinces, HTApplication.cities,
                            HTApplication.countries,
                            true);
            mCityPickerView.setCyclic(true, true, true);
            mCityPickerView.setSelectOptions(0, 0, 0);

            mCityPickerView
                    .setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3) {
                            // 返回的分别是三个级别的选中位置
                            String tx = HTApplication.provinces.get(options1).pvName + "-"
                                    + HTApplication.cities.get(options1).get(option2).ctName + "-"
                                    + HTApplication.countries.get(options1).get(option2)
                                    .get(options3).arName;
                            tvAddress.setText(tx);
                        }
                    });
        }
        mCityPickerView.show();
    }
}
