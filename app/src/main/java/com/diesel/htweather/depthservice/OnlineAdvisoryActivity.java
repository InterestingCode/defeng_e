package com.diesel.htweather.depthservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.user.EditProblemActivity;
import com.diesel.htweather.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 在线咨询
 */
public class OnlineAdvisoryActivity extends BaseActivity {


    @BindView(R.id.allMsgTab)
    RelativeLayout allMsgTab;

    @BindView(R.id.tvAll)
    TextView tvAll;

    @BindView(R.id.myMsgTab)
    RelativeLayout myMsgTab;

    @BindView(R.id.tvMe)
    TextView tvMe;

    @BindView(R.id.online_pager)
    NoScrollViewPager mViewPager;

    @BindView(R.id.share_layout)
    RelativeLayout mQuestionBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_advisory);
        ButterKnife.bind(this);
        mViewPager.setAdapter(new FragmentAdapter(initFragments(), getSupportFragmentManager()));
        allMsgTab.setOnClickListener(new CustomOnClickListener(0));
        myMsgTab.setOnClickListener(new CustomOnClickListener(1));
    }


    @OnClick({R.id.back_btn, R.id.online_search_btn, R.id.share_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.online_search_btn:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.share_layout:
                startActivity(new Intent(this, EditProblemActivity.class));
                break;
        }

    }

    class CustomOnClickListener implements View.OnClickListener {

        private int index = 0;

        public CustomOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            mViewPager.setCurrentItem(index);
            if (index == 0) {
                allMsgTab.setBackgroundColor(ContextCompat.getColor(OnlineAdvisoryActivity.this, R.color.online_btn_press));
                myMsgTab.setBackgroundColor(ContextCompat.getColor(OnlineAdvisoryActivity.this, R.color.online_btn_normal));
                tvAll.setTextColor(ContextCompat.getColor(OnlineAdvisoryActivity.this, android.R.color.white));
                tvMe.setTextColor(ContextCompat.getColor(OnlineAdvisoryActivity.this, R.color.gray_666));
            } else {
                allMsgTab.setBackgroundColor(ContextCompat.getColor(OnlineAdvisoryActivity.this, R.color.online_btn_normal));
                myMsgTab.setBackgroundColor(ContextCompat.getColor(OnlineAdvisoryActivity.this, R.color.online_btn_press));
                tvAll.setTextColor(ContextCompat.getColor(OnlineAdvisoryActivity.this, R.color.gray_666));
                tvMe.setTextColor(ContextCompat.getColor(OnlineAdvisoryActivity.this, android.R.color.white));
            }
        }
    }


    private List<BaseFragment> initFragments() {
        List<BaseFragment> fragments = new ArrayList<BaseFragment>();

        BaseFragment allMsgFragment = new OnlineAllFragment();
        fragments.add(allMsgFragment);

        BaseFragment myMsgFragment = new OnlineMeFragment();
        fragments.add(myMsgFragment);

        return fragments;
    }

    class FragmentAdapter extends FragmentPagerAdapter {

        private List<BaseFragment> mFragments;

        public FragmentAdapter(List<BaseFragment> fragments, FragmentManager fm) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

}
