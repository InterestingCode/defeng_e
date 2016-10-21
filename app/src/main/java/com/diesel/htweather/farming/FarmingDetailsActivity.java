package com.diesel.htweather.farming;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.response.FarmingInfoDetailsResJO;
import com.diesel.htweather.response.FarmingPolicyDetailsResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.IntentExtras;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.FarmingWebService;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FarmingDetailsActivity extends BaseActivity {

    @BindView(R.id.farming_title_tv)
    TextView mFarmingTitleTv;

    @BindView(R.id.farming_time_and_source_tv)
    TextView mFarmingTimeAndSourceTv;

    @BindView(R.id.farming_browse_tv)
    TextView mFarmingBrowseTv;

    @BindView(R.id.farming_cover_iv)
    SimpleDraweeView mFarmingCoverIv;

    @BindView(R.id.feedback_layout)
    RelativeLayout mFeedbackLayout;

    @BindView(R.id.share_layout)
    RelativeLayout mShareLayout;

    @BindView(R.id.news_content_tv)
    TextView mNewsContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farming_details);
        ButterKnife.bind(this);

        int newsId = IntentExtras.getFarmingNewsId(getIntent());
        int farmingType = IntentExtras.getFarmingType(getIntent());
        if (farmingType == FarmingListActivity.TYPE_FARMING_INFO) {
            getFarmingInfoDetails(newsId);
        } else if (farmingType == FarmingListActivity.TYPE_FARMING_POLICY) {
            getFarmingPolicyDetails(newsId);
        }
    }

    @OnClick({R.id.back_btn, R.id.feedback_layout, R.id.share_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.feedback_layout:
                break;
            case R.id.share_layout:
                break;
        }
    }

    private void getFarmingInfoDetails(int newsId) {
        showDialog();
        FarmingWebService.getInstance().getFarmingInfoDetail(newsId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getFarmingInfoDetails#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getFarmingInfoDetails#onResponse() " + response);
                dismissDialog();
                try {
                    FarmingInfoDetailsResJO resJO = FastJsonUtils
                            .getSingleBean(response, FarmingInfoDetailsResJO.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    FarmingInfoDetailsResJO.FarmingInfoEntity entity = resJO.obj;
                    if (resJO.status != 0 || null == entity) {
                        ToastUtils.show(resJO.msg);
                    } else {
                        mFarmingTitleTv.setText(entity.title);
                        mFarmingCoverIv.setImageURI(entity.titleImg);
                        mFarmingTimeAndSourceTv.setText(entity.sourceWay + " " + entity.sendTime);
                        mFarmingBrowseTv.setText(getString(R.string.browse_number, entity.counts));
                        mNewsContentTv.setText(entity.content);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getFarmingInfoDetails#onResponse() #Exception#" + e.getMessage());
                }
            }
        });
    }

    private void getFarmingPolicyDetails(int newsId) {
        showDialog();
        FarmingWebService.getInstance().getFarmingInfoDetail(newsId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getFarmingPolicyDetails#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getFarmingPolicyDetails#onResponse() " + response);
                dismissDialog();
                try {
                    FarmingPolicyDetailsResJO resJO = FastJsonUtils
                            .getSingleBean(response, FarmingPolicyDetailsResJO.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    FarmingPolicyDetailsResJO.FarmingPolicyEntity entity = resJO.obj;
                    if (resJO.status != 0 || null == entity) {
                        ToastUtils.show(resJO.msg);
                    } else {
                        mFarmingTitleTv.setText(entity.title);
                        mFarmingCoverIv.setImageURI(entity.titleImg);
                        mFarmingTimeAndSourceTv.setText(entity.sourceWay + " " + entity.sendTime);
                        mFarmingBrowseTv.setText(getString(R.string.browse_number, entity.counts));
                        mNewsContentTv.setText(entity.content);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getFarmingPolicyDetails#onResponse() #Exception#" + e.getMessage());
                }
            }
        });
    }
}
