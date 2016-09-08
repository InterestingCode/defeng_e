package com.diesel.htweather.farming.holder;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.diesel.htweather.R;
import com.diesel.htweather.farming.FarmingListActivity;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.widget.SlidingTableTabLayout;

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

    public ActualFarmingHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mActualFarmingPager.setAdapter(new ActualFramingAdapter());
        mActualFarmingTabsLayout.setViewPager(mActualFarmingPager);
    }

    @OnClick(R.id.actual_farming_setting_btn)
    public void addPlant() {
        ActivityNav.getInstance().startAddWatchPlantActivity(itemView.getContext());
    }

    private static class ActualFramingAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            String[] s = {"水稻", "大豆", "玉米", "番茄"};
            return s[position];
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final Context context = container.getContext();
            View view = View.inflate(context, R.layout.pager_item_actual_framing_data, null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityNav.getInstance().startFarmingDetailsActivity(context);
                }
            });
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
