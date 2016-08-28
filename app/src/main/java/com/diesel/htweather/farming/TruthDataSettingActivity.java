package com.diesel.htweather.farming;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.farming.adapter.TruthDataSettingAdapter;
import com.diesel.htweather.farming.model.TruthDataBean;
import com.diesel.htweather.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TruthDataSettingActivity extends BaseActivity {

    @BindView(R.id.truth_data_recycler_view)
    RecyclerView mRecyclerView;

    private List<TruthDataBean> mTruthData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truth_data_setting);
        ButterKnife.bind(this);

        addTestData();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST,
                        R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setAdapter(new TruthDataSettingAdapter(mTruthData));
    }

    private void addTestData() {
        TruthDataBean bean1 = new TruthDataBean();
        bean1.name = "气温(℃)";
        bean1.highestValue = 100;
        bean1.lowestValue = 20;

        TruthDataBean bean2 = new TruthDataBean();
        bean2.name = "降水(mm)";
        bean2.highestValue = 98;
        bean2.lowestValue = 12;

        TruthDataBean bean3 = new TruthDataBean();
        bean3.name = "土壤温度(℃)";
        bean3.highestValue = 34;
        bean3.lowestValue = 1;

        TruthDataBean bean4 = new TruthDataBean();
        bean4.name = "土壤湿度(%)";
        bean4.highestValue = 56;
        bean4.lowestValue = 24;

        TruthDataBean bean5 = new TruthDataBean();
        bean5.name = "空气湿度(%)";
        bean5.highestValue = 100;
        bean5.lowestValue = 20;

        TruthDataBean bean6 = new TruthDataBean();
        bean6.name = "日照(min)";
        bean6.highestValue = 100;
        bean6.lowestValue = 20;

        TruthDataBean bean7 = new TruthDataBean();
        bean7.name = "风力(m/s)";
        bean7.highestValue = 100;
        bean7.lowestValue = 20;

        mTruthData.add(bean1);
        mTruthData.add(bean2);
        mTruthData.add(bean3);
        mTruthData.add(bean4);
        mTruthData.add(bean5);
        mTruthData.add(bean6);
        mTruthData.add(bean7);
    }

    @OnClick({R.id.back_btn, R.id.save_setting_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.save_setting_layout:
                finish();
                break;
        }
    }
}
