package com.diesel.htweather.user;

import android.os.Bundle;
import android.view.View;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.widget.EditUserInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpCenterActivity extends BaseActivity {

    @BindView(R.id.common_problems_view)
    EditUserInfoView mCommonProblemsView;

    @BindView(R.id.operation_guide_view)
    EditUserInfoView mOperationGuideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.common_problems_view, R.id.operation_guide_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.common_problems_view:
                break;
            case R.id.operation_guide_view:
                break;
        }
    }
}
