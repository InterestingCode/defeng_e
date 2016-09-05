package com.diesel.htweather.user.holder;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.event.RecyclerItemEvent;
import com.diesel.htweather.user.model.PlantCategoryBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/5
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class PlantCategoryHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.highlight_view)
    ImageView mHighlightView;

    @BindView(R.id.plant_category_tv)
    TextView mPlantCategoryTv;

    private int mSelectBgColor, mNormalBgColor, mSelectTxtColor, mNormalTxtColor;

    public PlantCategoryHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mSelectBgColor = ContextCompat.getColor(itemView.getContext(), android.R.color.white);
        mNormalBgColor = ContextCompat.getColor(itemView.getContext(), R.color.bg_activity);
        mSelectTxtColor = ContextCompat.getColor(itemView.getContext(), R.color.black_333);
        mNormalTxtColor = ContextCompat.getColor(itemView.getContext(), R.color.gray_666);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new RecyclerItemEvent(getAdapterPosition()));
            }
        });
    }

    public void bindData(PlantCategoryBean bean) {
        mPlantCategoryTv.setText(bean.category);
        mPlantCategoryTv.setTextColor(bean.isSelectedItem ? mSelectTxtColor : mNormalTxtColor);
        mHighlightView.setVisibility(bean.isSelectedItem ? View.VISIBLE : View.INVISIBLE);
        itemView.setBackgroundColor(bean.isSelectedItem ? mSelectBgColor : mNormalBgColor);
    }
}
