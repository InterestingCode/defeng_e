package com.diesel.htweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.farming.FarmingPagerFragment;
import com.diesel.htweather.farming.model.FarmingInfoBean;
import com.diesel.htweather.farming.model.FarmingPolicyBean;
import com.diesel.htweather.response.FarmingResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.AreaWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    ViewPager mWeatherPager;

    private List<List<BaseBean>> mFarmingData = new ArrayList<>();

    private ArrayList<Fragment> mFragments = new ArrayList<>();

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
        mFragments.add(FarmingPagerFragment.newInstance());
        MainPagerAdapter adapter = new MainPagerAdapter(getChildFragmentManager());
        adapter.setList(mFragments);
        mWeatherPager.setAdapter(adapter);

        getFocusAreaFarmingData();
    }

    private int mUnreadMsgCnt;

    private void getFocusAreaFarmingData() {
        showDialog();
        AreaWebService.getInstance().getFocusAreaFarmingData(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getFocusAreaFarmingData#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getFocusAreaFarmingData#onResponse() " + response);
                dismissDialog();
                try {
                    FarmingResJO resJO = FastJsonUtils.getSingleBean(response, FarmingResJO.class);
                    if (null == resJO) {

                        return;
                    }
                    FarmingResJO.ObjEntity obj = resJO.obj;
                    if (resJO.status == 0 && null != obj) {
                        // 未读消息数
                        mUnreadMsgCnt = obj.unreadCounts;

                        // 实况数据+精准农技
                        List<FarmingResJO.ObjEntity.WeatherCropCollEntity> weatherCropColl = resJO.obj.weatherCropColl;
                        if (null != weatherCropColl && !weatherCropColl.isEmpty()) {
                            for (int i = 0; i < weatherCropColl.size(); i++) {
                                List<BaseBean> baseBeen = new ArrayList<>();

                                FarmingResJO.ObjEntity.WeatherCropCollEntity weatherCropCollEntity = weatherCropColl.get(i);
                                // 用户关注区域7天天气情况
                                List<FarmingResJO.ObjEntity.WeatherCropCollEntity.DayWeatherListEntity> dayWeatherList = weatherCropCollEntity.dayWeatherList;
                                // 实况数据
                                List<FarmingResJO.ObjEntity.WeatherCropCollEntity.HoursDataListEntity> hoursDataList = weatherCropCollEntity.hoursDataList;
                                // 精准农技
                                List<FarmingResJO.ObjEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity> timelyCropsNewsList = weatherCropCollEntity.timelyCropsNewsList;
                            }
                        }

                        // 中间部分广告图
                        List<FarmingResJO.ObjEntity.AdvertiseListEntity> advertiseList = obj.advertiseList;
                        if (null != advertiseList && !advertiseList.isEmpty()) {

                        }

                        // 农气情报
                        List<FarmingResJO.ObjEntity.ArticleCropsNewsEntity>
                                articleCropsNews = resJO.obj.articleCropsNews;
                        if (null != articleCropsNews && !articleCropsNews.isEmpty()) {
                            FarmingInfoBean farmingInfo = new FarmingInfoBean();
                            farmingInfo.convertArticleCropsNewsEntity(articleCropsNews.get(0));
//                            mFarmingData.add(farmingInfo);
                        }

                        // 农业政策
                        List<FarmingResJO.ObjEntity.PolcyCropsNewsEntity> polcyCropsNews = obj.polcyCropsNews;
                        if (null != polcyCropsNews && !polcyCropsNews.isEmpty()) {
                            FarmingPolicyBean farmingPolicy = new FarmingPolicyBean();
                            farmingPolicy.convertPolcyCropsNewsEntity(polcyCropsNews.get(0));
//                            mFarmingData.add(farmingPolicy);
                        }

                        // 下面部分活动图
                        List<FarmingResJO.ObjEntity.ActivityListEntity> activityList = obj.activityList;
                        if (null != activityList && !activityList.isEmpty()) {

                        }

                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getFocusAreaFarmingData#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }

}
