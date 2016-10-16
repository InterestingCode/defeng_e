package com.diesel.htweather.farming.holder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diesel.htweather.R;
import com.diesel.htweather.farming.adapter.HotAndRcmdAreaAdapter;
import com.diesel.htweather.farming.model.RecommendAreaBean;
import com.diesel.htweather.widget.DividerGridItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/10/14
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class RecommendAreaHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.hot_area_recycler_view)
    RecyclerView mRecyclerView;

    HotAndRcmdAreaAdapter mAdapter;

    public RecommendAreaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mAdapter = new HotAndRcmdAreaAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 4));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(itemView.getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void bindData(RecommendAreaBean bean) {
        mAdapter.setData(bean.recommendAreaList);
    }
}
