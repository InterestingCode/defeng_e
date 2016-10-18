package com.diesel.htweather.depthservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.depthservice.model.CommentsBean;

import java.util.List;


/**
 * Created by zhoujiangsen on 2016/10/18.
 */

public class CommentsAdapter extends BaseAdapter {

    private List<CommentsBean> mCommentsList;
    private LayoutInflater mInflater;

    public CommentsAdapter(Context context, List<CommentsBean> mList) {
        //setSortList(mList);
        mCommentsList = mList;
        mInflater = LayoutInflater.from(context);
    }

    public List<CommentsBean> getCommentsList() {
        return mCommentsList;
    }

    @Override
    public int getCount() {
        return mCommentsList.size();
    }

    @Override
    public CommentsBean getItem(int position) {
        return mCommentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        CommentsBean commentsBean = mCommentsList.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.comment_item, null, false);
            viewHolder.commentContent = (TextView) convertView.findViewById(R.id.tvCommentsItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if ("1".equals(commentsBean.getCmType())) {
            viewHolder.commentContent.setText(commentsBean.getFromUserNickName() + ": " + commentsBean.getComment());
        } else {
            String msg = commentsBean.getFromUserNickName() + " 回复 " + commentsBean.getToUserNickName() + ": " + commentsBean.getComment();
            viewHolder.commentContent.setText(msg);
        }

        return convertView;
    }

//    private void setSortList(List<CommentsBean> mCommentsList) {
//        Collections.sort(mCommentsList, new Comparator<CommentsBean>() {
//            @Override
//            public int compare(CommentsBean t1, CommentsBean t2) {
//                if (Integer.valueOf(t1.getCmId()) > Integer.valueOf(t2.getCmId())) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
//        });
//    }

    class ViewHolder {
        TextView commentContent;
    }
}



