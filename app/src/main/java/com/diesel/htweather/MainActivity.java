package com.diesel.htweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.diesel.htweather.widget.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_pager)
    NoScrollViewPager mMainPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(FarmingFragment.newInstance());
        fragments.add(DepthServiceFragment.newInstance());
        fragments.add(UserInfoFragment.newInstance());
        fragments.add(UserInfoFragment.newInstance());
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.setList(fragments);
        mMainPager.setAdapter(adapter);
        mMainPager.setOffscreenPageLimit(4);
        mMainPager.setCurrentItem(0, false);
    }

    @OnClick({R.id.farming_btn, R.id.service_btn, R.id.userinfo_btn, R.id.online_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.farming_btn:
                mMainPager.setCurrentItem(0);
                break;
            case R.id.service_btn:
                mMainPager.setCurrentItem(1);
                break;
            case R.id.userinfo_btn:
                mMainPager.setCurrentItem(2);
                break;
            case R.id.online_btn:
                mMainPager.setCurrentItem(3);
                break;
        }
    }
}
