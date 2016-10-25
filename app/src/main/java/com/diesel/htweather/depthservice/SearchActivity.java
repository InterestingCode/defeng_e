package com.diesel.htweather.depthservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.depthservice.adapter.SearchAdapter;
import com.diesel.htweather.event.RecyclerItemEvent;
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
import butterknife.OnClick;
import okhttp3.Call;


public class SearchActivity extends BaseActivity implements TextWatcher {

    @BindView(R.id.editText)
    EditText mSearchBtn;

    @BindView(R.id.search_recycler_view)
    XRecyclerView mRecyclerView;

    SearchAdapter mAdapter = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_search);
        ButterKnife.bind(this);
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSearchBtn.addTextChangedListener(this);
    }

    private void searchDatas(String faTitle) {
        showDialog();
        DepthWebService.getInstance().getOnlineConsultationMessages(faTitle, "", "1", new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "searchDatas#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "searchDatas#onResponse() " + response);
                dismissDialog();
                try {

                    OnlineAdvisoryResJO resJO = FastJsonUtils.getSingleBean(response, OnlineAdvisoryResJO.class);
                    if (null != resJO && !resJO.getData().isEmpty() && resJO.status == 0) {
                        mAdapter = new SearchAdapter(mActivity, resJO.getData());
                        mRecyclerView.setAdapter(mAdapter);
                    } else {
                        ToastUtils.show(resJO.msg);
                    }

                } catch (Exception e) {
                    Log.e(TAG, "searchDatas#onResponse() " + e.getMessage());
                }


            }
        });
    }


    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }

    @Subscribe
    public void onRecyclerItemEvent(RecyclerItemEvent event) {
        int position = event.position;
        if (mAdapter != null) {
            String id = mAdapter.getAdvisoryBeanList().get(position).getContentId();
            Intent intent = new Intent(this, OnlineAdvisoryDetailsActivity.class);
            intent.putExtra("contentId", id);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        final String faTitle = mSearchBtn.getText().toString();
        if (!TextUtils.isEmpty(faTitle)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    searchDatas(faTitle);
                }
            }, 1000);
        }
    }
}
