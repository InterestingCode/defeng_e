package com.diesel.htweather.farming.holder;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.db.model.FocusArea;
import com.diesel.htweather.event.DeleteFocusAreaEvent;
import com.diesel.htweather.event.DeletePlantAndAreaEvent;
import com.diesel.htweather.response.FocusAreaResJO;
import com.diesel.htweather.user.model.PlantAndAreaBean;
import com.diesel.htweather.user.model.PlantBaseBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/4
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class FocusAreaHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.city_three_tv)
    TextView mPlantNameTv;

    @BindView(R.id.arrow_or_delete_btn)
    ImageView mDeleteBtn;

    Resources mResources;

    private FocusAreaResJO.FocusAreaEntity mBean;

    public FocusAreaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mResources = itemView.getResources();
    }

    public void bindData(FocusAreaResJO.FocusAreaEntity bean) {
        mBean = bean;
        mPlantNameTv.setText(mResources
                .getString(R.string.focus_area_with_args, bean.pvName, bean.ctName, bean.arName));
        mDeleteBtn.setVisibility(bean.showStatus == PlantBaseBean.SHOW_STATUS_DELETE ? View.VISIBLE
                : View.INVISIBLE);
    }

    @OnClick(R.id.arrow_or_delete_btn)
    public void deletePlant() {
        EventBus.getDefault().post(new DeleteFocusAreaEvent(getAdapterPosition(), mBean));
    }
}
