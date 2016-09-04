package com.diesel.htweather.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.util.DialogUtils;
import com.diesel.htweather.util.PhotoUtils;
import com.diesel.htweather.widget.EditUserInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                mCurrPhotoItem = 1;
                openAlbums();
                break;
            case R.id.id_photo_2_iv:
                mCurrPhotoItem = 2;
                openAlbums();
                break;
            case R.id.id_photo_3_iv:
                mCurrPhotoItem = 3;
                openAlbums();
                break;
            case R.id.id_photo_4_iv:
                mCurrPhotoItem = 4;
                openAlbums();
                break;
            case R.id.save_setting_view:
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OPEN_ALBUM) {
            if (resultCode == RESULT_OK && null != data) {
                Bitmap bitmap = PhotoUtils.decodeUriToBitmap(this, data.getData());
                if (null != bitmap) {
                    switch (mCurrPhotoItem) {
                        case 1:
                            mIdPhoto1Iv
                                    .setImageDrawable(new BitmapDrawable(getResources(), bitmap));
                            break;
                        case 2:
                            mIdPhoto2Iv
                                    .setImageDrawable(new BitmapDrawable(getResources(), bitmap));
                            break;
                        case 3:
                            mIdPhoto3Iv
                                    .setImageDrawable(new BitmapDrawable(getResources(), bitmap));
                            break;
                        case 4:
                            mIdPhoto4Iv
                                    .setImageDrawable(new BitmapDrawable(getResources(), bitmap));
                            break;
                    }
                }
            }
        }
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