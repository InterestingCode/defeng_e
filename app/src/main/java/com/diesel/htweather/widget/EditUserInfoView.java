package com.diesel.htweather.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diesel.htweather.R;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/2
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class EditUserInfoView extends LinearLayout {

    TextView mColumnNameTv;

    TextView mColumnContentTv;

    ImageView mColumnIconIv;

    CheckBox mColumnCheckBox;

    ImageView mRightArrow;

    public EditUserInfoView(Context context) {
        this(context, null);
    }

    public EditUserInfoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditUserInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.item_edit_user_info_layout, this);
        mColumnNameTv = (TextView) view.findViewById(R.id.column_name_tv);
        mColumnContentTv = (TextView) view.findViewById(R.id.column_content_tv);
        mColumnIconIv = (ImageView) view.findViewById(R.id.column_icon_iv);
        mColumnCheckBox = (CheckBox) view.findViewById(R.id.column_check_box);
        mRightArrow = (ImageView) view.findViewById(R.id.right_arrow_iv);
        TypedArray ta = context
                .obtainStyledAttributes(attrs, R.styleable.EditUserInfoView, defStyleAttr, 0);
        String name = ta.getString(R.styleable.EditUserInfoView_column_name);
        String content = ta.getString(R.styleable.EditUserInfoView_column_content);
        Drawable icon = ta.getDrawable(R.styleable.EditUserInfoView_column_icon);
        Drawable checkBox = ta.getDrawable(R.styleable.EditUserInfoView_column_check_box);
        boolean hideArrow = ta.getBoolean(R.styleable.EditUserInfoView_hide_arrow, false);
        mColumnNameTv.setText(name);
        mColumnContentTv.setText(content);
        if (null != icon) {
            mColumnIconIv.setVisibility(VISIBLE);
            mColumnIconIv.setImageDrawable(icon);
        }
        if (null != checkBox) {
            mColumnCheckBox.setVisibility(VISIBLE);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                mColumnCheckBox.setBackgroundDrawable(checkBox);
            } else {
                mColumnCheckBox.setBackground(checkBox);
            }
        }
        mRightArrow.setVisibility(hideArrow ? GONE : VISIBLE);
        ta.recycle();
    }

    public CheckBox getColumnCheckBox() {
        return mColumnCheckBox;
    }

    public void setContent(String content) {
        mColumnContentTv.setText(content);
    }
}
