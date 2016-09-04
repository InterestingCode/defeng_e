package com.diesel.htweather.user;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.base.HTApplication;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.DialogUtils;
import com.diesel.htweather.util.StringUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.widget.EditUserInfoView;
import com.diesel.pickerview.OptionsPickerView;
import com.diesel.pickerview.TimePickerView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditUserInfoActivity extends BaseActivity {

    @BindView(R.id.back_btn)
    ImageView mBackBtn;

    @BindView(R.id.right_arrow_iv)
    ImageView mRightArrowIv;

    @BindView(R.id.user_avatar_view)
    SimpleDraweeView mUserAvatarView;

    @BindView(R.id.user_appellation_view)
    EditUserInfoView mUserAppellationView;

    @BindView(R.id.user_gender_view)
    EditUserInfoView mUserGenderView;

    @BindView(R.id.user_birth_view)
    EditUserInfoView mUserBirthView;

    @BindView(R.id.user_area_view)
    EditUserInfoView mUserAreaView;

    @BindView(R.id.user_occupation_view)
    EditUserInfoView mUserOccupationView;

    @BindView(R.id.user_address_view)
    EditUserInfoView mUserAddressView;

    @BindView(R.id.user_plant_view)
    EditUserInfoView mUserPlantView;

    @BindView(R.id.user_telephone_view)
    EditUserInfoView mUserTelephoneView;

    @BindView(R.id.user_certification_view)
    EditUserInfoView mUserCertificationView;

    @BindView(R.id.save_setting_view)
    LinearLayout mSaveSettingView;

    private TimePickerView mTimePickerView;

    private OptionsPickerView mCityPickerView, mOccupationPickerView;

    private ArrayList<String> mOccupations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.right_arrow_iv, R.id.user_avatar_view, R.id.user_appellation_view,
            R.id.user_gender_view, R.id.user_birth_view, R.id.user_area_view,
            R.id.user_occupation_view, R.id.user_address_view, R.id.user_plant_view,
            R.id.user_telephone_view, R.id.user_certification_view, R.id.save_setting_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.right_arrow_iv:
                break;
            case R.id.user_avatar_view:
                break;
            case R.id.user_appellation_view:
                DialogUtils.showInputDialog(this, getString(R.string.modify_appellation),
                        new DialogUtils.DialogOnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                    String inputContent) {
                                mUserAppellationView.setContent(inputContent);
                            }
                        });
                break;
            case R.id.user_gender_view:
                DialogUtils.showGenderDialog(this, new DialogUtils.DialogOnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, String inputContent) {
                        mUserGenderView.setContent(inputContent);
                    }
                });
                break;
            case R.id.user_birth_view:
                showTimePickerView();
                break;
            case R.id.user_area_view:
                showAreaPickerView();
                break;
            case R.id.user_occupation_view:
                showOccupationPickerView();
                break;
            case R.id.user_address_view:
                DialogUtils.showInputDialog(this, getString(R.string.modify_address),
                        new DialogUtils.DialogOnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                    String inputContent) {
                                mUserAppellationView.setContent(inputContent);
                            }
                        });
                break;
            case R.id.user_plant_view:
                ActivityNav.getInstance().startPlantAndAreaActivity(this);
                break;
            case R.id.user_telephone_view:
                DialogUtils.showTelephoneDialog(this, new DialogUtils.DialogOnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                            String inputContent) {
                        mUserTelephoneView.setContent(inputContent);
                    }
                });
                break;
            case R.id.user_certification_view:
                ActivityNav.getInstance().startRealNameAuthActivity(this);
                break;
            case R.id.save_setting_view:
                break;
        }
    }

    private void showTimePickerView() {
        if (null == mTimePickerView) {
            mTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
            mTimePickerView.setTitle(getString(R.string.choose_birth));
            mTimePickerView.setTime(new Date());
            mTimePickerView.setCyclic(true);
            mTimePickerView.setCancelable(true);
            mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String time = format.format(date);
                    mUserBirthView.setContent(time);
                }
            });
        }
        mTimePickerView.show();
    }

    private void showAreaPickerView() {
        if (null == mCityPickerView) {
            mCityPickerView = new OptionsPickerView(this);
            mCityPickerView.setTitle(getString(R.string.choose_area));
            mCityPickerView
                    .setPicker(HTApplication.provinces, HTApplication.cities,
                            HTApplication.countries,
                            true);
            mCityPickerView.setCyclic(true, true, true);
            mCityPickerView.setSelectOptions(0, 0, 0);

            mCityPickerView
                    .setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3) {
                            // 返回的分别是三个级别的选中位置
                            String tx = HTApplication.provinces.get(options1).name + "-"
                                    + HTApplication.cities.get(options1).get(option2).name + "-"
                                    + HTApplication.countries.get(options1).get(option2)
                                    .get(options3).name;
                            mUserAreaView.setContent(tx);
                        }
                    });
        }
        mCityPickerView.show();
    }

    private void showOccupationPickerView() {
        if (null == mOccupationPickerView) {
            List<String> strings = Arrays
                    .asList(getResources().getStringArray(R.array.occupation_ary));
            mOccupations.addAll(strings);
            mOccupationPickerView = new OptionsPickerView(this);
            mOccupationPickerView.setTitle(getString(R.string.modify_occupation));
            mOccupationPickerView.setPicker(mOccupations);
            mOccupationPickerView.setCyclic(true);
            mOccupationPickerView.setSelectOptions(0);
            mOccupationPickerView.setOnoptionsSelectListener(
                    new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3) {
                            mUserOccupationView.setContent(mOccupations.get(options1));
                        }
                    });
        }
        mOccupationPickerView.show();
    }
}
