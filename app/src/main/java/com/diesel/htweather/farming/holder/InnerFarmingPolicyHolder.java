package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.event.RecyclerItemEvent;
import com.diesel.htweather.response.FarmingInfoResJO;
import com.diesel.htweather.response.FarmingPolicyResJO;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/27
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class InnerFarmingPolicyHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.farming_info_cover_iv)
    ImageView mFarmingInfoCoverIv;

    @BindView(R.id.farming_info_title_tv)
    TextView mFarmingInfoTitleTv;

    @BindView(R.id.farming_info_source_tv)
    TextView mFarmingInfoSourceTv;

    @BindView(R.id.farming_info_time_tv)
    TextView mFarmingInfoTimeTv;

    public InnerFarmingPolicyHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new RecyclerItemEvent(getLayoutPosition()));
            }
        });
    }

    public void bindData(BaseBean bean) {
        if (bean instanceof FarmingPolicyResJO.ObjEntity.PolicyNewsEntity) {
        } else if (bean instanceof FarmingInfoResJO.ObjEntity.InfoNewsEntity) {
        }
//        mFarmingInfoCoverIv.setImageResource(bean.titleImg);
//        mFarmingInfoTitleTv.setText(bean.title);
//        mFarmingInfoSourceTv.setText(bean.publisher);
//        mFarmingInfoTimeTv.setText(bean.date);
    }
}
