package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.response.MessageResJO;
import com.diesel.htweather.util.ActivityNav;

import butterknife.BindView;
import butterknife.ButterKnife;

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
public class MessageHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.message_title_tv)
    TextView mMessageTitleTv;

    @BindView(R.id.message_time_tv)
    TextView mMessageTimeTv;

    @BindView(R.id.message_content_tv)
    TextView mMessageContentTv;

    public MessageHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityNav.getInstance().startMessageDetailsActivity(itemView.getContext());
            }
        });
    }

    public void bindData(MessageResJO.MessageEntity entity) {
        mMessageTitleTv.setText(entity.channelName);
        mMessageContentTv.setText(entity.content);
        mMessageTimeTv.setText(entity.addTime);
    }
}
