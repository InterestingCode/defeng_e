package com.diesel.htweather.farming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.farming.adapter.MessageAdapter;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
public class MessageFragment extends BaseFragment {

    private static final String MESSAGE_TYPE = "message_type";
    public static final int TYPE_SYSTEM_MESSAGE = 9001;
    public static final int TYPE_NOTICE_MESSAGE = 9002;

    private XRecyclerView mRecyclerView;

    public static MessageFragment newInstance(int messageType) {
        Bundle bundle = new Bundle();
        bundle.putInt(MESSAGE_TYPE, messageType);
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mRecyclerView = new XRecyclerView(getContext());
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST,
                        R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new MessageAdapter());
        return mRecyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        int msgType = bundle.getInt(MESSAGE_TYPE);
        if (msgType == TYPE_SYSTEM_MESSAGE) {

        } else if (msgType == TYPE_NOTICE_MESSAGE) {

        }
    }
}
