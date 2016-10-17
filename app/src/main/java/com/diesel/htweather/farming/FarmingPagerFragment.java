package com.diesel.htweather.farming;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.constant.Api;
import com.diesel.htweather.farming.adapter.FarmingPagerAdapter;
import com.diesel.htweather.farming.model.ActualFarmingBean;
import com.diesel.htweather.farming.model.FarmingInfoBean;
import com.diesel.htweather.farming.model.FarmingPolicyBean;
import com.diesel.htweather.farming.model.WeatherDataBean;
import com.diesel.htweather.response.FarmingResJO;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import okhttp3.Call;

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
public class FarmingPagerFragment extends BaseFragment
        implements BGARefreshLayout.BGARefreshLayoutDelegate, View.OnClickListener {

    private static final String KEY_ARGS_DATA = "key_args_data";

    @BindView(R.id.refresh_layout)
    BGARefreshLayout mRefreshLayout;

    @BindView(R.id.farming_data_view)
    RecyclerView mRecyclerView;

    private TextView nameTv, msgCntTv;

    private FarmingPagerAdapter mAdapter;

    private List<BaseBean> mFarmingData = new ArrayList<>();

    public static FarmingPagerFragment newInstance(ArrayList<BaseBean> data) {
        FarmingPagerFragment fragment = new FarmingPagerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_ARGS_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    public static FarmingPagerFragment newInstance() {
        return new FarmingPagerFragment();
    }

    public void setData(List<BaseBean> data) {
        mFarmingData.clear();
        mFarmingData.addAll(data);
        Log.e(TAG, "setData().....................");

//        WeatherDataBean weatherDataBean = (WeatherDataBean) mFarmingData.get(0);
//        nameTv.setText(weatherDataBean.arName);
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
        Log.e(TAG, "onViewCreated().....................");
        View headerView = LayoutInflater.from(getContext())
                .inflate(R.layout.weather_page_header_layout,
                        (ViewGroup) getActivity().findViewById(android.R.id.content), false);
        headerView.findViewById(R.id.area_layout).setOnClickListener(this);
        headerView.findViewById(R.id.message_iv).setOnClickListener(this);
        nameTv = (TextView) headerView.findViewById(R.id.city_name_tv);
        msgCntTv = (TextView) headerView.findViewById(R.id.new_msg_cnt_tv);
        mRefreshLayout.setCustomHeaderView(headerView, false);
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(),
                false);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
        mRefreshLayout.setDelegate(this);

        mAdapter = new FarmingPagerAdapter(mFarmingData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
            }
        }, 2000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.area_layout:
                ActivityNav.getInstance().startCityManageActivity(mActivity);
                break;
            case R.id.message_iv:
                ActivityNav.getInstance().startMessageActivity(mActivity);
                break;
        }
    }

}
