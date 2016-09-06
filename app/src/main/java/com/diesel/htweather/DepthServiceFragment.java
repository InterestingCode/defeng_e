package com.diesel.htweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.depthservice.GrowthDiaryActivity;
import com.diesel.htweather.depthservice.SettingFacilitiesActivity;
import com.diesel.htweather.util.ActivityNav;

import butterknife.ButterKnife;
import butterknife.OnClick;

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

    public static DepthServiceFragment newInstance() {
        return new DepthServiceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_depth_service, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Intent intent = new Intent(mActivity, GrowthDiaryActivity.class);
                startActivity(intent);
                break;
        }
    }
}
