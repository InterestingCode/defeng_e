package com.diesel.htweather.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.base.DFApplication;
import com.diesel.htweather.listener.RecyclerItemClickListener;
import com.diesel.htweather.model.UserInfoBean;
import com.diesel.htweather.response.BaseResJo;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.DialogUtils;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.PhotoUtils;
import com.diesel.htweather.util.SharedPreferencesUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.UserWebService;
import com.diesel.htweather.widget.ActionDialogAdapter;
import com.diesel.htweather.widget.CommonActionDialog;
import com.diesel.htweather.widget.EditUserInfoView;
import com.diesel.pickerview.OptionsPickerView;
import com.diesel.pickerview.TimePickerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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

    private CommonActionDialog mDialog;

    private List<ActionDialogAdapter.FontColor> mFontColorList = new ArrayList<>();

    private File mWatermarkFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
        ButterKnife.bind(this);

        bindUserInfo();
    }

    private void bindUserInfo() {
        UserInfoBean userInfo = SharedPreferencesUtils.getInstance(mContext).getUserInfo();
        mUserAvatarView.setImageURI(userInfo.userFace);
        mUserAppellationView.setContent(userInfo.userNickname);
        mUserGenderView.setContent(userInfo.userSex == 1 ? "男" : "女");
        mUserBirthView.setContent(userInfo.birthday);
        mUserAreaView.setContent(userInfo.areaAddr);
        mUserOccupationView.setContent("");
        mUserAddressView.setContent(userInfo.address);

        mUserTelephoneView.setContent(userInfo.userMobile);
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
                showChooseDialog();
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
                updateUserInfo();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (null != mTimePickerView && mTimePickerView.isShowing()) {
                mTimePickerView.dismiss();
                return true;
            }
            if (null != mCityPickerView && mCityPickerView.isShowing()) {
                mCityPickerView.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showChooseDialog() {
        if (null == mDialog) {
            File imgDirFile;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                imgDirFile = new File(Environment.getExternalStorageDirectory().getPath()
                        + "/defeng_e/pictures/");
            } else {
                imgDirFile = new File("/data/data/" + getPackageName() + "/pictures/");
            }
            if (!imgDirFile.exists()) {
                imgDirFile.mkdirs();
            }
            mWatermarkFile = new File(imgDirFile,
                    "defeng_e_" + System.currentTimeMillis() + ".jpg");
            if (mWatermarkFile.exists()) {
                mWatermarkFile.delete();
            }

            mDialog = new CommonActionDialog(mActivity);
            ActionDialogAdapter.FontColor strPhoto = new ActionDialogAdapter.FontColor(
                    getString(R.string.take_photo));
            ActionDialogAdapter.FontColor strAlbum = new ActionDialogAdapter.FontColor(
                    getString(R.string.album));
            mFontColorList.add(strPhoto);
            mFontColorList.add(strAlbum);
            mDialog.addDialogContent(mFontColorList);
            mDialog.addOnClickListener(new RecyclerItemClickListener() {
                @Override
                public void onRecyclerItemClick(int position) {
                    if (mDialog.isShowing()) {
                        mDialog.dismiss();
                    }
                    switch (position) {
                        case 0:
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mWatermarkFile));
                            startActivityForResult(intent, 0x01);
                            break;
                        case 1:
                            Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                            intent1.addCategory(Intent.CATEGORY_OPENABLE);
                            intent1.setType("image/*");
                            startActivityForResult(intent1, 0x02);
                            break;
                    }
                }
            });
        }
        mDialog.show();
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
                    .setPicker(DFApplication.provinces, DFApplication.cities,
                            DFApplication.countries,
                            true);
            mCityPickerView.setCyclic(true, true, true);
            mCityPickerView.setCancelable(true);
            mCityPickerView.setSelectOptions(0, 0, 0);

            mCityPickerView
                    .setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3) {
                            // 返回的分别是三个级别的选中位置
                            String tx = DFApplication.provinces.get(options1).pvName + "-"
                                    + DFApplication.cities.get(options1).get(option2).ctName + "-"
                                    + DFApplication.countries.get(options1).get(option2)
                                    .get(options3).arName;
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

    private void updateUserInfo() {
        showDialog();

        final UserInfoBean bean = SharedPreferencesUtils.getInstance(mContext).getUserInfo();
        bean.userNickname = mUserAppellationView.getInputContent();
        bean.userSex = "男".equals(mUserGenderView.getInputContent()) ? 1 : 2;
        bean.birthday = mUserBirthView.getInputContent();
        bean.arId = 0;
        bean.jobId = 0;
        bean.address = mUserAddressView.getInputContent();
        bean.userMobile = mUserTelephoneView.getInputContent();

        UserWebService.getInstance().modifyUserInfo(bean, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "updateUserInfo#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "updateUserInfo#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJo resJO = FastJsonUtils.getSingleBean(response, BaseResJo.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    if (resJO.status != 0) {
                        ToastUtils.show(resJO.msg);
                    } else {
                        SharedPreferencesUtils.getInstance(mContext).updateUserInfo(bean);
                        ToastUtils.show(getString(R.string.tips_modify_user_info_success));
                        finish();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "updateUserInfo#onResponse() " + e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x01 && resultCode == RESULT_OK) { // 相机
            String path = mWatermarkFile.getPath();
            String imagePath = mWatermarkFile.getAbsolutePath();
            Log.d(TAG, "onActivityResult() #相机# imagePath="+imagePath+"; path="+path);
            if (TextUtils.isEmpty(imagePath)) {
                ToastUtils.show(getString(R.string.cannot_get_image_source));
                return;
            }
            mUserAvatarView.setImageURI(Uri.parse("file://"+imagePath));
            uploadPicture(imagePath);
        } else if (requestCode == 0x02 && resultCode == RESULT_OK) { // 本地照片
            if (null != data && null != data.getData()) {
                mUserAvatarView.setImageURI(data.getData());
                String imagePath = PhotoUtils.getPath(mContext, data.getData());
                Log.d(TAG, "onActivityResult() #照片# imagePath="+imagePath);
                uploadPicture(imagePath);
            } else {
                ToastUtils.show(getString(R.string.cannot_get_image_source));
            }
        }
    }

    private void uploadPicture(String path) {
        UserWebService.getInstance().uploadPhoto(path, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "uploadPicture#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "uploadPicture#onResponse() " + response);
            }
        });
    }

}
