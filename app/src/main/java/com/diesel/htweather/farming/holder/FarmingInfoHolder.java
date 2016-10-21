package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.farming.FarmingListActivity;
import com.diesel.htweather.farming.model.FarmingInfoBean;
import com.diesel.htweather.util.ActivityNav;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/8/24
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */
public class FarmingInfoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.farming_info_cover_iv)
    ImageView mFarmingInfoCoverIv;

    @BindView(R.id.farming_info_title_tv)
    TextView mFarmingInfoTitleTv;

    @BindView(R.id.farming_info_content_tv)
    TextView mFarmingInfoContentTv;

    @BindView(R.id.farming_info_time_tv)
    TextView mFarmingInfoTimeTv;

    @BindView(R.id.farming_info_browse_tv)
    TextView mFarmingInfoBrowseTv;

    FarmingInfoBean mInfoBean;

    public FarmingInfoHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityNav.getInstance().startFarmingPolicyActivity(itemView.getContext(), mInfoBean.areaId,
                        FarmingListActivity.TYPE_FARMING_INFO);
            }
        });
    }

    public void bindData(FarmingInfoBean bean) {
        mInfoBean = bean;
        mFarmingInfoTitleTv.setText(bean.title);
        mFarmingInfoContentTv.setText(bean.desc);
        mFarmingInfoTimeTv.setText(bean.sendTime);
        mFarmingInfoBrowseTv
                .setText(itemView.getResources().getString(R.string.browse_number, bean.counts));
    }
}
