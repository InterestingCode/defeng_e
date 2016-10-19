package com.diesel.htweather.depthservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.constant.Api;
import com.diesel.htweather.depthservice.adapter.CommentsAdapter;
import com.diesel.htweather.depthservice.model.CommentsBean;
import com.diesel.htweather.depthservice.model.OnlineAdvisoryBean;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.response.OnlineDetailsResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.PicassoUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 咨询详情
 */
public class OnlineAdvisoryDetailsActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.tvUserFace)
    ImageView tvUserFace;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvUserType)
    TextView tvUserType;

    @BindView(R.id.tvAddress)
    TextView tvAddress;

    @BindView(R.id.tvCreateTime)
    TextView tvCreateTime;

    @BindView(R.id.tvContent)
    TextView tvContent;

    @BindView(R.id.ivImage1)
    ImageView ivImage1;

    @BindView(R.id.ivImage2)
    ImageView ivImage2;

    @BindView(R.id.ivImage3)
    ImageView ivImage3;

    @BindView(R.id.tvUpsNum)
    TextView tvUpsNum;

    @BindView(R.id.tvCommentsNum)
    TextView tvCommentsNum;

    @BindView(R.id.tvReadNum)
    TextView tvReadNum;

    @BindView(R.id.commentList)
    ListView commentList;

    @BindView(R.id.comment_edit)
    EditText comment_edit;

    @BindView(R.id.comment_rl)
    LinearLayout comment_rl;

    String contentId;

    String cmId;

    CommentsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_advisory_details);
        ButterKnife.bind(this);
        contentId = getIntent().getStringExtra("contentId");
        commentList.setOnItemClickListener(this);
        initDatas();
    }

    private void initDatas() {
        showDialog();
        DepthWebService.getInstance().getCommentsDetails(contentId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "OnlineAdvisoryDetails#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "OnlineAdvisoryDetails#onResponse() " + response);
                dismissDialog();
                try {
                    OnlineDetailsResJO resJO = FastJsonUtils.getSingleBean(response, OnlineDetailsResJO.class);

                    if (null != resJO && resJO.status == 0) {
                        OnlineAdvisoryBean obj = resJO.getObj();
                        String userFacePath = Api.SERVER_URL + obj.getUserFace();
                        PicassoUtils.loadImageViewHolder(mContext, userFacePath, R.drawable.ic_gather_data_avatar, tvUserFace);
                        tvName.setText(obj.getUserNickName());
                        tvUserType.setText(obj.getUserType());
                        tvAddress.setText(obj.getLocationAddr());
                        tvCreateTime.setText(obj.getCreatTime());
                        tvContent.setText(obj.getContent());
                        String imagePath[] = getImageViewUriPath(obj.getImgPaths());
                        if (imagePath != null && imagePath.length == 3) {
                            PicassoUtils.loadImageViewHolder(OnlineAdvisoryDetailsActivity.this, Api.SERVER_URL + imagePath[0], R.drawable.test_online_image, ivImage1);
                            PicassoUtils.loadImageViewHolder(OnlineAdvisoryDetailsActivity.this, Api.SERVER_URL + imagePath[1], R.drawable.test_online_image, ivImage2);
                            PicassoUtils.loadImageViewHolder(OnlineAdvisoryDetailsActivity.this, Api.SERVER_URL + imagePath[2], R.drawable.test_online_image, ivImage3);
                        }

                        tvUpsNum.setText(obj.getUps());
                        tvCommentsNum.setText(obj.getComments());
                        tvReadNum.setText(obj.getCounts());
                        mAdapter = new CommentsAdapter(OnlineAdvisoryDetailsActivity.this, obj.getCmList());
                        commentList.setAdapter(mAdapter);
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "OnlineAdvisoryDetails#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }

    private String[] getImageViewUriPath(String path) {
        return path.split(";");
    }

    @OnClick({R.id.back_btn, R.id.ivUpsBtn, R.id.ivCommentsBtn, R.id.commentBtn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.ivUpsBtn:
                // 点赞
                thumbsUpComments();
                break;
            case R.id.ivCommentsBtn:
                // 显示评论
                comment_rl.setVisibility(View.VISIBLE);
                cmId = "";
                break;
            case R.id.commentBtn:
                // 发表评论
                String commentsContent = comment_edit.getText().toString();
                if (TextUtils.isEmpty(commentsContent)) {
                    ToastUtils.show("请输入评论内容");
                    return;
                }
                // 一级评论
                publishComments(contentId, commentsContent, cmId);
                comment_rl.setVisibility(View.GONE);
                comment_edit.setText("");
                break;
        }
    }

    private void thumbsUpComments() {
        showDialog();
        DepthWebService.getInstance().thumbsUpComments(contentId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "thumbsUpComments#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "thumbsUpComments#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null != resJO && resJO.status == 0) {
                        ToastUtils.show(resJO.msg);
                        initDatas();
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "thumbsUpComments#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }


    private void publishComments(String contentId, String comment, String parentCmId) {
        showDialog();
        DepthWebService.getInstance().publishComments(contentId, comment, parentCmId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "publishComments#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "publishComments#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null != resJO && resJO.status == 0) {
                        ToastUtils.show(resJO.msg);
                        initDatas();
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "publishComments#onResponse() #Exception# " + e.getMessage());
                }
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        comment_rl.setVisibility(View.VISIBLE);
        CommentsBean bean = mAdapter.getCommentsList().get(position);
        // 二级评论
        cmId = bean.getCmId();
    }
}
