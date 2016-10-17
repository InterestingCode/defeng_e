package com.diesel.htweather.depthservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.depthservice.adapter.OnlineAdvisoryAdapter;
import com.diesel.htweather.response.OnlineAdvisoryResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by zhoujiangsen
 * 在线咨询页
 */

public class OnlineMeFragment extends BaseFragment {

    @BindView(R.id.farming_policy_recycler_view)
    XRecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.online_fragment, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST,
                R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initDatas();
        return view;
    }

    private void initDatas() {
        //showDialog();
        DepthWebService.getInstance().getOnlineConsultationMessages("2", new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "OnlineMeMsg#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "OnlineMeMsg#onResponse() " + response);
                dismissDialog();
                try {

                    OnlineAdvisoryResJO resJO = FastJsonUtils.getSingleBean(response, OnlineAdvisoryResJO.class);
                    if (null != resJO && !resJO.getData().isEmpty() && resJO.status == 0) {
                        mRecyclerView.setAdapter(new OnlineAdvisoryAdapter(resJO.getData()));
                    } else {
                        ToastUtils.show(resJO.msg);
                    }

                } catch (Exception e) {
                    Log.e(TAG, "OnlineMeMsg#onResponse() " + e.getMessage());
                }


            }
        });
    }

}
