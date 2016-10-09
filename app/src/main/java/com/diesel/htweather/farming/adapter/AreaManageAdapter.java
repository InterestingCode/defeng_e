package com.diesel.htweather.farming.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.db.model.FocusArea;
import com.diesel.htweather.farming.holder.AddFocusAreaHolder;
import com.diesel.htweather.farming.holder.FocusAreaHolder;
import com.diesel.htweather.response.FocusAreaResJO;
import com.diesel.htweather.user.holder.AddPlantAndAreaHolder;
import com.diesel.htweather.user.holder.PlantAndAreaHolder;
import com.diesel.htweather.user.model.AddPlantBean;
import com.diesel.htweather.user.model.PlantAndAreaBean;
import com.diesel.htweather.user.model.PlantBaseBean;

import java.util.List;

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
public class AreaManageAdapter extends RecyclerView.Adapter {

    private static final int TYPE_SHOW_LIST = 1;

    private static final int TYPE_SHOW_DELETE = 2;

    private List<PlantBaseBean> mPlants;

    public AreaManageAdapter(List<PlantBaseBean> plants) {
        mPlants = plants;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder;
        if (viewType == TYPE_SHOW_LIST) {
            holder = new FocusAreaHolder(
                    inflater.inflate(R.layout.list_item_focus_area, parent, false));
        } else if (viewType == TYPE_SHOW_DELETE) {
            holder = new AddFocusAreaHolder(
                    inflater.inflate(R.layout.list_item_add_area, parent, false));
        } else {
            View emptyView = new View(parent.getContext());
            holder = new RecyclerView.ViewHolder(emptyView) {
            };
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        PlantBaseBean bean = mPlants.get(position);
        if (viewType == TYPE_SHOW_LIST) {
            ((FocusAreaHolder) holder).bindData((FocusAreaResJO.FocusAreaEntity) bean);
        } else if (viewType == TYPE_SHOW_DELETE) {
            ((AddFocusAreaHolder) holder).bindData((AddPlantBean) bean);
        }
    }

    @Override
    public int getItemCount() {
        return null == mPlants ? 0 : mPlants.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        PlantBaseBean bean = mPlants.get(position);
        if (FocusAreaResJO.FocusAreaEntity.class.isInstance(bean)) {
            viewType = TYPE_SHOW_LIST;
        } else if (AddPlantBean.class.isInstance(bean)) {
            viewType = TYPE_SHOW_DELETE;
        }
        return viewType;
    }
}
