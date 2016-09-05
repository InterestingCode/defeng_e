package com.diesel.htweather.user.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

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
public class GatherDataPhotoHolder extends RecyclerView.ViewHolder {

    SimpleDraweeView mDraweeView;

    public GatherDataPhotoHolder(View itemView) {
        super(itemView);
        mDraweeView = (SimpleDraweeView) itemView;
    }

    public void bindData(String url) {
        mDraweeView.setImageURI(url);
    }
}
