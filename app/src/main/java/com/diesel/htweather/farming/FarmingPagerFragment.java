package com.diesel.htweather.farming;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.farming.adapter.FarmingPagerAdapter;
import com.diesel.htweather.farming.model.ActualFarmingBean;
import com.diesel.htweather.farming.model.FarmingBannerBean;
import com.diesel.htweather.farming.model.FarmingInfoBean;
import com.diesel.htweather.farming.model.FarmingPolicyBean;
import com.diesel.htweather.farming.model.WeatherDataBean;
import com.diesel.htweather.farming.model.WeatherPageHeaderBean;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    XRecyclerView mRecyclerView;

    @BindView(R.id.weather_page_header_layout)
    RelativeLayout mHeaderLayout;

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
//        mFarmingData.add(new WeatherPageHeaderBean());
        mFarmingData.add(new WeatherDataBean());
        mFarmingData.add(new ActualFarmingBean());
        mFarmingData.add(new FarmingBannerBean());
        mFarmingData.add(new FarmingInfoBean());
        mFarmingData.add(new FarmingPolicyBean());
        mFarmingData.add(new FarmingBannerBean());
        mAdapter = new FarmingPagerAdapter(mFarmingData);

        View header = LayoutInflater.from(getContext())
                .inflate(R.layout.weather_page_header_layout,
                        (ViewGroup) getActivity().findViewById(android.R.id.content), false);
        header.setVisibility(View.VISIBLE);
        mRecyclerView.addHeaderView(header);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = ((LinearLayoutManager) recyclerView
                        .getLayoutManager()).findFirstVisibleItemPosition();
                Log.v(TAG, "onScrolled() position=" + position + "; dx=" + dx + "; dy=" + dy);
                mHeaderLayout.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
            }
        });
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.loadMoreComplete();
                    }
                }, 2000);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.area_layout, R.id.message_iv})
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
