package com.diesel.htweather.depthservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.depthservice.adapter.FacilitiesOneAdapter;
import com.diesel.htweather.depthservice.adapter.FacilitiesTwoAdapter;
import com.diesel.htweather.response.FacilitiesResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.DepthWebService;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


public class FacilitiesActivity extends BaseActivity {

    @BindView(R.id.myDeepServiceListView)
    XRecyclerView myDeepServiceListView;

    @BindView(R.id.recommendServiceListView)
    XRecyclerView recommendServiceListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);
        ButterKnife.bind(this);
        myDeepServiceListView.setPullRefreshEnabled(false);
        myDeepServiceListView.setLoadingMoreEnabled(false);
        myDeepServiceListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.recycler_view_1px_divider_shape));
        myDeepServiceListView.setLayoutManager(new LinearLayoutManager(this));

        recommendServiceListView.setPullRefreshEnabled(false);
        recommendServiceListView.setLoadingMoreEnabled(false);
        recommendServiceListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.recycler_view_1px_divider_shape));
        recommendServiceListView.setLayoutManager(new LinearLayoutManager(this));
        getFacilitiesAgricultureList();
    }

    private void getFacilitiesAgricultureList() {
        showDialog();
        DepthWebService.getInstance().getFacilitiesAgricultureList(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getFacilitiesAgricultureList#onError() " + e.getMessage());
                ToastUtils.show(getString(R.string.tips_request_failure));
                dismissDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getFacilitiesAgricultureList#onResponse() " + response);
                dismissDialog();
                try {
                    FacilitiesResJO resJO = FastJsonUtils.getSingleBean(response, FacilitiesResJO.class);
                    if (null != resJO && resJO.status == 0) {
                        myDeepServiceListView.setAdapter(new FacilitiesOneAdapter(FacilitiesActivity.this, resJO.getObj().getOwnerSetList()));
                        recommendServiceListView.setAdapter(new FacilitiesTwoAdapter(FacilitiesActivity.this, resJO.getObj().getRecommendSetList()));
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getFacilitiesAgricultureList#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }


    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
