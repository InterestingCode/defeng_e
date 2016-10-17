package com.diesel.htweather.depthservice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.depthservice.adapter.PublishIssuesAdapter;
import com.diesel.htweather.event.PublishIssuesEvent;
import com.diesel.htweather.listener.RecyclerItemClickListener;
import com.diesel.htweather.map.MapLocationClient;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.PhotoUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.diesel.htweather.webapi.UserWebService;
import com.diesel.htweather.widget.ActionDialogAdapter;
import com.diesel.htweather.widget.CommonActionDialog;
import com.diesel.htweather.widget.DividerGridItemDecoration;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/11
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class PublishedIssuesActivity extends BaseActivity {

    @BindView(R.id.problem_et)
    EditText mProblemEt;

    @BindView(R.id.problem_photos_recycler_view)
    RecyclerView mRecyclerView;

    private PublishIssuesAdapter mAdapter;

    private List<String> mPhotos = new ArrayList<>();

    private List<ActionDialogAdapter.FontColor> mFontColorList = new ArrayList<>();

    private CommonActionDialog mDialog;

    private File mWatermarkFile = null;

    private MapLocationClient mMapLocationClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_issues);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPhotos.add("add_photo");
        mAdapter = new PublishIssuesAdapter(mPhotos);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

    }

    @OnClick({R.id.back_btn, R.id.send_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.send_btn:
                String problem = mProblemEt.getText().toString();
                if (TextUtils.isEmpty(problem)) {
                    ToastUtils.show(getString(R.string.tips_input_problem));
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mPhotos.size() - 1; i++) {
                    sb.append(mPhotos.get(i)).append(";");
                }
                String imagePath = "";
                if (!TextUtils.isEmpty(sb.toString())) {
                    imagePath = sb.substring(0, sb.toString().length() - 1);
                }

                publishProblem(problem, imagePath);
                break;
        }
    }

    private void showChooseDialog() {
        if (null == mDialog) {
            File imgDirFile;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                imgDirFile = new File(Environment.getExternalStorageDirectory().getPath() + "/defeng_e/pictures/");
            } else {
                imgDirFile = new File("/data/data/" + getPackageName() + "/pictures/");
            }
            if (!imgDirFile.exists()) {
                imgDirFile.mkdirs();
            }
            mWatermarkFile = new File(imgDirFile, "defeng_e_" + System.currentTimeMillis() + ".jpg");
            if (mWatermarkFile.exists()) {
                mWatermarkFile.delete();
            }

            mDialog = new CommonActionDialog(mActivity);
            ActionDialogAdapter.FontColor strPhoto = new ActionDialogAdapter.FontColor(getString(R.string.take_photo));
            ActionDialogAdapter.FontColor strAlbum = new ActionDialogAdapter.FontColor(getString(R.string.album));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x01 && resultCode == RESULT_OK) { // 相机
            String imagePath = mWatermarkFile.getAbsolutePath();
            Log.d(TAG, "onActivityResult() #相机# imagePath=" + imagePath);
            if (TextUtils.isEmpty(imagePath)) {
                ToastUtils.show(getString(R.string.cannot_get_image_source));
                return;
            }
            uploadPicture(imagePath);

        } else if (requestCode == 0x02 && resultCode == RESULT_OK) { // 本地照片
            if (null != data && null != data.getData()) {
                String imagePath = PhotoUtils.getPath(mContext, data.getData());
                Log.d(TAG, "onActivityResult() #相册# imagePath=" + imagePath);
                uploadPicture(imagePath);
            } else {
                ToastUtils.show(getString(R.string.cannot_get_image_source));
            }
        }
    }

    @Subscribe
    public void onPublishIssuesEvent(PublishIssuesEvent event) {
        if (event.action == PublishIssuesEvent.ACTION_ADD_PHOTO) {
            showChooseDialog();
        } else if (event.action == PublishIssuesEvent.ACTION_DEL_PHOTO) {
            mPhotos.remove(event.position);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (null != mMapLocationClient) {
            mMapLocationClient.destroy();
            mMapLocationClient = null;
        }
    }

    private void uploadPicture(String path) {
        showDialog();
        UserWebService.getInstance().uploadPhoto(3, path, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "uploadPicture#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "uploadPicture#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJo = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null != resJo && resJo.status == 0) {
                        String imagePath = resJo.msg;
                        mPhotos.add(mPhotos.size() - 1, imagePath);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.show(getString(R.string.tips_upload_picture_failed));
                    }
                } catch (Exception e) {
                    Log.e(TAG, "uploadPicture#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }


    private void publishProblem(String content, String imagePath) {
        showDialog();
        DepthWebService.getInstance().publishProblem(content, imagePath, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "PublishedIssues#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "PublishedIssues#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null != resJO && resJO.status == 0) {
                        ToastUtils.show("您的问题发布成功");
                        finish();
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "PublishedIssues#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }
}
