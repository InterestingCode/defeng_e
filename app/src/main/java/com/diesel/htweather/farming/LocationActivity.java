package com.diesel.htweather.farming;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.base.BaseBean;
import com.diesel.htweather.farming.adapter.LocationAdapter;
import com.diesel.htweather.farming.model.HotAreaBean;
import com.diesel.htweather.farming.model.LocationBean;
import com.diesel.htweather.farming.model.RecommendAreaBean;
import com.diesel.htweather.response.LocationResJO;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.SharedPreferencesUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.AreaWebService;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class LocationActivity extends BaseActivity {

    @BindView(R.id.area_recycler_view)
    RecyclerView mRecyclerView;

    private List<BaseBean> mAreaList = new ArrayList<>();

    private LocationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        LocationBean locationBean = new LocationBean();
        locationBean.location = SharedPreferencesUtils.getInstance(mContext).getLocation();
        mAreaList.add(locationBean);

        mAdapter = new LocationAdapter(mAreaList);

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
                        List<LocationResJO.AreaListEntity.RecommendAreaListEntity> recommendAreaList
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
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getHotAndRecommendArea#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }
}
