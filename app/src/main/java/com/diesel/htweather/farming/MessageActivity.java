package com.diesel.htweather.farming;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.diesel.htweather.MainPagerAdapter;
import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.widget.MainTab;
import com.diesel.htweather.widget.MainTabBar;
import com.diesel.htweather.widget.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {

    @BindView(R.id.message_tab)
    MainTabBar mMessageTab;

    @BindView(R.id.message_pager)
    NoScrollViewPager mMessagePager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);

        initFragments();
        addTabBarChangeListener();
        mMessageTab.setCurrentTabBar(0);
        mMessagePager.setCurrentItem(0, false);
    }

    private void initFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(MessageFragment.newInstance(MessageFragment.TYPE_SYSTEM_MESSAGE));
        fragments.add(MessageFragment.newInstance(MessageFragment.TYPE_NOTICE_MESSAGE));
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.setList(fragments);
        mMessagePager.setAdapter(adapter);
    }

    private void addTabBarChangeListener() {
        for (int i = 0; i < mMessageTab.getChildCount(); i++) {
            MainTab tab = (MainTab) (mMessageTab.getChildAt(i));
            tab.setOnClickListener(new TabClickListener());
            tab.setOnCheckedChangeListener(new TabChangeListener());
        }
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
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
                int position = mMessageTab.getIndexOfChild(tab);
                if (position != -1) {
                    mMessagePager.setCurrentItem(position, false);
                }
            }
        }
    }
}
