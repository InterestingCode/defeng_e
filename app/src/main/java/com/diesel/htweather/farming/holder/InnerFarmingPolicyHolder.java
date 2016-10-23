package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.event.RecyclerItemEvent;
import com.diesel.htweather.farming.FarmingListActivity;
import com.diesel.htweather.response.FarmingInfoResJO;
import com.diesel.htweather.response.FarmingPolicyResJO;
import com.diesel.htweather.util.ActivityNav;
import com.facebook.drawee.view.SimpleDraweeView;

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
    SimpleDraweeView mFarmingInfoCoverIv;

    @BindView(R.id.farming_info_title_tv)
    TextView mFarmingInfoTitleTv;

    @BindView(R.id.farming_info_source_tv)
    TextView mFarmingInfoSourceTv;

    @BindView(R.id.farming_info_time_tv)
    TextView mFarmingInfoTimeTv;

    FarmingPolicyResJO.ObjEntity.PolicyNewsEntity mPolicyNewsEntity;

    FarmingInfoResJO.ObjEntity.InfoNewsEntity mInfoNewsEntity;

    public InnerFarmingPolicyHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mInfoNewsEntity) {
                    ActivityNav.getInstance().startFarmingDetailsActivity(itemView.getContext(),
                            mInfoNewsEntity.newsId, FarmingListActivity.TYPE_FARMING_INFO);
                } else if (null != mPolicyNewsEntity) {
                    ActivityNav.getInstance().startFarmingDetailsActivity(itemView.getContext(),
                            mPolicyNewsEntity.newsId, FarmingListActivity.TYPE_FARMING_POLICY);
                }
            }
        });
    }

    public void bindData(BaseBean bean) {
        Log.i("FarmingListActivity", "bindData()");
        if (bean instanceof FarmingPolicyResJO.ObjEntity.PolicyNewsEntity) {
            mPolicyNewsEntity = (FarmingPolicyResJO.ObjEntity.PolicyNewsEntity) bean;
            FarmingPolicyResJO.ObjEntity.PolicyNewsEntity policyNewsEntity
                    = (FarmingPolicyResJO.ObjEntity.PolicyNewsEntity) bean;
            mFarmingInfoCoverIv.setImageURI(policyNewsEntity.titleImg);
            mFarmingInfoTitleTv.setText(policyNewsEntity.title);
            mFarmingInfoSourceTv.setText(policyNewsEntity.sourceWay);
            mFarmingInfoTimeTv.setText(policyNewsEntity.sendTime);
        } else if (bean instanceof FarmingInfoResJO.ObjEntity.InfoNewsEntity) {
            mInfoNewsEntity = (FarmingInfoResJO.ObjEntity.InfoNewsEntity) bean;
            FarmingInfoResJO.ObjEntity.InfoNewsEntity infoNewsEntity
                    = (FarmingInfoResJO.ObjEntity.InfoNewsEntity) bean;
            mFarmingInfoCoverIv.setImageURI(infoNewsEntity.titleImg);
            mFarmingInfoTitleTv.setText(infoNewsEntity.title);
            mFarmingInfoSourceTv.setText(infoNewsEntity.sourceWay);
            mFarmingInfoTimeTv.setText(infoNewsEntity.sendTime);
        }
    }
}
