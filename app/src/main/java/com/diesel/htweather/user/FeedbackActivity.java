package com.diesel.htweather.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.response.BaseResJo;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.UserWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.advice_et)
    EditText mAdviceEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.commit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.commit_btn:
                String content = mAdviceEt.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.show(getString(R.string.tips_empty_advices));
                    return;
                }
                feedbackAdvice(content);
                break;
        }
    }

    private void feedbackAdvice(String content) {
        showDialog();
        UserWebService.getInstance().feedbackAdvise(content, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "feedbackAdvice#onError() " + e.getMessage());
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "feedbackAdvice#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJo resJO = FastJsonUtils.getSingleBean(response, BaseResJo.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    if (resJO.status != 0) {
                        ToastUtils.show(resJO.msg);
                    } else {
                        ToastUtils.show(getString(R.string.tips_thanks_for_your_advices));
                        finish();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "feedbackAdvice#onResponse() " + e.getMessage());
                }
            }
        });
    }
}
