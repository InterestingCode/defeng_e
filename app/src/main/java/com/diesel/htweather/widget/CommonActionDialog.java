package com.diesel.htweather.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.listener.RecyclerItemClickListener;
import com.diesel.htweather.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class CommonActionDialog extends Dialog {

    private Button mCancelBtn;

    private FullListView mDialogList;

    private TextView mPromptTv;

    private ActionDialogAdapter mAdapter;

    public CommonActionDialog(Context context) {
        this(context, 0);
    }

    public CommonActionDialog(Context context, int theme) {
        super(context, theme == 0 ? R.style.ActionSheetDialog : theme);
        final Window window = getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        View rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.common_action_sheet_dialog, null);

        mDialogList = (FullListView) rootView.findViewById(R.id.dialog_list);
        mCancelBtn = (Button) rootView.findViewById(R.id.dialog_btn_cancel);
        mPromptTv = (TextView) rootView.findViewById(R.id.dialog_tip_title);
        mAdapter = new ActionDialogAdapter();
        mDialogList.setAdapter(mAdapter);
        mCancelBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });

        setContentView(rootView);
    }

    /**
     * 添加dialog每个item的点击事件
     */
    public void addOnClickListener(RecyclerItemClickListener listener) {
        if (null != mAdapter) {
            mAdapter.setOnClickListener(listener);
        }
    }

    /**
     * 添加dialog显示的文本数据
     */
    public void addDialogContent(List<ActionDialogAdapter.FontColor> strings) {
        if (null != mAdapter) {
            mAdapter.addData(strings, null);
        }
    }

    /**
     * 添加dialog显示的文本与图片数据
     */
    public void addDialogContentAndImg(List<ActionDialogAdapter.FontColor> strings,
            ArrayList<String> picPath) {
        if (null != mAdapter) {
            mAdapter.addData(strings, picPath);
        }
    }

    /**
     * 添加顶部提示文案
     */
    public void setTopPrompt(String prompt) {
        ViewUtils.visible(mPromptTv);
        mPromptTv.setText(prompt);
    }

    public void setGoneTopPrompt(){
        ViewUtils.gone(mPromptTv);
    }

    /**
     * 设置“取消”按钮文案（默认‘取消’）
     */
    public void setCancelTxt(String cancelTxt) {
        mCancelBtn.setText(cancelTxt);
    }
}
