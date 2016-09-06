package com.diesel.htweather.user;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                ToastUtils.show(getString(R.string.tips_thanks_for_your_advices));
                finish();
                break;
        }
    }
}
