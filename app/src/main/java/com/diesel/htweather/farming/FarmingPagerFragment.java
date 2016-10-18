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
public class FarmingPagerFragment extends BaseFragment {

    private static final String KEY_ARGS_DATA = "key_args_data";

    @BindView(R.id.farming_data_view)
    RecyclerView mRecyclerView;

    private List<BaseBean> mFarmingData = new ArrayList<>();

    public static FarmingPagerFragment newInstance(ArrayList<BaseBean> data) {
        FarmingPagerFragment fragment = new FarmingPagerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_ARGS_DATA, data);
        fragment.setArguments(args);
        return fragment;
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
        Bundle arguments = getArguments();
        if (null != arguments) {
            ArrayList<BaseBean> data = arguments.getParcelableArrayList(KEY_ARGS_DATA);
            if (null != data && !data.isEmpty()) {
                mFarmingData.clear();
                mFarmingData.addAll(data);
            }
        }

        FarmingPagerAdapter mAdapter = new FarmingPagerAdapter(mFarmingData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mScrollListener) {
            mRecyclerView.removeOnScrollListener(mScrollListener);
        }
    }

    public void addOnScrollListener() {
        if (null != mScrollListener) {
            mRecyclerView.addOnScrollListener(mScrollListener);
        }
    }

    public void removeOnScrollListener() {
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
            int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0)
                    ? 0 : recyclerView.getChildAt(0).getTop();
            Log.d(TAG, "onScrolled() topRowVerticalPosition=" + topRowVerticalPosition);
            ((FarmingFragment) getParentFragment()).mRefreshLayout
                    .setPullDownRefreshEnable(topRowVerticalPosition >= 0);
        }
    };

}
