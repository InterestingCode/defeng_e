package com.diesel.htweather.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.constant.Api;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.util.DialogUtils;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.PhotoUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.UserWebService;
import com.diesel.htweather.widget.EditUserInfoView;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class RealNameAuthActivity extends BaseActivity {

    private static final int REQUEST_CODE_OPEN_ALBUM = 101;

    @BindView(R.id.your_name_view)
    EditUserInfoView mYourNameView;

    @BindView(R.id.your_id_number_view)
    EditUserInfoView mYourIdNumberView;

    @BindView(R.id.id_photo_1_iv)
    ImageView mIdPhoto1Iv;

    @BindView(R.id.id_photo_2_iv)
    ImageView mIdPhoto2Iv;

    @BindView(R.id.id_photo_3_iv)
    ImageView mIdPhoto3Iv;

    @BindView(R.id.id_photo_4_iv)
    ImageView mIdPhoto4Iv;

    private int mCurrPhotoItem;

    private String[] mImagePath = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_auth);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn, R.id.your_name_view, R.id.your_id_number_view, R.id.id_photo_1_iv,
            R.id.id_photo_2_iv, R.id.id_photo_3_iv, R.id.id_photo_4_iv, R.id.save_setting_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.your_name_view:
                DialogUtils.showInputDialog(this, getString(R.string.modify_your_name),
                        new DialogUtils.DialogOnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                    String inputContent) {
                                mYourNameView.setContent(inputContent);
                            }
                        });
                break;
            case R.id.your_id_number_view:
                DialogUtils.showInputDialog(this, getString(R.string.modify_your_id_number),
                        new DialogUtils.DialogOnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                    String inputContent) {
                                mYourIdNumberView.setContent(inputContent);
                            }
                        });
                break;
            case R.id.id_photo_1_iv:
                mCurrPhotoItem = 0;
                openAlbums();
                break;
            case R.id.id_photo_2_iv:
                mCurrPhotoItem = 1;
                openAlbums();
                break;
            case R.id.id_photo_3_iv:
                mCurrPhotoItem = 2;
                openAlbums();
                break;
            case R.id.id_photo_4_iv:
                mCurrPhotoItem = 3;
                openAlbums();
                break;
            case R.id.save_setting_view:
                realNameAuth();
                break;
        }
    }

    private void openAlbums() {
        Intent intent;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(intent, REQUEST_CODE_OPEN_ALBUM);
    }

    private void realNameAuth() {
        String realName = mYourNameView.getInputContent();
        if (!TextUtils.isEmpty(realName)) {
            ToastUtils.show(getString(R.string.tips_input_real_name));
            return;
        }
        String cardId = mYourIdNumberView.getInputContent();
        if (!TextUtils.isEmpty(cardId)) {
            ToastUtils.show(getString(R.string.tips_input_card_id));
            return;
        }
        StringBuilder imgPath = new StringBuilder();
        for (int i = 0; i < mImagePath.length; i ++) {
            if (!TextUtils.isEmpty(mImagePath[i])) {
                imgPath.append(mImagePath[i]).append(";");
            }
        }
        String path = "";
        if (!TextUtils.isEmpty(imgPath.toString())) {
            path = imgPath.substring(0, imgPath.length() - 1);
        }
        showDialog();
        UserWebService.getInstance().realNameAuth(realName, cardId, path, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "realNameAuth#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "realNameAuth#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null != resJO && resJO.status == 0) {
                        ToastUtils.show(getString(R.string.tips_real_name_auth_success));
                        finish();
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "realNameAuth#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OPEN_ALBUM) {
            if (resultCode == RESULT_OK && null != data) {
                String imagePath = PhotoUtils.getPath(mContext, data.getData());
                uploadPicture(imagePath);
                Bitmap bitmap = PhotoUtils.decodeUriToBitmap(this, data.getData());
                if (null != bitmap) {
                    switch (mCurrPhotoItem) {
                        case 0:
                            mIdPhoto1Iv
                                    .setImageDrawable(new BitmapDrawable(getResources(), bitmap));
                            break;
                        case 1:
                            mIdPhoto2Iv
                                    .setImageDrawable(new BitmapDrawable(getResources(), bitmap));
                            break;
                        case 2:
                            mIdPhoto3Iv
                                    .setImageDrawable(new BitmapDrawable(getResources(), bitmap));
                            break;
                        case 3:
                            mIdPhoto4Iv
                                    .setImageDrawable(new BitmapDrawable(getResources(), bitmap));
                            break;
                    }
                }
            }
        }
    }

    private void uploadPicture(String path) {
        UserWebService.getInstance().uploadPhoto(1, path, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "uploadPicture#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "uploadPicture#onResponse() " + response);
                try {
                    BaseResJO resJo = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null != resJo && resJo.status == 0) {
                        mImagePath[mCurrPhotoItem] = Api.SERVER_URL + resJo.msg;
                    } else {
                        ToastUtils.show(getString(R.string.tips_upload_picture_failed));
                    }
                } catch (Exception e) {
                    Log.e(TAG, "uploadPicture#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }

//    private void crop(Uri uri) {
//        // 裁剪图片意图
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//        // 裁剪框的比例，1：1
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // 裁剪后输出图片的尺寸大小
//        intent.putExtra("outputX", 250);
//        intent.putExtra("outputY", 250);
//
//        intent.putExtra("outputFormat", "JPEG");// 图片格式
//        intent.putExtra("noFaceDetection", true);// 取消人脸识别
//        intent.putExtra("return-data", true);
//        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
//        startActivityForResult(intent, PHOTO_REQUEST_CUT);
//    }
}