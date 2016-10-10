package com.diesel.htweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.farming.FarmingPagerFragment;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.response.FarmingResJO;
import com.diesel.htweather.response.FocusAreaResJO;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.AreaWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
                    if (null != resJO && resJO.status == 0) {

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
