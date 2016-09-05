package com.diesel.htweather.user.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.user.adapter.GatherDataPhotoAdapter;
import com.diesel.htweather.user.model.GatherDataBean;
import com.diesel.htweather.util.ImageUrlUtils;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/4
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class GatherDataHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_avatar_view)
    SimpleDraweeView mUserAvatarView;

    @BindView(R.id.user_name_tv)
    TextView mUserNameTv;

    @BindView(R.id.user_rank_tv)
    TextView mUserRankTv;

    @BindView(R.id.user_addr_tv)
    TextView mUserAddrTv;

    @BindView(R.id.publish_time_tv)
    TextView mPublishTimeTv;

    @BindView(R.id.gather_data_content_tv)
    TextView mGatherDataContentTv;

    @BindView(R.id.gather_data_photos_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.browse_number_tv)
    TextView mBrowseNumberTv;

    @BindView(R.id.comment_number_tv)
    TextView mCommentNumberTv;

    GatherDataPhotoAdapter mAdapter;

    List<String> mPhotos = new ArrayList<>();

    public GatherDataHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        // TODO 测试代码
        mPhotos.add(ImageUrlUtils.getImageUrls()[0]);
        mPhotos.add(ImageUrlUtils.getImageUrls()[1]);
        mPhotos.add(ImageUrlUtils.getImageUrls()[2]);

        Context context = itemView.getContext();
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL_LIST,
                        R.drawable.recycler_view_5px_divider_shape));
        mAdapter = new GatherDataPhotoAdapter(mPhotos);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void bindData(GatherDataBean bean) {

    }

    @OnClick(R.id.comment_btn)
    public void onClick() {
    }
}
