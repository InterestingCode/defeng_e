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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diesel.htweather.FarmingFragment;
import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.event.DeleteBannerEvent;
import com.diesel.htweather.event.RefreshFarmingDataEvent;
import com.diesel.htweather.farming.adapter.FarmingPagerAdapter;
import com.diesel.htweather.farming.model.WeatherDataBean;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.ViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    private static final String KEY_UNREAD_MSG_CNT = "key_unread_msg_cnt";

    @BindView(R.id.refresh_layout)
    BGARefreshLayout mRefreshLayout;

    @BindView(R.id.farming_data_view)
    RecyclerView mRecyclerView;

    TextView mCityNameTv;

    TextView mNewMsgCntTv;

    LinearLayout mPageIndicatorLayout;

    private List<BaseBean> mFarmingData = new ArrayList<>();

    private FarmingPagerAdapter mAdapter;

    public static FarmingPagerFragment newInstance(ArrayList<BaseBean> data, int unreadCnt) {
        FarmingPagerFragment fragment = new FarmingPagerFragment();
        Log.i("FarmingPagerFragment", "newInstance() fragmentId="+fragment.getId());
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_ARGS_DATA, data);
        args.putInt(KEY_UNREAD_MSG_CNT, unreadCnt);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach()..................");
        mAdapter = new FarmingPagerAdapter(mFarmingData);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()..................");
        View view = inflater.inflate(R.layout.pager_item_farming, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated()..................");
        EventBus.getDefault().register(this);
        View headerView = LayoutInflater.from(getContext())
                .inflate(R.layout.weather_page_header_layout,
                        (ViewGroup) getActivity().findViewById(android.R.id.content), false);
        mPageIndicatorLayout = (LinearLayout) headerView.findViewById(R.id.page_indicator_layout);
        mCityNameTv = (TextView) headerView.findViewById(R.id.city_name_tv);
        mNewMsgCntTv = (TextView) headerView.findViewById(R.id.new_msg_cnt_tv);
        headerView.findViewById(R.id.area_layout).setOnClickListener(this);
        headerView.findViewById(R.id.message_iv).setOnClickListener(this);
        mRefreshLayout.setCustomHeaderView(headerView, false);

        Bundle arguments = getArguments();
        if (null != arguments) {
            ArrayList<BaseBean> data = arguments.getParcelableArrayList(KEY_ARGS_DATA);
            if (null != data && !data.isEmpty()) {
                mFarmingData.clear();
                mFarmingData.addAll(data);

                BaseBean bean = mFarmingData.get(0);
                if (bean instanceof WeatherDataBean) {
                    mCityNameTv.setText(((WeatherDataBean) bean).arName);
                }
            }
            int unreadMsgCnt = arguments.getInt(KEY_UNREAD_MSG_CNT, 0);
            if (unreadMsgCnt > 0) {
                ViewUtils.visible(mNewMsgCntTv);
                mNewMsgCntTv.setText(String.valueOf(unreadMsgCnt));
            } else {
                ViewUtils.gone(mNewMsgCntTv);
            }
        }

        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(),
                false);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
        mRefreshLayout.setDelegate(this);

        mRecyclerView.addOnScrollListener(mScrollListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setData(ArrayList<BaseBean> data, int unreadCnt) {
        if (unreadCnt > 0) {
            ViewUtils.visible(mNewMsgCntTv);
            mNewMsgCntTv.setText(String.valueOf(unreadCnt));
        } else {
            ViewUtils.gone(mNewMsgCntTv);
        }

        if (null != data && !data.isEmpty()) {
            mFarmingData.clear();
            mFarmingData.addAll(data);

//            BaseBean bean = mFarmingData.get(0);
//            if (bean instanceof WeatherDataBean) {
//                mCityNameTv.setText(((WeatherDataBean) bean).arName);
//            }

            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()..................");
        EventBus.getDefault().unregister(this);
        if (null != mScrollListener) {
            mRecyclerView.removeOnScrollListener(mScrollListener);
        }
    }

    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                mRefreshLayout.setPullDownRefreshEnable(
                        ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() == 0);
            }
        }
    };

    public void onRefreshComplete() {
        if (null != mRefreshLayout) {
            mRefreshLayout.endRefreshing();
        }
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

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        EventBus.getDefault().post(new RefreshFarmingDataEvent());
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Subscribe
    public void onDeleteBannerEvent(DeleteBannerEvent event) {
        if (null != event.mAdvertiseBannerBean && mFarmingData.contains(event.mAdvertiseBannerBean)) {
            mFarmingData.remove(event.mAdvertiseBannerBean);
            mAdapter.notifyDataSetChanged();
        } else if (null != event.mActivityBannerBean && mFarmingData.contains(event.mActivityBannerBean)) {
            mFarmingData.remove(event.mActivityBannerBean);
            mAdapter.notifyDataSetChanged();
        }
    }

}
