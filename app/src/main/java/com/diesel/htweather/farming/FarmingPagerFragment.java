package com.diesel.htweather.farming;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diesel.htweather.FarmingFragment;
import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.farming.adapter.FarmingPagerAdapter;
import com.diesel.htweather.farming.model.WeatherDataBean;
import com.diesel.htweather.util.ActivityNav;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

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
        mAdapter.notifyDataSetChanged();
        Log.e(TAG, "setData().....................");

        WeatherDataBean weatherDataBean = (WeatherDataBean) mFarmingData.get(0);
        nameTv.setText(weatherDataBean.arName);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach().....................Arguments="+getArguments());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate().....................Arguments="+getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_item_farming, container, false);
        ButterKnife.bind(this, view);
        Log.e(TAG, "onCreateView().....................Arguments="+getArguments());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated().....................Arguments="+getArguments());
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

        Bundle arguments = getArguments();
        if (null != arguments) {
            ArrayList<BaseBean> data = arguments.getParcelableArrayList(KEY_ARGS_DATA);
            if (null != data && !data.isEmpty()) {
                mFarmingData.addAll(data);

                WeatherDataBean weatherDataBean = (WeatherDataBean) mFarmingData.get(0);
                nameTv.setText(weatherDataBean.arName);
            }
        }

        mAdapter = new FarmingPagerAdapter(mFarmingData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<BaseBean> data = ((FarmingFragment) getParentFragment()).getData(0);
        Log.i(TAG, "onActivityCreated().....................Arguments="+getArguments()+"; data="+data);
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
