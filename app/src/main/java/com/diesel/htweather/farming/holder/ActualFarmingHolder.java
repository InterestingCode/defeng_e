package com.diesel.htweather.farming.holder;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.farming.FarmingListActivity;
import com.diesel.htweather.farming.model.ActualFarmingBean;
import com.diesel.htweather.response.FarmingResJO;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.widget.SlidingTableTabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/8/23
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */
public class ActualFarmingHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.actual_farming_setting_btn)
    ImageView mActualFarmingSettingBtn;

    @BindView(R.id.actual_farming_tabs_layout)
    SlidingTableTabLayout mActualFarmingTabsLayout;

    @BindView(R.id.more_iv)
    ImageView mMoreIv;

    @BindView(R.id.actual_farming_pager)
    ViewPager mActualFarmingPager;

    private ActualFramingAdapter mAdapter;

    public ActualFarmingHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(ActualFarmingBean bean) {
        List<FarmingResJO.ObjEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity>
                entities = bean.mTimelyCropsNewsListEntities;
        if (null == mAdapter) {
            mAdapter = new ActualFramingAdapter(entities);
            mActualFarmingPager.setAdapter(mAdapter);
            mActualFarmingTabsLayout.setViewPager(mActualFarmingPager);
        } else {
            mAdapter.setEntities(entities);
        }
    }

    @OnClick(R.id.actual_farming_setting_btn)
    public void addPlant() {
        ActivityNav.getInstance().startAddWatchPlantActivity(itemView.getContext(), 0);
    }

    private static class ActualFramingAdapter extends PagerAdapter {

        private List<FarmingResJO.ObjEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity>
                mEntities;

        public ActualFramingAdapter(
                List<FarmingResJO.ObjEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity> entities) {
            mEntities = entities;
        }

        public void setEntities(
                List<FarmingResJO.ObjEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity> entities) {
            mEntities = entities;
            notifyDataSetChanged();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getEntity(position).cropName;
        }

        private FarmingResJO.ObjEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity getEntity(
                int position) {
            return mEntities.get(position);
        }

        @Override
        public int getCount() {
            return null == mEntities ? 0 : mEntities.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final Context context = container.getContext();
            final FarmingResJO.ObjEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity
                    entity = getEntity(position);
            View view = View.inflate(context, R.layout.pager_item_actual_framing_data, null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityNav.getInstance().startFarmingDetailsActivity(context, entity.newsId,
                            FarmingListActivity.TYPE_FARMING_INFO);
                }
            });
            TextView mTipsTitleTv = (TextView) view.findViewById(R.id.tips_title_tv);
            TextView mTipsTimeTv = (TextView) view.findViewById(R.id.tips_time_tv);
            TextView mTipsDataTv = (TextView) view.findViewById(R.id.tips_data_tv);
            mTipsTitleTv.setText(entity.title);
            mTipsTimeTv.setText(entity.sendTime);
            mTipsDataTv.setText(entity.content);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
