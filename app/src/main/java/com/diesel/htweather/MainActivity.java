package com.diesel.htweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.map.MapLocationClient;
import com.diesel.htweather.service.JobIntentService;
import com.diesel.htweather.util.BackToExitUtil;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.widget.MainTab;
import com.diesel.htweather.widget.MainTabBar;
import com.diesel.htweather.widget.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Comments：主页
 *
 * @author Diesel
 *         Time: 2016/8/13
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.main_tab)
    MainTabBar mTabBar;

    @BindView(R.id.main_pager)
    NoScrollViewPager mMainPager;

    private BackToExitUtil mBackToExit = new BackToExitUtil();

    private MapLocationClient mMapLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        addTabBarChangeListener();
        mTabBar.setCurrentTabBar(0);
        mMainPager.setCurrentItem(0, false);

        startService(new Intent(this, JobIntentService.class));
        mMapLocationClient = new MapLocationClient(this);
        mMapLocationClient.startLocation();
    }

    private void initView() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        String name;
        name = getResources().getString(R.string.main_tab_one_class);
        if (!TextUtils.isEmpty(name)) {
            fragments.add(Fragment.instantiate(this, name, null));
        }
        mTabBar.getChildAt(0).setTag(name);

        name = getResources().getString(R.string.main_tab_two_class);
        if (!TextUtils.isEmpty(name)) {
            fragments.add(Fragment.instantiate(this, name));
        }
        mTabBar.getChildAt(1).setTag(name);

        name = getResources().getString(R.string.main_tab_three_class);
        if (!TextUtils.isEmpty(name)) {
            fragments.add(Fragment.instantiate(this, name));
        }
        mTabBar.getChildAt(2).setTag(name);

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.setList(fragments);
        mMainPager.setAdapter(adapter);
        mMainPager.setOffscreenPageLimit(4);
    }

    private void addTabBarChangeListener() {
        mMainPager.addOnPageChangeListener(new PagerChangeListener());
        for (int i = 0; i < mTabBar.getChildCount(); i++) {
            MainTab tab = (MainTab) (mTabBar.getChildAt(i));
            tab.setOnClickListener(new TabClickListener());
            tab.setOnCheckedChangeListener(new TabChangeListener());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            pressAgainToExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void pressAgainToExit() {
        if (mBackToExit.isExit()) {
            finish();
            System.gc();
        } else {
            ToastUtils.show(getString(R.string.again_back_to_exit));
            mBackToExit.doExitInOneSecond();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mMapLocationClient) {
            mMapLocationClient.destroy();
            mMapLocationClient = null;
        }
    }

    private class PagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
    }

    private class TabClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            MainTab tab = (MainTab) view;
            tab.toggle();
        }
    }

    private class TabChangeListener implements MainTab.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(MainTab tab, boolean isChecked) {
            if (isChecked) {
                int position = mTabBar.getIndexOfChild(tab);
                if (position != -1) {
                    mMainPager.setCurrentItem(position, false);
                }
            }
        }
    }

}
