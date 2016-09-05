package com.diesel.htweather.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.diesel.htweather.user.holder.GatherDataPhotoHolder;
import com.diesel.htweather.util.Drawables;
import com.diesel.htweather.util.ViewUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/9/5
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */
public class GatherDataPhotoAdapter extends RecyclerView.Adapter<GatherDataPhotoHolder> {

    private List<String> mPhotos;

    public GatherDataPhotoAdapter(List<String> photos) {
        mPhotos = photos;
    }

    @Override
    public GatherDataPhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int width = ViewUtils.dip2px(context, 110);
        int height = ViewUtils.dip2px(context, 70);
        Log.d("GatherDataPhotoAdapter", "width=" + width + "; height=" + height);
        SimpleDraweeView draweeView = new SimpleDraweeView(context);
        draweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        draweeView.setLayoutParams(new ViewGroup.MarginLayoutParams(width, height));

        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(context.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
                .setPlaceholderImage(Drawables.sPlaceholderDrawable)
                .build();
        draweeView.setHierarchy(hierarchy);
        return new GatherDataPhotoHolder(draweeView);
    }

    @Override
    public void onBindViewHolder(GatherDataPhotoHolder holder, int position) {
        holder.bindData(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return null == mPhotos ? 0 : mPhotos.size();
    }
}
