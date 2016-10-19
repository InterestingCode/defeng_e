package com.diesel.htweather.depthservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.response.DeepServiceDescResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


public class ProfileActivity extends BaseActivity {

    @BindView(R.id.tvDescContent)
    TextView tvDescContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        initDatas();
    }

    private void initDatas() {
        showDialog();
        DepthWebService.getInstance().getDeepServiceDesc(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getDeepServiceDesc#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getDeepServiceDesc#onResponse() " + response);
                dismissDialog();
                try {
                    DeepServiceDescResJO resJO = FastJsonUtils.getSingleBean(response, DeepServiceDescResJO.class);

                    if (null != resJO && resJO.status == 0) {
                        tvDescContent.setText(resJO.getObj().getContent());
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getDeepServiceDesc#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
