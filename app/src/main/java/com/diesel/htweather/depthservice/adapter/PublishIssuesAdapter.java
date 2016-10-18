package com.diesel.htweather.depthservice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.depthservice.holder.AddPublishIssuesHolder;
import com.diesel.htweather.depthservice.holder.PublishIssuesHolder;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/11
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class PublishIssuesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_PHOTO = 1;

    private static final int TYPE_ADD_PHOTO = 2;

    private List<String> mPhotos;

    private Context mContext;

    public PublishIssuesAdapter(Context context, List<String> photos) {
        mContext = context;
        mPhotos = photos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == TYPE_PHOTO) {
            holder = new PublishIssuesHolder(mContext, LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_edit_problem_photo, parent, false));
        } else if (viewType == TYPE_ADD_PHOTO) {
            holder = new AddPublishIssuesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_edit_problem_add_photo, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_PHOTO) {
            ((PublishIssuesHolder) holder).bindData(mPhotos.get(position));
        }
    }

    @Override
    public int getItemCount() {
        int count = null == mPhotos ? 0 : mPhotos.size();
        return Math.min(count, 4);
    }

    @Override
    public int getItemViewType(int position) {
        return "add_photo".equals(mPhotos.get(position)) ? TYPE_ADD_PHOTO : TYPE_PHOTO;
    }

}
