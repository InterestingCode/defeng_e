package com.diesel.htweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.event.RefreshFarmingDataEvent;
import com.diesel.htweather.farming.FarmingPagerFragment;
import com.diesel.htweather.farming.model.ActivityBannerBean;
import com.diesel.htweather.farming.model.ActualFarmingBean;
import com.diesel.htweather.farming.model.AdvertiseBannerBean;
import com.diesel.htweather.farming.model.FarmingInfoBean;
import com.diesel.htweather.farming.model.FarmingPolicyBean;
import com.diesel.htweather.farming.model.WeatherDataBean;
import com.diesel.htweather.response.FarmingResJO;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.util.ViewUtils;
import com.diesel.htweather.webapi.AreaWebService;
import com.diesel.htweather.widget.CusViewPager;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import okhttp3.Call;

/**
 * Comments：农事
 *
 * @author Diesel
 *         Time: 2016/8/13
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class FarmingFragment extends BaseFragment {

    @BindView(R.id.weather_pager)
    CusViewPager mWeatherPager;

    private ArrayList<ArrayList<BaseBean>> mFarmingData = new ArrayList<>();

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private List<Integer> mFragmentIds = new ArrayList<>();

    private MainPagerAdapter mAdapter = null;

    private int mCurrIndex;

    public static FarmingFragment newInstance() {
        return new FarmingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_farming, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);

        mAdapter = new MainPagerAdapter(getChildFragmentManager());
        mAdapter.setList(mFragments);
        mWeatherPager.setAdapter(mAdapter);
        mWeatherPager.addOnPageChangeListener(mListener);

        getFocusAreaFarmingData(false);
    }

    private void getFocusAreaFarmingData(final boolean isRefresh) {
        showDialog();
        AreaWebService.getInstance().getFocusAreaFarmingData(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getFocusAreaFarmingData#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
                if (isRefresh) {
                    ((FarmingPagerFragment) mFragments.get(mCurrIndex)).onRefreshComplete();
                }
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getFocusAreaFarmingData#onResponse() " + response);
                dismissDialog();
                if (isRefresh) {
                    ((FarmingPagerFragment) mFragments.get(mCurrIndex)).onRefreshComplete();
                }
                try {
                    FarmingResJO resJO = FastJsonUtils.getSingleBean(response, FarmingResJO.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    FarmingResJO.ObjEntity obj = resJO.obj;
                    if (resJO.status == 0 && null != obj) {
                        if (obj.count == 0) {
                            ToastUtils.show("您还没有关注地区，请先关注地区");
                            ActivityNav.getInstance().startLocationActivity(mActivity);
                            return;
                        }

                        mFarmingData.clear();

                        // 实况数据+精准农技
                        List<FarmingResJO.ObjEntity.WeatherCropCollEntity> weatherCropColl
                                = resJO.obj.weatherCropColl;
                        if (null != weatherCropColl && !weatherCropColl.isEmpty()) {
                            for (int i = 0; i < weatherCropColl.size(); i++) {
                                ArrayList<BaseBean> baseBeen = new ArrayList<>();
                                WeatherDataBean weatherDataBean = new WeatherDataBean();
                                ActualFarmingBean actualFarmingBean = new ActualFarmingBean();

                                FarmingResJO.ObjEntity.WeatherCropCollEntity weatherCropCollEntity = weatherCropColl.get(i);
                                weatherDataBean.arId = weatherCropCollEntity.arId;
                                weatherDataBean.arName = weatherCropCollEntity.arName;
                                weatherDataBean.tips = obj.tips;
                                // 用户关注区域7天天气情况
                                weatherDataBean.dayWeatherList = weatherCropCollEntity.dayWeatherList;
                                // 实况数据
                                weatherDataBean.hoursDataList = weatherCropCollEntity.hoursDataList;
                                // 精准农技
                                actualFarmingBean.areaId = weatherCropCollEntity.arId;
                                actualFarmingBean.mTimelyCropsNewsListEntities = weatherCropCollEntity.timelyCropsNewsList;

                                baseBeen.add(weatherDataBean);
                                baseBeen.add(actualFarmingBean);
                                mFarmingData.add(baseBeen);
                            }
                        }

                        // 中间部分广告图
                        List<FarmingResJO.ObjEntity.AdvertiseListEntity> advertiseList
                                = obj.advertiseList;
                        if (null != advertiseList && !advertiseList.isEmpty()) {
                            AdvertiseBannerBean bannerBean = new AdvertiseBannerBean();
                            bannerBean.advertiseEntity = advertiseList.get(0);
                            for (int i = 0; i < obj.count; i++) {
                                mFarmingData.get(i).add(bannerBean);
                            }
                        }

                        // 农气情报
                        List<FarmingResJO.ObjEntity.ArticleCropsNewsEntity> articleCropsNews
                                = resJO.obj.articleCropsNews;
                        if (null != articleCropsNews && !articleCropsNews.isEmpty()) {
                            for (int i = 0; i < obj.count; i++) {
                                FarmingInfoBean farmingInfo = new FarmingInfoBean();
                                farmingInfo.convertArticleCropsNewsEntity(articleCropsNews.get(0));
                                WeatherDataBean bean = (WeatherDataBean) mFarmingData.get(i).get(0);
                                farmingInfo.areaId = bean.arId;
                                mFarmingData.get(i).add(farmingInfo);
                            }
                        }

                        // 农业政策
                        List<FarmingResJO.ObjEntity.PolcyCropsNewsEntity> polcyCropsNews
                                = obj.polcyCropsNews;
                        if (null != polcyCropsNews && !polcyCropsNews.isEmpty()) {
                            for (int i = 0; i < obj.count; i++) {
                                FarmingPolicyBean farmingPolicy = new FarmingPolicyBean();
                                farmingPolicy.convertPolcyCropsNewsEntity(polcyCropsNews.get(0));
                                WeatherDataBean bean = (WeatherDataBean) mFarmingData.get(i).get(0);
                                farmingPolicy.areaId = bean.arId;
                                mFarmingData.get(i).add(farmingPolicy);
                            }
                        }

                        // 下面部分活动图
                        List<FarmingResJO.ObjEntity.ActivityListEntity> activityList
                                = obj.activityList;
                        if (null != activityList && !activityList.isEmpty()) {
                            ActivityBannerBean bannerBean = new ActivityBannerBean();
                            bannerBean.activityEntity = activityList.get(0);
                            for (int i = 0; i < obj.count; i++) {
                                mFarmingData.get(i).add(bannerBean);
                            }
                        }

                        List<Integer> tempFragmentIds = new ArrayList<>();
                        tempFragmentIds.addAll(mFragmentIds);
                        mFragmentIds.clear();
                        mFragments.clear();
                        for (int i = 0; i < mFarmingData.size(); i++) {
                            FarmingPagerFragment fragment = FarmingPagerFragment.newInstance(mFarmingData.get(i), resJO.obj.unreadCounts);
//                            if (tempFragmentIds.contains(fragment.getId())) {
//                                fragment.setData(mFarmingData.get(i), resJO.obj.unreadCounts);
//                            }
                            mFragments.add(fragment);
                            mFragmentIds.add(fragment.getId());
                        }
                        mAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
//                    Log.e(TAG, "getFocusAreaFarmingData#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }

    @Subscribe
    public void onRefreshFarmingDataEvent(RefreshFarmingDataEvent event) {
        getFocusAreaFarmingData(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mWeatherPager.removeOnPageChangeListener(mListener);
    }

//    @OnClick({R.id.area_layout, R.id.message_iv, R.id.new_msg_cnt_tv})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.area_layout:
//                ActivityNav.getInstance().startCityManageActivity(mActivity);
//                break;
//            case R.id.message_iv:
//            case R.id.new_msg_cnt_tv:
//                ActivityNav.getInstance().startMessageActivity(mActivity);
//                break;
//        }
//    }

    private ViewPager.OnPageChangeListener mListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mCurrIndex = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
