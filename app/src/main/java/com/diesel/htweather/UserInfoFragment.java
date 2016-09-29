package com.diesel.htweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.event.UpdateUserInfoEvent;
import com.diesel.htweather.model.UserInfoBean;
import com.diesel.htweather.response.LoginResJO;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.SharedPreferencesUtils;
import com.diesel.htweather.webapi.UserWebService;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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

    @BindView(R.id.user_avatar_view)
    SimpleDraweeView mUserAvatarView;

    @BindView(R.id.user_name_tv)
    TextView mUserNameTv;

    @BindView(R.id.user_rank_tv)
    TextView mUserRankTv;

    @BindView(R.id.user_addr_tv)
    TextView mUserAddrTv;

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
        EventBus.getDefault().register(this);
        bindUserInfo();
        getUserInfo();
    }

    private void bindUserInfo() {
        UserInfoBean userInfo = SharedPreferencesUtils.getInstance(getContext()).getUserInfo();
        mUserAvatarView.setImageURI(userInfo.userFace);
        mUserNameTv.setText(userInfo.userNickname);
        String[] rank = {"普通会员", "种植大户", "信息员"};
        mUserRankTv.setText(rank[userInfo.userType]);
        mUserAddrTv.setText(userInfo.areaAddr);
    }

    @OnClick({R.id.edit_user_info_tv, R.id.real_weather_tv, R.id.actual_farming_tv,
            R.id.gather_data_tv, R.id.user_message_tv, R.id.help_center_tv, R.id.system_setting_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_user_info_tv:
                ActivityNav.getInstance().startEditUserInfoActivity(getContext());
                break;
            case R.id.real_weather_tv:
                ActivityNav.getInstance().startCityManageActivity(getContext());
                break;
            case R.id.actual_farming_tv:
                ActivityNav.getInstance().startActualFarmingSettingActivity(getContext());
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

    private void getUserInfo() {
        UserWebService.getInstance().getUserInfo(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getUserInfo#onError() " + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getUserInfo#onResponse() " + response);
                try {
                    LoginResJO resJO = FastJsonUtils.getSingleBean(response, LoginResJO.class);
                    if (null != resJO && null != resJO.obj && resJO.status == 0) {
                        UserInfoBean userInfo = SharedPreferencesUtils.getInstance(getContext())
                                .getUserInfo();
                        userInfo.userMobile = resJO.obj.userMobile;
                        userInfo.userNickname = resJO.obj.userNickname;
                        userInfo.userId = resJO.obj.userId;
                        userInfo.userFace = resJO.obj.userFace;
                        userInfo.birthday = resJO.obj.birthday;
                        userInfo.arId = resJO.obj.arId;
                        userInfo.userSex = resJO.obj.userSex;
                        userInfo.address = resJO.obj.address;
                        userInfo.isTrue = resJO.obj.isTrue;
                        userInfo.jobId = resJO.obj.jobId;
                        userInfo.realName = resJO.obj.realName;
                        userInfo.cardId = resJO.obj.cardId;
                        userInfo.pushWarning = resJO.obj.pushWarning;
                        userInfo.areaAddr = resJO.obj.areaAddr;
                        userInfo.userType = resJO.obj.userType;
                        userInfo.userFace = resJO.obj.userFace;
                        SharedPreferencesUtils.getInstance(getContext()).updateUserInfo(userInfo);
                        SharedPreferencesUtils.getInstance(getContext())
                                .enableMessageNotify(resJO.obj.pushWarning == 1);
                        bindUserInfo();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getUserInfo#onResponse() " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onUpdateUserInfoEvent(UpdateUserInfoEvent event) {
        getUserInfo();
    }
}
