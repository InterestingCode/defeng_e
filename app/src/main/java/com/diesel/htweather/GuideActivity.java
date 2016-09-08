package com.diesel.htweather;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.SharedPreferencesUtils;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.guide_view_pager)
    ViewPager mViewPager;

    private EdgeEffectCompat leftEdge;

    private EdgeEffectCompat rightEdge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        SharedPreferencesUtils.getInstance(this).updateEnterGuidePageStatus();

        initViewPager();
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(new GuidePagerAdapter(this));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        if (leftEdge != null && rightEdge != null) {
//            leftEdge.finish();
//            rightEdge.finish();
//            leftEdge.setSize(0, 0);
//            rightEdge.setSize(0, 0);
//        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        if (rightEdge != null && !rightEdge.isFinished()) { //到了最后一张并且还继续拖动，出现蓝色限制边条了
//            ActivityNav.getInstance().startMainActivity(this);
//            finish();
//        }
    }

    private void initViewPager() {
        try {
            Field leftEdgeField = mViewPager.getClass().getDeclaredField("mLeftEdge");
            Field rightEdgeField = mViewPager.getClass().getDeclaredField("mRightEdge");
            if (leftEdgeField != null && rightEdgeField != null) {
                leftEdgeField.setAccessible(true);
                rightEdgeField.setAccessible(true);
                leftEdge = (EdgeEffectCompat) leftEdgeField.get(mViewPager);
                rightEdge = (EdgeEffectCompat) rightEdgeField.get(mViewPager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(this);
    }

    private static class GuidePagerAdapter extends PagerAdapter {

        private int[] mGuideDrawableRes = {R.drawable.bg_guide_1, R.drawable.bg_guide_2,
                R.drawable.bg_guide_3};

        private Activity mActivity;

        public GuidePagerAdapter(Activity activity) {
            mActivity = activity;
        }

        @Override
        public int getCount() {
            return mGuideDrawableRes.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(container.getContext(), R.layout.pager_item_guide, null);
            ImageView guidePageIv = (ImageView) view.findViewById(R.id.guide_image_iv);
            ImageButton enterBtn = (ImageButton) view.findViewById(R.id.enter_app_btn);
            enterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityNav.getInstance().startMainActivity(mActivity);
                    mActivity.finish();
                }
            });
            enterBtn.setVisibility(position == getCount() - 1 ? View.VISIBLE : View.GONE);
            guidePageIv.setImageResource(mGuideDrawableRes[position]);
            container.addView(view);
            return view;
//            ImageView imageView = new ImageView(container.getContext());
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT);
//            imageView.setLayoutParams(lp);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            imageView.setImageResource(mGuideDrawableRes[position]);
//            container.addView(imageView);
//            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
