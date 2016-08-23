package com.diesel.htweather.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.util.ViewUtils;

public class MainTab extends LinearLayout implements Checkable {

    private boolean mChecked;

    private boolean mBroadcasting;

    private Drawable mDrawable;

    private ColorStateList mTextColor;

    private String text;

    private ImageView imageImv, notifyImv;

    private TextView textTxv;

    private OnCheckedChangeListener mOnCheckedChangeListener, mOnCheckedChangeWidgetListener;

    private static final int[] CHECKED_STATE_SET = {
            R.attr.tabChecked
    };

    public MainTab(Context context) {
        this(context, null);
    }

    public MainTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabMenu);
        mDrawable = typedArray.getDrawable(R.styleable.TabMenu_tabDrawable);
        mTextColor = typedArray.getColorStateList(R.styleable.TabMenu_tabTextColor);
        text = typedArray.getString(R.styleable.TabMenu_tabText);

        RelativeLayout mRelativeLayout = new RelativeLayout(context);
        addView(mRelativeLayout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        LinearLayout mLinearLayout = new LinearLayout(context);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        addView(mLinearLayout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        imageImv = new ImageView(context);
        imageImv.setId(R.id.menu_image);
        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(
                ViewUtils.dip2px(getContext(), 24), ViewUtils.dip2px(getContext(), 24));
        imageParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mRelativeLayout.addView(imageImv, imageParams);

        notifyImv = new ImageView(context);
        notifyImv.setImageResource(R.drawable.ic_tip_red_dot);
        RelativeLayout.LayoutParams numberParams = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        numberParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        numberParams.addRule(RelativeLayout.RIGHT_OF, R.id.menu_image);
        numberParams.topMargin = ViewUtils.dip2px(getContext(), 0);
        numberParams.leftMargin = ViewUtils.dip2px(getContext(), -4);
        mRelativeLayout.addView(notifyImv, numberParams);
        notifyImv.setVisibility(INVISIBLE);

//        mCountIcon = new CountIcon(context);
//        mCountIcon.setGravity(Gravity.CENTER);
//        mCountIcon.setTextColor(getResources().getColor(android.R.color.white));
//        mCountIcon.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
//        RelativeLayout.LayoutParams countParams = new RelativeLayout.LayoutParams(
//                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        countParams.topMargin = ViewUtils.dip2px(getContext(), 0);
//        countParams.leftMargin = ViewUtils.dip2px(getContext(), -6);
//        countParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//        countParams.addRule(RelativeLayout.RIGHT_OF, R.id.menu_image);
//        mRelativeLayout.addView(mCountIcon, countParams);

        textTxv = new TextView(context);
        textTxv.setText(text);
        textTxv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                typedArray.getDimensionPixelSize(R.styleable.TabMenu_tabTextSize, 24));
        textTxv.setSingleLine();
        textTxv.setEllipsize(TextUtils.TruncateAt.END);
        textTxv.setMaxEms(5);
        LayoutParams textParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        mLinearLayout.addView(textTxv, textParams);

        setChecked(false);
        typedArray.recycle();
    }

    @Override
    public void toggle() {
        if (!isChecked()) {
            setChecked(!mChecked);
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();

            if (mBroadcasting) {
                return;
            }

            mBroadcasting = true;

            if (mOnCheckedChangeListener != null) {
                mOnCheckedChangeListener.onCheckedChanged(this, mChecked);
            }

            if (mOnCheckedChangeWidgetListener != null) {
                mOnCheckedChangeWidgetListener.onCheckedChanged(this, mChecked);
            }

            mBroadcasting = false;
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    public void setOnCheckedChangeWidgetListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeWidgetListener = listener;
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if (mDrawable != null) {
            int[] myDrawableState = getDrawableState();
            mDrawable.setState(myDrawableState);
            imageImv.setImageDrawable(mDrawable.getCurrent());
        }

        if (mTextColor != null) {
            int[] myDrawableState = getDrawableState();
            textTxv.setTextColor(mTextColor.getColorForState(myDrawableState, 0));
        }

        invalidate();
    }

    public void showNotify() {
        notifyImv.setVisibility(View.VISIBLE);
    }

    public void dismissNotify() {
        notifyImv.setVisibility(View.INVISIBLE);
    }

//    public void setCount(int count) {
//        dismissNotify();
//        mCountIcon.setText("" + count);
//    }

    public void setDrawable(Drawable drawable) {
        if (drawable != null && drawable instanceof StateListDrawable) {
            this.mDrawable = drawable;
        }
    }

    public void setText(String text) {
        if (text != null && !text.isEmpty()) {
            this.text = text;
            textTxv.setText(text);
        }
    }

    public interface OnCheckedChangeListener {

        void onCheckedChanged(MainTab mainTab, boolean isChecked);
    }
}
