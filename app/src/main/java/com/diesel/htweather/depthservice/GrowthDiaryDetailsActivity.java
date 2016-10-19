package com.diesel.htweather.depthservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.depthservice.model.GrowthDiaryBean;
import com.diesel.htweather.response.GrowthDiaryDetailsResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


public class GrowthDiaryDetailsActivity extends BaseActivity {

    @BindView(R.id.tvGrowthDetailTitle)
    TextView tvGrowthDetailTitle;

    @BindView(R.id.tvGrowthDetailTime)
    TextView tvGrowthDetailTime;

    @BindView(R.id.tvGrowthDetailReadNum)
    TextView tvGrowthDetailReadNum;

    @BindView(R.id.tvGrowthDetailContent)
    TextView tvGrowthDetailContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_growth_diary_details);
        ButterKnife.bind(this);
        String growthId = getIntent().getStringExtra("growthId");
        getGrowthDiaryDetails(growthId);
    }

    private void getGrowthDiaryDetails(String growthId) {
        showDialog();
        DepthWebService.getInstance().getGrowthDiaryDetails(growthId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getGrowthDiaryDetails#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getGrowthDiaryDetails#onResponse() " + response);
                dismissDialog();
                try {
                    GrowthDiaryDetailsResJO resJO = FastJsonUtils.getSingleBean(response, GrowthDiaryDetailsResJO.class);
                    if (null != resJO && resJO.status == 0) {
                        GrowthDiaryBean bean = resJO.getObj();
                        tvGrowthDetailTitle.setText(bean.getTitle());
                        tvGrowthDetailTime.setText("德丰e农 " + bean.getTime());
                        tvGrowthDetailReadNum.setText("阅读: " + bean.getReadCount());
                        tvGrowthDetailContent.setText(bean.getContent());
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getGrowthDiaryDetails#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
