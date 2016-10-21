package com.diesel.htweather.depthservice;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.base.DFApplication;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.util.DialogUtils;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.diesel.pickerview.OptionsPickerView;
import com.diesel.pickerview.TimePickerView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.diesel.htweather.base.DFApplication.countries;


public class SettingFacilitiesActivity extends BaseActivity {
    @BindView(R.id.tvCrop)
    TextView tvCrop;
    @BindView(R.id.cropTypeName)
    TextView cropTypeName;
    @BindView(R.id.cropPropertyNames)
    TextView cropPropertyNames;
    @BindView(R.id.areaNum)
    TextView areaNum;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvGetTime)
    TextView tvGetTime;
    @BindView(R.id.plantingTime)
    TextView plantingTime;

    private int arId; // 区域ID

    private OptionsPickerView mCityPickerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_facilities);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.btnCrop, R.id.cropTypeName, R.id.cropPropertyNames, R.id.areaNum, R.id.btnGetTime, R.id.btnAddress, R.id.btnPlantingTime, R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.btnCrop:
                DialogUtils.showInputDialog(this, "种植作物",
                        new DialogUtils.DialogOnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, String inputContent) {
                                tvCrop.setText(inputContent);
                            }
                        });
                break;
            case R.id.cropTypeName:
                DialogUtils.showInputDialog(this, "作物品种",
                        new DialogUtils.DialogOnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, String inputContent) {
                                cropTypeName.setText(inputContent);
                            }
                        });
                break;
            case R.id.cropPropertyNames:
                DialogUtils.showInputDialog(this, "作物特性",
                        new DialogUtils.DialogOnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, String inputContent) {
                                cropPropertyNames.setText(inputContent);
                            }
                        });
                break;
            case R.id.areaNum:
                DialogUtils.showInputDialog(this, "种植面积",
                        new DialogUtils.DialogOnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, String inputContent) {
                                areaNum.setText(inputContent);
                            }
                        });
                break;
            case R.id.btnGetTime:
                showTimePickerView();
                break;
            case R.id.btnPlantingTime:
                showPlantingTimePickerView();
                break;
            case R.id.btnAddress:
                showAreaPickerView();
                break;
            case R.id.btnCommit:
                commitApply();
                break;

        }
    }

    private void commitApply() {
        String cropName = tvCrop.getText().toString();
        if (TextUtils.isEmpty(cropName)) {
            ToastUtils.show("请输入种植作物名称");
            return;
        }
        String cropType = cropTypeName.getText().toString();
        if (TextUtils.isEmpty(cropType)) {
            ToastUtils.show("请输入作物品种");
            return;
        }
        String cropProperty = cropPropertyNames.getText().toString();
        if (TextUtils.isEmpty(cropProperty)) {
            ToastUtils.show("请输入作物特性");
            return;
        }
        String area = areaNum.getText().toString();
        if (TextUtils.isEmpty(area)) {
            ToastUtils.show("请输入种植面积");
            return;
        }
        String address = String.valueOf(arId);
        if (TextUtils.isEmpty(area)) {
            ToastUtils.show("请选择地址");
            return;
        }

        String sowingTime = tvGetTime.getText().toString();
        if (TextUtils.isEmpty(sowingTime)) {
            ToastUtils.show("请选择播种时间");
            return;
        }
        String planting = plantingTime.getText().toString();
        if (TextUtils.isEmpty(planting)) {
            ToastUtils.show("请选择种植时间");
            return;
        }


        applyFacilitiesAgriculture(cropName, cropType, cropProperty, area, address, sowingTime, planting);
    }

    private void showTimePickerView() {
        TimePickerView mTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        mTimePickerView.setTitle("选择日期");
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

        mTimePickerView.show();
    }

    private void showPlantingTimePickerView() {
        TimePickerView mTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        mTimePickerView.setTitle("选择日期");
        mTimePickerView.setTime(new Date());
        mTimePickerView.setCyclic(true);
        mTimePickerView.setCancelable(true);
        mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String time = format.format(date);
                plantingTime.setText(time);
            }
        });

        mTimePickerView.show();
    }

    private void showAreaPickerView() {
        if (null == mCityPickerView) {
            mCityPickerView = new OptionsPickerView(this);
            mCityPickerView.setTitle(getString(R.string.choose_area));
            mCityPickerView.setPicker(DFApplication.provinces, DFApplication.cities, countries, true);
            mCityPickerView.setCyclic(true, true, true);
            mCityPickerView.setSelectOptions(0, 0, 0);

            mCityPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    // 返回的分别是三个级别的选中位置
                    String tx = DFApplication.provinces.get(options1).pvName + "-" + DFApplication.cities.get(options1).get(option2).ctName + "-"
                            + countries.get(options1).get(option2).get(options3).arName;
                    tvAddress.setText(tx);
                    arId = DFApplication.countries.get(options1).get(option2).get(options3).arId;

                }
            });
        }
        mCityPickerView.show();
    }

    /**
     * 申请设施农业
     *
     * @param cropName
     * @param cropTypeName
     * @param cropPropertyNames
     * @param areaNum
     * @param arId
     * @param sowingTime
     * @param plantingTime
     */
    private void applyFacilitiesAgriculture(String cropName, String cropTypeName, String cropPropertyNames, String areaNum, String arId, String sowingTime, String plantingTime) {
        showDialog();
        DepthWebService.getInstance().applyFacilitiesAgriculture(cropName, cropTypeName, cropPropertyNames, areaNum, arId, sowingTime, plantingTime, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "applyFacilitiesAgriculture#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "applyFacilitiesAgriculture#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null != resJO && resJO.status == 0) {
                        DialogUtils.showCommitDialog(SettingFacilitiesActivity.this);
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "applyFacilitiesAgriculture#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }
}
