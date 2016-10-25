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
import com.diesel.htweather.event.RefreshDataEvent;
import com.diesel.htweather.event.ThumbsUpEvent;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.response.OnlineAdvisoryResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by zhoujiangsen
 * 在线咨询页
 */

public class OnlineAllFragment extends BaseFragment implements XRecyclerView.LoadingListener {

    @BindView(R.id.farming_policy_recycler_view)
    XRecyclerView mRecyclerView;

    OnlineAllMsgAdapter mAdapter = null;

    int mPosition;

    int page = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.online_fragment, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST,
                R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        initDatas("1");
        return view;
    }

    private void initDatas(String pageNum) {
        page = Integer.valueOf(pageNum);
        showDialog();
        DepthWebService.getInstance().getOnlineConsultationMessages("", pageNum, "1", new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "OnlineAllMsg#onError() " + e.getMessage());
                        dismissDialog();
                        mRecyclerView.refreshComplete();
                        mRecyclerView.loadMoreComplete();
                        ToastUtils.show(getString(R.string.tips_request_failure));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "OnlineAllMsg#onResponse() " + response);
                        mRecyclerView.refreshComplete();
                        mRecyclerView.loadMoreComplete();
                        dismissDialog();
                        try {
                            OnlineAdvisoryResJO resJO = FastJsonUtils.getSingleBean(response, OnlineAdvisoryResJO.class);
                            if (null != resJO && resJO.status == 0) {
                                if (page == 1) {
                                    mAdapter = new OnlineAllMsgAdapter(mActivity, resJO.getData());
                                    mRecyclerView.setAdapter(mAdapter);
                                } else if (page > 1) {
                                    List<OnlineAdvisoryBean> listData = mAdapter.getAdvisoryBeanList();
                                    listData.addAll(resJO.getData());
                                    mAdapter.update(listData);
                                }
                            } else {
                                ToastUtils.show(resJO.msg);
                            }

                        } catch (Exception e) {
                            Log.e(TAG, "OnlineAllMsg#onResponse() " + e.getMessage());
                        }


                    }
                }

        );
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

    @Subscribe
    public void onRefreshDataEvent(RefreshDataEvent event) {
        initDatas("1");
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
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRefresh() {
        initDatas("1");
    }

    @Override
    public void onLoadMore() {
        if (page > 0) {
            initDatas(String.valueOf(page + 1));
        }
    }
}
