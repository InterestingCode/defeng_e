package com.diesel.htweather.depthservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.depthservice.model.GrowthDiaryBean;

import java.util.List;

/**
 * Created by penley on 16/9/6.
 */
public class DepthDiaryAdapter extends BaseAdapter {

    List<GrowthDiaryBean> mGrowthDiaryBeanList;

    private LayoutInflater mInflater;

    public DepthDiaryAdapter(Context context, List<GrowthDiaryBean> objects) {
        mInflater = LayoutInflater.from(context);
        mGrowthDiaryBeanList = objects;
    }

    public List<GrowthDiaryBean> getGrowthDiaryBeanList() {
        return mGrowthDiaryBeanList;
    }

    @Override
    public int getCount() {
        return mGrowthDiaryBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGrowthDiaryBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        GrowthDiaryBean growthDiaryBean = mGrowthDiaryBeanList.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_growth_diary, null, false);
            viewHolder.tvGrowthLunar = (TextView) convertView.findViewById(R.id.tvGrowthLunar);
            viewHolder.tvGrowthTitle = (TextView) convertView.findViewById(R.id.tvGrowthTitle);
            viewHolder.tvGrowthTime = (TextView) convertView.findViewById(R.id.tvGrowthTime);
            viewHolder.tvGrowthReadNum = (TextView) convertView.findViewById(R.id.tvGrowthReadNum);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvGrowthLunar.setText(growthDiaryBean.getLunarStr());
        viewHolder.tvGrowthTitle.setText(growthDiaryBean.getTitle());
        viewHolder.tvGrowthTime.setText(growthDiaryBean.getTime());
        viewHolder.tvGrowthReadNum.setText("阅读 " + growthDiaryBean.getReadCount());

        return convertView;
    }

    class ViewHolder {
        TextView tvGrowthLunar;
        TextView tvGrowthTitle;
        TextView tvGrowthTime;
        TextView tvGrowthReadNum;
    }
}
