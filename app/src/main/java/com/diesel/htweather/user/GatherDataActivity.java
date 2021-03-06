package com.diesel.htweather.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.response.GatherDataResJO;
import com.diesel.htweather.user.adapter.GatherDataAdapter;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.PlantWebService;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import okhttp3.Call;

public class GatherDataActivity extends BaseActivity
        implements BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.edit_btn)
    ImageView mEditBtn;

    @BindView(R.id.search_content_et)
    EditText mSearchContentEt;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.refresh_layout)
    BGARefreshLayout mRefreshLayout;

    @BindView(R.id.scroll_to_top_btn)
    ImageButton mScrollToTopBtn;

    private int mPage = 1;

    private GatherDataAdapter mAdapter;

    private List<GatherDataResJO.GatherDataEntity> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gather_data);
        ButterKnife.bind(this);

        mRefreshLayout.setDelegate(this);
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);

        mAdapter = new GatherDataAdapter(mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST,
                        R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setAdapter(mAdapter);

        getGatherData("");
    }

    @OnClick({R.id.back_btn, R.id.edit_btn, R.id.search_btn, R.id.scroll_to_top_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.edit_btn:
                ActivityNav.getInstance().startEditProblemActivity(mActivity);
                break;
            case R.id.search_btn:
                String keywords = mSearchContentEt.getText().toString();
                if (TextUtils.isEmpty(keywords)) {
                    ToastUtils.show("请输入搜索关键词");
                    return;
                }
                getGatherData(keywords);
                break;
            case R.id.scroll_to_top_btn:
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPage = 1;
        getGatherData("");
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        getGatherData("");
        return true;
    }

    private void getGatherData(String keywords) {
        showDialog();
        PlantWebService.getInstance().getGatherData(mPage, keywords, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getGatherData#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
                mRefreshLayout.endRefreshing();
                mRefreshLayout.endLoadingMore();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getGatherData#onResponse() " + response);
                dismissDialog();
                mRefreshLayout.endRefreshing();
                mRefreshLayout.endLoadingMore();
                try {
                    GatherDataResJO resJO = FastJsonUtils.getSingleBean(response, GatherDataResJO.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    if (resJO.status != 0) {
                        ToastUtils.show(resJO.msg);
                    } else {
                        if (null != resJO.data && !resJO.data.isEmpty()) {
                            if (mPage == 1) {
                                mList.clear();
                            }
                            mList.addAll(resJO.data);
                            mAdapter.notifyDataSetChanged();
                            mPage ++;
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getGatherData#onResponse() #Exception#" + e.getMessage());
                }
            }
        });
    }
}
