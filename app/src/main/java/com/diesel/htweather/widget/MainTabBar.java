package com.diesel.htweather.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainTabBar extends LinearLayout {

    private int mCheckedId = View.NO_ID;

    private MainTab.OnCheckedChangeListener mChildOnCheckedChangeListener;

    private boolean mProtectFromCheckedChange = false;

    private OnCheckedChangeListener mOnCheckedChangeListener;

    private PassThroughHierarchyChangeListener mPassThroughListener;

    public MainTabBar(Context context) {
        super(context);
        init();
    }

    public MainTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MainTabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            mChildOnCheckedChangeListener = new CheckedStateTracker();
            mPassThroughListener = new PassThroughHierarchyChangeListener();
            super.setOnHierarchyChangeListener(mPassThroughListener);
        }
    }

    public void setCurrentTabBar(int position) {
        MainTab tab = (MainTab) getChildAt(position);
        if (tab != null) {
            tab.setChecked(true);
        }
    }

    public int getIndexOfChild(MainTab tab) {
        int position = indexOfChild(tab);

        for (int i = 0; i < indexOfChild(tab); i++) {
            if (getChildAt(i).getVisibility() == GONE) {
                position--;
            }
        }

        return position;
    }

    @Override
    public void setOnHierarchyChangeListener(OnHierarchyChangeListener listener) {
        mPassThroughListener.mOnHierarchyChangeListener = listener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mCheckedId != -1) {
            mProtectFromCheckedChange = true;
            setCheckedStateForView(mCheckedId, true);
            mProtectFromCheckedChange = false;
            setCheckedId(mCheckedId);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof MainTab) {
            final MainTab button = (MainTab) child;
            if (button.isChecked()) {
                mProtectFromCheckedChange = true;
                if (mCheckedId != -1) {
                    setCheckedStateForView(mCheckedId, false);
                }
                mProtectFromCheckedChange = false;
                setCheckedId(button.getId());
            }
        }

        super.addView(child, index, params);
    }

    private void setCheckedId(int id) {
        mCheckedId = id;
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, mCheckedId);
        }
    }

    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView != null && checkedView instanceof MainTab) {
            ((MainTab) checkedView).setChecked(checked);
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    public interface OnCheckedChangeListener {

        public void onCheckedChanged(MainTabBar group, int checkedId);
    }

    private class CheckedStateTracker implements MainTab.OnCheckedChangeListener {

        public void onCheckedChanged(MainTab buttonView, boolean isChecked) {
            if (mProtectFromCheckedChange) {
                return;
            }

            mProtectFromCheckedChange = true;
            if (mCheckedId != -1) {
                setCheckedStateForView(mCheckedId, false);
            }
            mProtectFromCheckedChange = false;

            int id = buttonView.getId();
            setCheckedId(id);
        }
    }

    private class PassThroughHierarchyChangeListener implements OnHierarchyChangeListener {

        private ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;

        public void onChildViewAdded(View parent, View child) {
            if (parent == MainTabBar.this && child instanceof MainTab) {
                ((MainTab) child).setOnCheckedChangeWidgetListener(
                        mChildOnCheckedChangeListener);
            }

            if (mOnHierarchyChangeListener != null) {
                mOnHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        public void onChildViewRemoved(View parent, View child) {
            if (parent == MainTabBar.this && child instanceof MainTab) {
                ((MainTab) child).setOnCheckedChangeWidgetListener(null);
            }

            if (mOnHierarchyChangeListener != null) {
                mOnHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }
}
