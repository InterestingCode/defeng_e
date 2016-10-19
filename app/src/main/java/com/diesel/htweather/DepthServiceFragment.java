package com.diesel.htweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
import com.diesel.htweather.depthservice.model.DoctorBean;
import com.diesel.htweather.depthservice.model.GrowthDiaryBean;
import com.diesel.htweather.depthservice.model.SuggestBean;
import com.diesel.htweather.response.AgriculturalDoctorResJO;
import com.diesel.htweather.response.AgriculturalSuggestResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.diesel.htweather.widget.FullListView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import okhttp3.Call;

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

    @BindView(R.id.tvDoctorTitle)
    TextView tvDoctorTitle;

    @BindView(R.id.tvDoctorTime)
    TextView tvAgricultureTime;

    @BindView(R.id.tvDoctorContent)
    TextView tvDoctorContent;


    @BindView(R.id.tvSuggestTitle)
    TextView tvSuggestTitle;

    @BindView(R.id.tvSuggestTime)
    TextView tvSuggestTime;

    @BindView(R.id.tvSuggestContent)
    TextView tvSuggestContent;


    DoctorBean doctorBean;

    SuggestBean suggestBean;


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

        // 获取农业小博士信息
        getAgricultureDoctor();

        // 农事建议
        getAgriculturalSuggest();

        List<GrowthDiaryBean> data = new ArrayList<GrowthDiaryBean>();
        for (int i = 0; i < 3; i++) {
            data.add(new GrowthDiaryBean());
        }
        diaryList.setAdapter(new DepthDiaryAdapter(mActivity, data));
    }

    @OnClick({R.id.tvDepthFacilities, R.id.tvAgriculturalFacilities, R.id.agriculture_ll, R.id.btnDepthDiary, R.id.btnJust,
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
            case R.id.agriculture_ll:
                if (null != doctorBean) {
                    Intent doctorIntent = new Intent(mActivity, AgricultureDoctorActivity.class);
                    doctorIntent.putExtra("doctorBean", doctorBean);
                    startActivity(doctorIntent);
                }
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
                if (null != suggestBean) {
                    Intent doctorIntent = new Intent(mActivity, FarmingAdviceActivity.class);
                    doctorIntent.putExtra("suggestBean", suggestBean);
                    startActivity(doctorIntent);
                }
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

    private void getAgricultureDoctor() {
        showDialog();
        DepthWebService.getInstance().getAgricultureDoctor(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getAgricultureDoctor#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getAgricultureDoctor#onResponse() " + response);
                dismissDialog();
                try {
                    AgriculturalDoctorResJO resJO = FastJsonUtils.getSingleBean(response, AgriculturalDoctorResJO.class);

                    if (null != resJO && resJO.status == 0) {
                        doctorBean = resJO.getObj();
                        tvDoctorTitle.setText(doctorBean.getTitle());
                        tvAgricultureTime.setText(doctorBean.getSendTime());
                        tvDoctorContent.setText(doctorBean.getContent());
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getAgricultureDoctor#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }

    private void getAgriculturalSuggest() {
        showDialog();
        DepthWebService.getInstance().getAgriculturalSuggest(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getAgriculturalSuggest#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getAgriculturalSuggest#onResponse() " + response);
                dismissDialog();
                try {
                    AgriculturalSuggestResJO resJO = FastJsonUtils.getSingleBean(response, AgriculturalSuggestResJO.class);

                    if (null != resJO && resJO.status == 0) {
                        suggestBean = resJO.getObj();
                        tvSuggestTitle.setText(suggestBean.getTitle());
                        tvSuggestTime.setText(suggestBean.getSendTime());
                        tvSuggestContent.setText(suggestBean.getContent());
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getAgriculturalSuggest#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }
}
