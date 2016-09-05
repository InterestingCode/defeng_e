package com.diesel.htweather.user;

import android.os.Bundle;
import android.view.View;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.base.HTApplication;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.pickerview.OptionsPickerView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActualFarmingSettingActivity extends BaseActivity {

    private OptionsPickerView mCityPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_farming_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.add_area_tv, R.id.watch_plants_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.add_area_tv:
                showAreaPickerView();
                break;
            case R.id.watch_plants_tv:
                ActivityNav.getInstance().startAddWatchPlantActivity(this);
                break;
        }
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
                            String tx = HTApplication.provinces.get(options1).name + "-"
                                    + HTApplication.cities.get(options1).get(option2).name + "-"
                                    + HTApplication.countries.get(options1).get(option2)
                                    .get(options3).name;
                            ToastUtils.show("添加 \"" + tx + "\" 成功");
                        }
                    });
        }
        mCityPickerView.show();
    }
}
