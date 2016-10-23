package com.diesel.htweather.depthservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.depthservice.model.FacilitiesBean;
import com.diesel.htweather.response.FacilitiesDetailsResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by mac14 on 16/9/6.
 */
public class FacilitiesDetailsActivity extends BaseActivity {

    @BindView(R.id.tvCropName)
    TextView tvCropName;

    @BindView(R.id.tvCropTypeName)
    TextView tvCropTypeName;

    @BindView(R.id.tvCropPropertyNames)
    TextView tvCropPropertyNames;

    @BindView(R.id.tvAreaNum)
    TextView tvAreaNum;

    @BindView(R.id.tvHouseAddr)
    TextView tvHouseAddr;

    @BindView(R.id.tvCropAddress)
    TextView tvCropAddress;

    @BindView(R.id.tvSowingTime)
    TextView tvSowingTime;

    @BindView(R.id.tvPlantingTime)
    TextView tvPlantingTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_details);
        ButterKnife.bind(this);
        String csId = getIntent().getStringExtra("csId");
        getFacilitiesAgricultureDetails(csId);
    }

    /**
     * @param csId
     */
    private void getFacilitiesAgricultureDetails(String csId) {
        showDialog();
        DepthWebService.getInstance().getFacilitiesAgricultureDetails(csId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getFacilitiesAgricultureDetails#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getFacilitiesAgricultureDetails#onResponse() " + response);
                dismissDialog();
                try {
                    FacilitiesDetailsResJO resJO = FastJsonUtils.getSingleBean(response, FacilitiesDetailsResJO.class);
                    if (null != resJO && resJO.status == 0) {
                        FacilitiesBean bean = resJO.getObj();
                        tvCropName.setText(bean.getCropName());
                        tvCropTypeName.setText(bean.getCropTypeName());
                        tvCropPropertyNames.setText(bean.getCropPropertyNames());
                        tvAreaNum.setText(bean.getAreaNum());
                        tvHouseAddr.setText(bean.getHouseAddr());
                        tvCropAddress.setText(bean.getAddr());
                        tvSowingTime.setText(bean.getSowingTime());
                        tvPlantingTime.setText(bean.getPlantingTime());
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getFacilitiesAgricultureDetails#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }


    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
