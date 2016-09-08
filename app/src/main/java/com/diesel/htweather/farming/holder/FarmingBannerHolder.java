package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.diesel.htweather.R;

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
public class FarmingBannerHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.farming_banner_iv)
    ImageView mBannerIv;

    public FarmingBannerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData() {
        if (getAdapterPosition() == 2) {
            mBannerIv.setImageResource(R.drawable.banner_depth_service);
        } else {
            mBannerIv.setImageResource(R.drawable.banner_farming_info);
        }
    }
}
