package com.diesel.htweather.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.user.adapter.GatherDataAdapter;
import com.diesel.htweather.user.model.GatherDataBean;
import com.diesel.htweather.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

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

    private GatherDataAdapter mAdapter;

    private List<GatherDataBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gather_data);
        ButterKnife.bind(this);

        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, false);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
        mRefreshLayout.setPullDownRefreshEnable(false);
        mRefreshLayout.setDelegate(this);

        mList.add(new GatherDataBean());
        mList.add(new GatherDataBean());
        mList.add(new GatherDataBean());
        mAdapter = new GatherDataAdapter(mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST,
                        R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.back_btn, R.id.edit_btn, R.id.search_btn, R.id.scroll_to_top_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.edit_btn:
                break;
            case R.id.search_btn:
                break;
            case R.id.scroll_to_top_btn:
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
