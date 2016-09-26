package com.diesel.htweather.widget;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.listener.RecyclerItemClickListener;
import com.diesel.htweather.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class ActionDialogAdapter extends BaseAdapter {

    private List<FontColor> mStrings = new ArrayList<>();

    private List<String> mPicPaths = new ArrayList<>();

    public static class FontColor {

        public String content;

        public int color;

        public FontColor(String content) {
            this(content, R.color.black_333);
        }

        public FontColor(String content, @ColorRes int color) {
            this.content = content;
            this.color = color;
        }
    }

    private RecyclerItemClickListener mListener;


    public void addData(List<FontColor> strings, ArrayList<String> picPaths) {
        mStrings.clear();
        mPicPaths.clear();
        if (strings != null) {
            mStrings.addAll(strings);
        }
        if (picPaths != null && picPaths.size() > 0) {
            mPicPaths.addAll(picPaths);
        }
        notifyDataSetChanged();
    }

    public void setOnClickListener(RecyclerItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getCount() {
        return null == mStrings ? 0 : mStrings.size();
    }

    @Override
    public Object getItem(int position) {
        return null == mStrings ? "" : mStrings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(
                R.layout.common_action_sheet_item, parent, false);
        TextView mTextView = (TextView) view.findViewById(R.id.item_tv);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_image);

        mTextView.setText(mStrings.get(position).content);
        mTextView.setTextColor(context.getResources().getColor(mStrings.get(position).color));
        if (mPicPaths.size() > 0) {
            ViewUtils.visible(imageView);
        } else {
            ViewUtils.gone(imageView);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onRecyclerItemClick(position);
                }
            }
        });
        return view;
    }
}
