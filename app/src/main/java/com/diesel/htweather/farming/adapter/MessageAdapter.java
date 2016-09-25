package com.diesel.htweather.farming.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.farming.holder.MessageHolder;
import com.diesel.htweather.response.MessageResJO;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/27
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageHolder> {

    private List<MessageResJO.MessageEntity> mList;

    public MessageAdapter(List<MessageResJO.MessageEntity> list) {
        mList = list;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        holder.bindData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return null != mList ? mList.size() : 0;
    }
}
