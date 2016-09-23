package com.diesel.htweather.farming;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.base.HTApplication;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.pickerview.OptionsPickerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 城市管理
 */
public class CityManageActivity extends BaseActivity {

    @BindView(R.id.edit_btn)
    ImageView mEditBtn;

    @BindView(R.id.save_btn)
    TextView mSaveBtn;

    @BindView(R.id.city_one_tv)
    TextView mCityOneTv;

    @BindView(R.id.arrow_or_delete_btn_1)
    ImageView mArrowOrDeleteBtn1;

    @BindView(R.id.city_two_tv)
    TextView mCityTwoTv;

    @BindView(R.id.arrow_or_delete_btn_2)
    ImageView mArrowOrDeleteBtn2;

    @BindView(R.id.city_three_tv)
    TextView mCityThreeTv;

    @BindView(R.id.arrow_or_delete_btn_3)
    ImageView mArrowOrDeleteBtn3;

    @BindView(R.id.add_weather_city_layout)
    LinearLayout mAddWeatherCityLayout;

    private boolean mIsEditable = true;

    private OptionsPickerView mCityPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manage);
        ButterKnife.bind(this);

        initPickerView();
    }

    @OnClick({R.id.back_btn, R.id.edit_btn, R.id.city_one_tv, R.id.arrow_or_delete_btn_1,
            R.id.city_two_tv, R.id.arrow_or_delete_btn_2, R.id.city_three_tv,
            R.id.arrow_or_delete_btn_3, R.id.add_weather_city_layout, R.id.save_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.edit_btn:
                mIsEditable = !mIsEditable;
                changeWidgetStatus();
                break;
            case R.id.city_one_tv:
                break;
            case R.id.arrow_or_delete_btn_1:
                break;
            case R.id.city_two_tv:
                break;
            case R.id.arrow_or_delete_btn_2:
                break;
            case R.id.city_three_tv:
                break;
            case R.id.arrow_or_delete_btn_3:
                break;
            case R.id.add_weather_city_layout:
                mCityPickerView.show();
                break;
            case R.id.save_btn:
                mIsEditable = !mIsEditable;
                changeWidgetStatus();
                break;
        }
    }

    private void changeWidgetStatus() {
        mArrowOrDeleteBtn1.setImageResource(mIsEditable ? R.drawable.ic_right_gray_arrow
                : R.drawable.ic_delete_weather_city);
        mArrowOrDeleteBtn2.setImageResource(mIsEditable ? R.drawable.ic_right_gray_arrow
                : R.drawable.ic_delete_weather_city);
        mArrowOrDeleteBtn3.setImageResource(mIsEditable ? R.drawable.ic_right_gray_arrow
                : R.drawable.ic_delete_weather_city);
        mEditBtn.setVisibility(mIsEditable ? View.VISIBLE : View.GONE);
        mSaveBtn.setVisibility(mIsEditable ? View.GONE : View.VISIBLE);
        mAddWeatherCityLayout.setVisibility(mIsEditable ? View.VISIBLE : View.GONE);
    }

    private void initPickerView() {
        mCityPickerView = new OptionsPickerView(this);
        mCityPickerView.setTitle(getString(R.string.choose_area));
        mCityPickerView
                .setPicker(HTApplication.provinces, HTApplication.cities, HTApplication.countries,
                        true);
        mCityPickerView.setCyclic(true, true, true);
        mCityPickerView.setSelectOptions(0, 0, 0);

        mCityPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                // 返回的分别是三个级别的选中位置
                String tx = HTApplication.provinces.get(options1).pvName + "-"
                        + HTApplication.cities.get(options1).get(option2).ctName + "-"
                        + HTApplication.countries.get(options1).get(option2).get(options3).arName;
                ToastUtils.show(tx);
            }
        });
    }

}
