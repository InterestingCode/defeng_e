package com.diesel.htweather.depthservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.depthservice.adapter.OnlineAllMsgAdapter;
import com.diesel.htweather.depthservice.model.OnlineAdvisoryBean;
import com.diesel.htweather.event.AllMsgItemEvent;
import com.diesel.htweather.event.ThumbsUpEvent;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.response.OnlineAdvisoryResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by zhoujiangsen
 * 在线咨询页
 */

public class OnlineAllFragment extends BaseFragment {

    @BindView(R.id.farming_policy_recycler_view)
    XRecyclerView mRecyclerView;

    OnlineAllMsgAdapter mAdapter = null;

    int mPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.online_fragment, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST,
                R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initDatas();
        return view;
    }

    private void initDatas() {
        showDialog();
        DepthWebService.getInstance().getOnlineConsultationMessages("", "1", new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "OnlineAllMsg#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "OnlineAllMsg#onResponse() " + response);
                dismissDialog();
                try {

                    OnlineAdvisoryResJO resJO = FastJsonUtils.getSingleBean(response, OnlineAdvisoryResJO.class);
                    if (null != resJO && !resJO.getData().isEmpty() && resJO.status == 0) {
                        mAdapter = new OnlineAllMsgAdapter(mActivity, resJO.getData());
                        mRecyclerView.setAdapter(mAdapter);
                    } else {
                        ToastUtils.show(resJO.msg);
                    }

                } catch (Exception e) {
                    Log.e(TAG, "OnlineAllMsg#onResponse() " + e.getMessage());
                }


            }
        });
    }


    @Subscribe
    public void onAllMsgItemEvent(AllMsgItemEvent event) {
        int position = event.position;
        String id = mAdapter.getAdvisoryBeanList().get(position).getContentId();
        Intent intent = new Intent(getActivity(), OnlineAdvisoryDetailsActivity.class);
        intent.putExtra("contentId", id);
        startActivity(intent);
    }

    @Subscribe
    public void onThumbsUpEvent(ThumbsUpEvent event) {
        mPosition = event.position;
        String id = mAdapter.getAdvisoryBeanList().get(mPosition).getContentId();
        thumbsUpComments(id);
    }


    private void thumbsUpComments(String contentId) {
        showDialog();
        DepthWebService.getInstance().thumbsUpComments(contentId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "thumbsUpComments#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "thumbsUpComments#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null != resJO && resJO.status == 0) {
                        OnlineAdvisoryBean bean = mAdapter.getAdvisoryBeanList().get(mPosition);
                        int countUps = Integer.valueOf(bean.getUps());
                        bean.setUps(String.valueOf(countUps + 1));
                        mAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "thumbsUpComments#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
