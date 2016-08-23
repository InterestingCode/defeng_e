package com.diesel.htweather.farming.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.diesel.htweather.R;
import com.diesel.htweather.widget.SlidingTableTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

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
            View view = View
                    .inflate(container.getContext(), R.layout.pager_item_acutal_framing_data, null);
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
