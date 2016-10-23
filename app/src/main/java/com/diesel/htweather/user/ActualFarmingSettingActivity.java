package com.diesel.htweather.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.base.DFApplication;
import com.diesel.htweather.event.RefreshFarmingDataEvent;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.IntentExtras;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.util.ViewUtils;
import com.diesel.pickerview.OptionsPickerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActualFarmingSettingActivity extends BaseActivity {

    private static final int REQUEST_CODE_ADD_CROPS = 99;

    @BindView(R.id.add_area_value_tv)
    TextView mAddAreaValueTv;

    @BindView(R.id.watch_plants_value_tv)
    TextView mWatchPlantsValueTv;

    private OptionsPickerView mCityPickerView;

    private int mAreaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_farming_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.add_area_layout, R.id.add_plants_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.add_area_layout:
                showAreaPickerView();
                break;
            case R.id.add_plants_layout:
                if (mAreaId == 0) {
                    ToastUtils.show("请先选择地区");
                    return;
                }
                ActivityNav.getInstance().startAddWatchPlantActivity(this, mAreaId, REQUEST_CODE_ADD_CROPS);
                break;
        }
    }

    private void showAreaPickerView() {
        if (null == mCityPickerView) {
            mCityPickerView = new OptionsPickerView(this);
            mCityPickerView.setTitle(getString(R.string.choose_area));
            mCityPickerView
                    .setPicker(DFApplication.provinces, DFApplication.cities,
                            DFApplication.countries,
                            true);
            mCityPickerView.setCyclic(false, false, false);
            mCityPickerView.setSelectOptions(0, 0, 0);

            mCityPickerView
                    .setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3) {
//                            // 返回的分别是三个级别的选中位置
                            String tx = DFApplication.provinces.get(options1).pvName + "-"
                                    + DFApplication.cities.get(options1).get(option2).ctName + "-"
                                    + DFApplication.countries.get(options1).get(option2)
                                    .get(options3).arName;
//                            ToastUtils.show("添加 \"" + tx + "\" 成功");
                            mAreaId = DFApplication.countries.get(options1).get(option2)
                                    .get(options3).arId;
                            mAddAreaValueTv.setText(tx);
                        }
                    });
        }
        mCityPickerView.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_CROPS && resultCode == RESULT_OK && null != data) {
            mWatchPlantsValueTv.setText(IntentExtras.getCropsName(data));
            EventBus.getDefault().post(new RefreshFarmingDataEvent());
        }
    }
}
