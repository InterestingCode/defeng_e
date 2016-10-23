package com.diesel.htweather.depthservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.depthservice.model.SuggestBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 农事建议
 */
public class FarmingAdviceActivity extends BaseActivity {


    @BindView(R.id.farming_title_tv)
    TextView tvSuggestTitle;

    @BindView(R.id.farming_time_and_source_tv)
    TextView tvSuggestTime;

    @BindView(R.id.tvSuggestContent)
    TextView tvSuggestContent;

    SuggestBean suggestBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farming_advice);
        ButterKnife.bind(this);
        suggestBean = (SuggestBean) getIntent().getSerializableExtra("suggestBean");
        tvSuggestTitle.setText(suggestBean.getTitle());
        tvSuggestTime.setText(suggestBean.getSendTime());
        tvSuggestContent.setText(suggestBean.getDesc());
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
