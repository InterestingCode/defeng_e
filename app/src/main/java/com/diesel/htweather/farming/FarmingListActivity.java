package com.diesel.htweather.farming;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.event.RecyclerItemEvent;
import com.diesel.htweather.farming.adapter.FarmingPolicyAdapter;
import com.diesel.htweather.response.FarmingInfoResJO;
import com.diesel.htweather.response.FarmingPolicyResJO;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.IntentExtras;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.FarmingWebService;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FarmingListActivity extends BaseActivity {

    public static final int TYPE_ACTUAL_FARMING = 9000;

    public static final int TYPE_FARMING_INFO = 9001;

    public static final int TYPE_FARMING_POLICY = 9002;

    @BindView(R.id.farming_policy_recycler_view)
    XRecyclerView mRecyclerView;

    @BindView(R.id.header_title_tv)
    TextView mHeaderTitleTv;

    @BindView(R.id.farming_policy_banner_iv)
    ImageView mBannerIv;

    private int mPage = 1;

    private int mAreaId;

    private int mFarmingType;

    private List<BaseBean> mList = new ArrayList<>();

    private FarmingPolicyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farming_policy);
        ButterKnife.bind(this);

        mAdapter = new FarmingPolicyAdapter(mList);
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST,
                        R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new FarmingPolicyAdapter(mList));

        mAreaId = IntentExtras.getAreaId(getIntent());
        mFarmingType = IntentExtras.getFarmingType(getIntent());
        if (mFarmingType == TYPE_FARMING_INFO) {
            mHeaderTitleTv.setText(R.string.farming_info);
            mBannerIv.setImageResource(R.drawable.banner_farming_info);
            getFarmingInfoList();
        } else if (mFarmingType == TYPE_FARMING_POLICY) {
            mHeaderTitleTv.setText(R.string.farming_policy);
            mBannerIv.setImageResource(R.drawable.banner_depth_service);
            getFarmingPolicyList();
        }
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }

    @Subscribe
    public void onRecyclerItemEvent(RecyclerItemEvent event) {
        int position = event.position;
        if (mFarmingType == TYPE_FARMING_INFO) {
            ActivityNav.getInstance().startFarmingDetailsActivity(this,
                    ((FarmingInfoResJO.ObjEntity.InfoNewsEntity) mList.get(position)).newsId,
                    mFarmingType);
        } else if (mFarmingType == TYPE_FARMING_POLICY) {
            ActivityNav.getInstance().startFarmingDetailsActivity(this,
                    ((FarmingPolicyResJO.ObjEntity.PolicyNewsEntity) mList.get(position)).newsId,
                    mFarmingType);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void getFarmingInfoList() {
        showDialog();
        FarmingWebService.getInstance().getFarmingInfoList(mAreaId, mPage, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getFarmingInfoList#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getFarmingInfoList#onResponse() " + response);
                dismissDialog();
                try {
                    FarmingInfoResJO resJO = FastJsonUtils
                            .getSingleBean(response, FarmingInfoResJO.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    if (resJO.status != 0) {
                        ToastUtils.show(resJO.msg);
                    } else {
                        if (null != resJO.obj && !resJO.obj.articleNewsList.isEmpty()) {
                            if (mPage == 1) {
                                mList.clear();
                            }
                            mList.addAll(resJO.obj.articleNewsList);
                            mAdapter.notifyDataSetChanged();
                            mPage++;
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getFarmingInfoList#onResponse() #Exception#" + e.getMessage());
                }
            }
        });
    }

    private void getFarmingPolicyList() {
        showDialog();
        FarmingWebService.getInstance().getFarmingPolicyList(mAreaId, mPage, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getFarmingPolicyList#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getFarmingPolicyList#onResponse() " + response);
                dismissDialog();
                try {
                    FarmingPolicyResJO resJO = FastJsonUtils
                            .getSingleBean(response, FarmingPolicyResJO.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    if (resJO.status != 0) {
                        ToastUtils.show(resJO.msg);
                    } else {
                        if (null != resJO.obj && !resJO.obj.polcyNewsList.isEmpty()) {
                            if (mPage == 1) {
                                mList.clear();
                            }
                            mList.addAll(resJO.obj.polcyNewsList);
                            mAdapter.notifyDataSetChanged();
                            mPage++;
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getFarmingPolicyList#onResponse() #Exception#" + e.getMessage());
                }
            }
        });
    }

}
