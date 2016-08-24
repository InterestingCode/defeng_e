package com.diesel.htweather.farming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.farming.adapter.FarmingPagerAdapter;
import com.diesel.htweather.farming.model.ActualFarmingBean;
import com.diesel.htweather.farming.model.FarmingBannerBean;
import com.diesel.htweather.farming.model.FarmingInfoBean;
import com.diesel.htweather.farming.model.FarmingPolicyBean;
import com.diesel.htweather.farming.model.WeatherDataBean;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/22
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class FarmingPagerFragment extends BaseFragment {

    @BindView(R.id.farming_data_view)
    XRecyclerView mFarmingDataView;

    private FarmingPagerAdapter mAdapter;

    private List<BaseBean> mFarmingData = new ArrayList<>();

    public static FarmingPagerFragment newInstance() {
        return new FarmingPagerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_item_farming, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFarmingData.add(new WeatherDataBean());
        mFarmingData.add(new ActualFarmingBean());
        mFarmingData.add(new FarmingBannerBean());
        mFarmingData.add(new FarmingInfoBean());
        mFarmingData.add(new FarmingPolicyBean());
        mFarmingData.add(new FarmingBannerBean());
        mAdapter = new FarmingPagerAdapter(mFarmingData);

        mFarmingDataView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFarmingDataView.addItemDecoration(new DividerItemDecoration(getContext()));
        mFarmingDataView.setAdapter(mAdapter);
    }
}
