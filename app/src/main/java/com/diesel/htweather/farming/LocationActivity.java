package com.diesel.htweather.farming;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.event.AddHotRcmdAreaEvent;
import com.diesel.htweather.event.RefreshFarmingDataEvent;
import com.diesel.htweather.farming.adapter.LocationAdapter;
import com.diesel.htweather.farming.model.HotAreaBean;
import com.diesel.htweather.farming.model.LocationBean;
import com.diesel.htweather.farming.model.RecommendAreaBean;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.response.LocationResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.SharedPreferencesUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.AreaWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class LocationActivity extends BaseActivity {

    @BindView(R.id.area_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.search_city_et)
    EditText mSearchAreaEt;

    private List<BaseBean> mAreaList = new ArrayList<>();

    private LocationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        LocationBean locationBean = new LocationBean();
        locationBean.location = SharedPreferencesUtils.getInstance(mContext).getLocation();
        mAreaList.add(locationBean);

        mAdapter = new LocationAdapter(mAreaList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);

        mSearchAreaEt.addTextChangedListener(mTextWatcher);

        getHotAndRecommendArea();
    }

    private void getHotAndRecommendArea() {
        showDialog();
        AreaWebService.getInstance().getHotAndRecommendArea(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getHotAndRecommendArea#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getHotAndRecommendArea#onResponse() " + response);
                dismissDialog();
                try {
                    LocationResJO resJO = FastJsonUtils
                            .getSingleBean(response, LocationResJO.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    if (resJO.status == 0 && null != resJO.obj) {
                        List<LocationResJO.AreaListEntity.HotAreaListEntity> hotAreaList
                                = resJO.obj.hotAreaList;
                        List<LocationResJO.AreaListEntity.HotAreaListEntity> recommendAreaList
                                = resJO.obj.recommendAreaList;
                        if (null != hotAreaList && !hotAreaList.isEmpty()) {
                            HotAreaBean hotAreaBean = new HotAreaBean();
                            hotAreaBean.hotAreaList = hotAreaList;
                            mAreaList.add(hotAreaBean);
                        }
                        if (null != recommendAreaList && !recommendAreaList.isEmpty()) {
                            RecommendAreaBean recommendAreaBean = new RecommendAreaBean();
                            recommendAreaBean.recommendAreaList = recommendAreaList;
                            mAreaList.add(recommendAreaBean);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getHotAndRecommendArea#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }

    @Subscribe
    public void onAddHotRcmdAreaEvent(AddHotRcmdAreaEvent event) {
        addFocusArea(event.entity.arId);
    }

    private void addFocusArea(int countryCode) {
        showDialog();
        AreaWebService.getInstance().focusArea(String.valueOf(countryCode), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "addFocusArea#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "addFocusArea#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJO = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    if (resJO.status != 0) {
                        ToastUtils.show(resJO.msg);
                    } else {
                        ToastUtils.show("城市添加成功");
                        EventBus.getDefault().post(new RefreshFarmingDataEvent());
                        finish();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "addFocusArea#onResponse() " + e.getMessage());
                }
            }
        });
    }

    private void searchAreaByName(String area) {
        AreaWebService.getInstance().getAreaByName(area, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "addFocusArea#onError() " + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "addFocusArea#onResponse() " + response);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mSearchAreaEt.removeTextChangedListener(mTextWatcher);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
