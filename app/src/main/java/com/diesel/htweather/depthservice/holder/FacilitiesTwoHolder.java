package com.diesel.htweather.depthservice.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.diesel.htweather.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhoujiangsen on 16/10/21.
 */
public class FacilitiesTwoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rbFacilitiesSelect)
    public RadioButton rbFacilitiesSelect;

    @BindView(R.id.tvFacilitiesTitle)
    public TextView tvFacilitiesTitle;

    public FacilitiesTwoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EventBus.getDefault().post(new RecyclerItemEvent(getLayoutPosition()));
//            }
//        });
    }
}
