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

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.constant.Api;
import com.diesel.htweather.constant.Consts;
import com.diesel.htweather.farming.adapter.FarmingPagerAdapter;
import com.diesel.htweather.farming.model.ActualFarmingBean;
import com.diesel.htweather.farming.model.FarmingBannerBean;
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

    @BindView(R.id.refresh_layout)
    BGARefreshLayout mRefreshLayout;

    @BindView(R.id.farming_data_view)
    RecyclerView mRecyclerView;

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

        View headerView = LayoutInflater.from(getContext())
                .inflate(R.layout.weather_page_header_layout,
                        (ViewGroup) getActivity().findViewById(android.R.id.content), false);
        headerView.findViewById(R.id.area_layout).setOnClickListener(this);
        headerView.findViewById(R.id.message_iv).setOnClickListener(this);
        mRefreshLayout.setCustomHeaderView(headerView, false);
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(),
                false);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
        mRefreshLayout.setDelegate(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

//        getFarmingData();
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

    private void getFarmingData() {
        OkHttpUtils
                .get()
                .url(Api.FARMING_URL)
                .addParams("drivenType", "02")
                .addParams("appkey", "b66a5c46acf46c10a601bc8cabe4c074")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "getFarmingData#onError() " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "getFarmingData#onResponse() " + response);
                        try {
                            FarmingResJO resJO = FastJsonUtils
                                    .getSingleBean(response, FarmingResJO.class);
                            if (null == resJO) {
                                return;
                            }
                            if (resJO.status != 0 || null == resJO.obj) {
                                ToastUtils.show(resJO.msg);
                                return;
                            }

                            mFarmingData.clear();
                            List<FarmingResJO.ObjEntity.WeatherCropCollEntity>
                                    weatherCropColl = resJO.obj.weatherCropColl;
                            if (null != weatherCropColl && !weatherCropColl.isEmpty()) {
                                for (int i = 0; i < weatherCropColl.size(); i++) {
                                    // FIXME:当前只添加一个页面的数据
                                    if (weatherCropColl.get(i).arId > 1) break;
                                    // 区域天气情况
                                    List<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity>
                                            dayWeatherList = weatherCropColl.get(i).dayWeatherList;
                                    if (null != dayWeatherList && !dayWeatherList.isEmpty()) {
                                        for (int m = 0; m < dayWeatherList.size(); m++) {
                                            FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity
                                                    dayWeatherListEntity = dayWeatherList.get(m);
                                            // FIXME:当前数据为测试数据
                                            if ("2016-04-13"
                                                    .equals(dayWeatherListEntity.currDate)) {
                                                WeatherDataBean weatherData = new WeatherDataBean();
                                                weatherData.convertDayWeatherListEntity(
                                                        dayWeatherListEntity);
                                                mFarmingData.add(0, weatherData);
                                            }
                                        }
                                    }
                                    // 精准农技信息
                                    List<FarmingResJO.ObjEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity>
                                            timelyCropsNewsList = weatherCropColl.get(i).timelyCropsNewsList;
                                    if (null != timelyCropsNewsList && !timelyCropsNewsList.isEmpty()) {
                                        ActualFarmingBean actualFarming = new ActualFarmingBean();
                                        actualFarming.mTimelyCropsNewsListEntities = timelyCropsNewsList;
                                        mFarmingData.add(actualFarming);
                                    }
                                }

                            }
                            // 农气情报
                            List<FarmingResJO.ObjEntity.ArticleCropsNewsEntity>
                                    articleCropsNews = resJO.obj.articleCropsNews;
                            if (null != articleCropsNews && !articleCropsNews.isEmpty()) {
                                FarmingInfoBean farmingInfo = new FarmingInfoBean();
                                farmingInfo.convertArticleCropsNewsEntity(articleCropsNews.get(0));
                                mFarmingData.add(farmingInfo);
                            }
                            // 农业政策
                            List<FarmingResJO.ObjEntity.PolcyCropsNewsEntity>
                                    polcyCropsNews = resJO.obj.polcyCropsNews;
                            if (null != polcyCropsNews && !polcyCropsNews.isEmpty()) {
                                FarmingPolicyBean farmingPolicy = new FarmingPolicyBean();
                                farmingPolicy.convertPolcyCropsNewsEntity(polcyCropsNews.get(0));
                                mFarmingData.add(farmingPolicy);
                            }
                            mAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
