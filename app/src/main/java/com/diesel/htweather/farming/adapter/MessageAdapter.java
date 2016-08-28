package com.diesel.htweather.farming.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.farming.holder.MessageHolder;

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

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
