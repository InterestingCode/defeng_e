package com.diesel.htweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.depthservice.AgricultureDoctorActivity;
import com.diesel.htweather.depthservice.FacilitiesActivity;
import com.diesel.htweather.depthservice.FacilitiesDetailsActivity;
import com.diesel.htweather.depthservice.FarmingAdviceActivity;
import com.diesel.htweather.depthservice.GrowthDiaryActivity;
import com.diesel.htweather.depthservice.GrowthDiaryDetailsActivity;
import com.diesel.htweather.depthservice.OnlineAdvisoryActivity;
import com.diesel.htweather.depthservice.ProfileActivity;
import com.diesel.htweather.depthservice.SettingFacilitiesActivity;
import com.diesel.htweather.depthservice.adapter.DepthDiaryAdapter;
import com.diesel.htweather.depthservice.model.GrowthDiaryBean;
import com.diesel.htweather.widget.FullListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Comments：深度服务
 *
 * @author Diesel
 *         Time: 2016/8/13
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class DepthServiceFragment extends BaseFragment {

    @BindView(R.id.diaryList)
    FullListView diaryList;

    @BindView(R.id.btnProfile)
    FrameLayout mBtnProfile;

    @BindView(R.id.mScrollView)
    ScrollView mScrollView;


    public static DepthServiceFragment newInstance() {
        return new DepthServiceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_depth_service, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {

        List<GrowthDiaryBean> data = new ArrayList<GrowthDiaryBean>();
        for (int i = 0; i < 3; i++) {
            data.add(new GrowthDiaryBean());
        }
        diaryList.setAdapter(new DepthDiaryAdapter(mActivity, data));
    }

    @OnClick({R.id.tvDepthFacilities, R.id.tvAgriculturalFacilities, R.id.tvAgriculture, R.id.btnDepthDiary, R.id.btnJust,
            R.id.btnAsk, R.id.btnFarming, R.id.btnProfile, R.id.tvDepthClose, R.id.tvReturnTop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvDepthFacilities:
                Intent intent = new Intent(mActivity, FacilitiesActivity.class);
                startActivity(intent);
                break;
            case R.id.tvAgriculturalFacilities:
                startActivity(new Intent(mActivity, FacilitiesDetailsActivity.class));
                break;
            case R.id.tvAgriculture:
                startActivity(new Intent(mActivity, AgricultureDoctorActivity.class));
                break;
            case R.id.btnDepthDiary:
                startActivity(new Intent(mActivity, GrowthDiaryActivity.class));
                break;
            case R.id.btnJust:
                startActivity(new Intent(mActivity, SettingFacilitiesActivity.class));
                break;
            case R.id.btnAsk:
                startActivity(new Intent(mActivity, OnlineAdvisoryActivity.class));
                break;
            case R.id.btnFarming:
                startActivity(new Intent(mActivity, FarmingAdviceActivity.class));
                break;
            case R.id.btnProfile:
                startActivity(new Intent(mActivity, ProfileActivity.class));
                break;
            case R.id.tvDepthClose:
                mBtnProfile.setVisibility(View.GONE);
                break;
            case R.id.tvReturnTop:
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
                break;


            default:
                break;
        }
    }

    @OnItemClick({R.id.diaryList})
    public void onItemClick() {
        startActivity(new Intent(mActivity, GrowthDiaryDetailsActivity.class));
    }
}
