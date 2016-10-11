package com.diesel.htweather.user.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diesel.htweather.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
public class PhotoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.problem_photo_view)
    SimpleDraweeView mProblemPhotoView;

    public PhotoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.delete_photo_btn)
    public void onClick() {
    }
}
