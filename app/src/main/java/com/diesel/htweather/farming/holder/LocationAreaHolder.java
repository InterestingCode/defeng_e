package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.farming.model.LocationBean;

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

public class LocationAreaHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.location_city_tv)
    TextView mTextView;

    public LocationAreaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(LocationBean bean) {
        mTextView.setText(bean.location.city);
    }
}
