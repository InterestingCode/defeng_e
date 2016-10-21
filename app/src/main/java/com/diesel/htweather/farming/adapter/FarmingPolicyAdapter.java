package com.diesel.htweather.farming.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.farming.holder.InnerFarmingPolicyHolder;
import com.diesel.htweather.response.FarmingInfoResJO;
import com.diesel.htweather.response.FarmingPolicyResJO;

import java.util.List;

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
public class FarmingPolicyAdapter extends RecyclerView.Adapter<InnerFarmingPolicyHolder> {

    private List<BaseBean> mList;

    public FarmingPolicyAdapter(List<BaseBean> list) {
        mList = list;
    }

    @Override
    public InnerFarmingPolicyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InnerFarmingPolicyHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_inner_farming_policy, parent, false));
    }

    @Override
    public void onBindViewHolder(InnerFarmingPolicyHolder holder, int position) {
        int viewType = getItemViewType(position);
        BaseBean bean = mList.get(position);
        if (viewType == 1) {
            holder.bindData((FarmingPolicyResJO.ObjEntity.PolicyNewsEntity) bean);
        } else if (viewType == 2) {
            holder.bindData((FarmingInfoResJO.ObjEntity.InfoNewsEntity) bean);
        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        BaseBean bean = mList.get(position);
        if (bean instanceof FarmingPolicyResJO.ObjEntity.PolicyNewsEntity) {
            viewType = 1;
        } else if (bean instanceof FarmingInfoResJO.ObjEntity.InfoNewsEntity) {
            viewType = 2;
        }
        return viewType;
    }
}
