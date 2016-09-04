package com.diesel.htweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.util.ActivityNav;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Comments：个人中心
 *
 * @author Diesel
 *         Time: 2016/8/13
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class UserInfoFragment extends BaseFragment {

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick({R.id.edit_user_info_tv, R.id.real_weather_tv, R.id.actual_farming_tv,
            R.id.gather_data_tv, R.id.user_message_tv, R.id.help_center_tv, R.id.system_setting_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_user_info_tv:
                ActivityNav.getInstance().startEditUserInfoActivity(getContext());
                break;
            case R.id.real_weather_tv:
                break;
            case R.id.actual_farming_tv:
                break;
            case R.id.gather_data_tv:
                ActivityNav.getInstance().startGatherDataActivity(getContext());
                break;
            case R.id.user_message_tv:
                ActivityNav.getInstance().startMessageActivity(getContext());
                break;
            case R.id.help_center_tv:
                ActivityNav.getInstance().startHelpCenterActivity(getContext());
                break;
            case R.id.system_setting_tv:
                ActivityNav.getInstance().startSystemSettingActivity(getContext());
                break;
        }
    }
}
