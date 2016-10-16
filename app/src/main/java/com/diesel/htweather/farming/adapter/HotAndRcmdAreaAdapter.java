package com.diesel.htweather.farming.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.farming.holder.HotAndRcmdAreaHolder;
import com.diesel.htweather.response.LocationResJO;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/16
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class HotAndRcmdAreaAdapter extends RecyclerView.Adapter<HotAndRcmdAreaHolder> {

    public List<LocationResJO.AreaListEntity.HotAreaListEntity> hotAreaList;

    public HotAndRcmdAreaAdapter() {
    }

    public void setData(List<LocationResJO.AreaListEntity.HotAreaListEntity> hotAreaList) {
        this.hotAreaList = hotAreaList;
        notifyDataSetChanged();
    }

    @Override
    public HotAndRcmdAreaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotAndRcmdAreaHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_plant_name, parent, false));
    }

    @Override
    public void onBindViewHolder(HotAndRcmdAreaHolder holder, int position) {
        holder.binData(hotAreaList.get(position));
    }

    @Override
    public int getItemCount() {
        return null != hotAreaList ? hotAreaList.size() : 0;
    }
}
