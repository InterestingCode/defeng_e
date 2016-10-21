package com.diesel.htweather.depthservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.depthservice.adapter.GrowthDiaryAdapter;
import com.diesel.htweather.depthservice.model.GrowthDiaryBean;
import com.diesel.htweather.event.RecyclerItemEvent;
import com.diesel.htweather.response.GrowthDiaryResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


public class GrowthDiaryActivity extends BaseActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.farming_policy_recycler_view)
    XRecyclerView mRecyclerView;

    GrowthDiaryAdapter mAdapter = null;

    int page = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_growth_diary);
        ButterKnife.bind(this);
        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST,
                        R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        getGrowthDiaryList("1", "20"); // 第一页前20个
    }


    private void getGrowthDiaryList(String pageNum, String rows) {
        page = Integer.valueOf(pageNum);
        showDialog();
        DepthWebService.getInstance().getGrowthDiaryList(pageNum, rows, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getGrowthDiaryList#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getGrowthDiaryList#onResponse() " + response);
                dismissDialog();
                try {
                    GrowthDiaryResJO resJO = FastJsonUtils.getSingleBean(response, GrowthDiaryResJO.class);
                    if (null != resJO && resJO.status == 0) {
                        if (page == 1) {
                            mAdapter = new GrowthDiaryAdapter(GrowthDiaryActivity.this, resJO.getData());
                            mRecyclerView.setAdapter(mAdapter);
                            mRecyclerView.refreshComplete();
                        } else {
                            List<GrowthDiaryBean> listData = mAdapter.getGrowthDiaryBeanList();
                            listData.addAll(resJO.getData());
                            mAdapter.update(listData);
                            mRecyclerView.loadMoreComplete();
                        }
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getGrowthDiaryList#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }

    @Subscribe
    public void onRecyclerItemEvent(RecyclerItemEvent event) {
        int position = event.position;
        GrowthDiaryBean growthDiaryBean = mAdapter.getGrowthDiaryBeanList().get(position);
        Intent intent = new Intent(this, GrowthDiaryDetailsActivity.class);
        intent.putExtra("growthId", growthDiaryBean.getId());
        startActivity(intent);

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

    @Override
    public void onRefresh() {
        getGrowthDiaryList("1", "20");
    }

    @Override
    public void onLoadMore() {
        getGrowthDiaryList(String.valueOf(page + 1), "20");
    }
}
