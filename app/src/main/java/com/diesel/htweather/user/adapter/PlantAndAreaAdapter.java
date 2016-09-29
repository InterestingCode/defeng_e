package com.diesel.htweather.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
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
public class PlantAndAreaAdapter extends RecyclerView.Adapter {

    private static final int TYPE_SHOW_LIST = 1;

    private static final int TYPE_SHOW_DELETE = 2;

    private List<PlantBaseBean> mPlants;

    public PlantAndAreaAdapter(List<PlantBaseBean> plants) {
        mPlants = plants;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder;
        if (viewType == TYPE_SHOW_LIST) {
            holder = new PlantAndAreaHolder(
                    inflater.inflate(R.layout.list_item_plant_and_area, parent, false));
        } else if (viewType == TYPE_SHOW_DELETE) {
            holder = new AddPlantAndAreaHolder(
                    inflater.inflate(R.layout.list_item_add_plant, parent, false));
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
            ((PlantAndAreaHolder) holder).bindData((PlantAndAreaBean) bean);
        } else if (viewType == TYPE_SHOW_DELETE) {
            ((AddPlantAndAreaHolder) holder).bindData((AddPlantBean) bean);
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
        if (PlantAndAreaBean.class.isInstance(bean)) {
            viewType = TYPE_SHOW_LIST;
        } else if (AddPlantBean.class.isInstance(bean)) {
            viewType = TYPE_SHOW_DELETE;
        }
        return viewType;
    }
}
