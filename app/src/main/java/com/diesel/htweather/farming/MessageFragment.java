package com.diesel.htweather.farming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.farming.adapter.MessageAdapter;
import com.diesel.htweather.response.MessageResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.UserWebService;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

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
public class MessageFragment extends BaseFragment implements XRecyclerView.LoadingListener {

    private static final String MESSAGE_TYPE = "message_type";
    public static final int TYPE_SYSTEM_MESSAGE = 9001;
    public static final int TYPE_NOTICE_MESSAGE = 9002;

    private int mMsgType;

    private int mPage = 1;

    private XRecyclerView mRecyclerView;

    private MessageAdapter mAdapter;

    private List<MessageResJO.MessageEntity> mList = new ArrayList<>();

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
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST,
                        R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MessageAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        return mRecyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        mMsgType = bundle.getInt(MESSAGE_TYPE);
        getMessages();
    }

    private void getMessages() {
        showDialog();
        if (mMsgType == TYPE_SYSTEM_MESSAGE) {
            UserWebService.getInstance().getSystemMessage(mPage, new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e(TAG, "getSystemMessages#onError() " + e.getMessage());
                    dismissDialog();
                    ToastUtils.show(getString(R.string.tips_request_failure));
                }

                @Override
                public void onResponse(String response, int id) {
                    Log.d(TAG, "getSystemMessages#onResponse() " + response);
                    dismissDialog();
                    try {
                        MessageResJO resJO = FastJsonUtils.getSingleBean(response, MessageResJO.class);
                        if (null != resJO && resJO.status == 0 && null != resJO.data
                                && !resJO.data.isEmpty()) {
                            if (mPage == 1) {
                                mList.clear();
                            }
                            mList.addAll(resJO.data);
                            mAdapter.notifyDataSetChanged();
                            mPage ++;
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "getSystemMessages#onResponse() " + e.getMessage());
                    }
                }
            });
        } else if (mMsgType == TYPE_NOTICE_MESSAGE) {
            UserWebService.getInstance().getNotifyMessage(mPage, new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e(TAG, "getNotifyMessages#onError() " + e.getMessage());
                    dismissDialog();
                    ToastUtils.show(getString(R.string.tips_request_failure));
                }

                @Override
                public void onResponse(String response, int id) {
                    Log.d(TAG, "getNotifyMessages#onResponse() " + response);
                    dismissDialog();
                    try {
                        MessageResJO resJO = FastJsonUtils.getSingleBean(response, MessageResJO.class);
                        if (null != resJO && resJO.status == 0 && null != resJO.data
                                && !resJO.data.isEmpty()) {
                            if (mPage == 1) {
                                mList.clear();
                            }
                            mList.addAll(resJO.data);
                            mAdapter.notifyDataSetChanged();
                            mPage ++;
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "getNotifyMessages#onResponse() " + e.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        getMessages();
    }
}
