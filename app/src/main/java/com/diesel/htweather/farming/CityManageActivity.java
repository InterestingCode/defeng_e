package com.diesel.htweather.farming;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.base.DFApplication;
import com.diesel.htweather.db.DBHelperFocusArea;
import com.diesel.htweather.db.model.FocusArea;
import com.diesel.htweather.event.AddFocusAreaEvent;
import com.diesel.htweather.event.DeleteFocusAreaEvent;
import com.diesel.htweather.event.RefreshFarmingDataEvent;
import com.diesel.htweather.farming.adapter.AreaManageAdapter;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.response.FocusAreaResJO;
import com.diesel.htweather.user.model.AddPlantBean;
import com.diesel.htweather.user.model.PlantBaseBean;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.util.ViewUtils;
import com.diesel.htweather.webapi.AreaWebService;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.diesel.pickerview.OptionsPickerView;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 城市管理
 */
public class CityManageActivity extends BaseActivity {

    @BindView(R.id.edit_btn)
    ImageView mEditBtn;

    @BindView(R.id.save_btn)
    TextView mSaveBtn;

    @BindView(R.id.focus_area_recycler_view)
    RecyclerView mRecyclerView;

    private OptionsPickerView mCityPickerView;

    private AreaManageAdapter mAdapter;

    private List<PlantBaseBean> mPlants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manage);
        ButterKnife.bind(this);

//        List<FocusArea> focusArea = DBHelperFocusArea.getFocusArea();
//        mPlants.addAll(focusArea);
        mPlants.add(new AddPlantBean());
        mAdapter = new AreaManageAdapter(mPlants);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST,
                        R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setAdapter(mAdapter);

        initPickerView();

        getFocusArea();
    }

    @OnClick({R.id.back_btn, R.id.edit_btn, R.id.save_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.edit_btn:
                ViewUtils.visible(mSaveBtn);
                ViewUtils.gone(mEditBtn);
                changeToListStatus(false);
                break;
            case R.id.save_btn:
                ViewUtils.gone(mSaveBtn);
                ViewUtils.visible(mEditBtn);
                changeToListStatus(true);
                break;
        }
    }

    private void changeToListStatus(boolean showList) {
        for (int i = 0; i < mPlants.size(); i++) {
            mPlants.get(i).changeToListStatus(showList);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initPickerView() {
        mCityPickerView = new OptionsPickerView(this);
        mCityPickerView.setTitle(getString(R.string.choose_area));
        mCityPickerView
                .setPicker(DFApplication.provinces, DFApplication.cities, DFApplication.countries,
                        true);
        mCityPickerView.setCyclic(false, false, false);
        mCityPickerView.setSelectOptions(0, 0, 0);

        mCityPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
//                FocusArea area = new FocusArea();
//                area.provinceCode = DFApplication.provinces.get(options1).pvId+"";
//                area.provinceName = DFApplication.provinces.get(options1).pvName;
//                area.cityCode = DFApplication.cities.get(options1).get(option2).ctId+"";
//                area.cityName = DFApplication.cities.get(options1).get(option2).ctName;
//                area.countryCode = DFApplication.countries.get(options1).get(option2).get(options3).arId+"";
//                area.countryName = DFApplication.countries.get(options1).get(option2).get(options3).arName;
                addFocusArea(DFApplication.countries.get(options1).get(option2).get(options3).arId);
            }
        });
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

    @Subscribe
    public void onAddFocusAreaEvent(AddFocusAreaEvent event) {
        mCityPickerView.show();
    }

    @Subscribe
    public void onDeleteFocusAreaEvent(DeleteFocusAreaEvent event) {
        cancelFocusArea(event.mBean.uaId);
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
//                        ToastUtils.show("城市添加成功");
//                        mPlants.add(mPlants.size() == 1 ? 0 : mPlants.size() - 1, area);
//                        mAdapter.notifyDataSetChanged();
//                        DBHelperFocusArea.insertArea(area);
                        EventBus.getDefault().post(new RefreshFarmingDataEvent());

                        getFocusArea();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "addFocusArea#onResponse() " + e.getMessage());
                }
            }
        });
    }

    private void cancelFocusArea(int uaid) {
        showDialog();
        AreaWebService.getInstance().cancelFocusArea(String.valueOf(uaid), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "cancelFocusArea#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "cancelFocusArea#onResponse() " + response);
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
//                        ToastUtils.show("取消关注城市成功");
//                        mPlants.remove(area);
//                        mAdapter.notifyDataSetChanged();
//                        DBHelperFocusArea.deleteArea(area);
                        EventBus.getDefault().post(new RefreshFarmingDataEvent());

                        getFocusArea();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "cancelFocusArea#onResponse() " + e.getMessage());
                }
            }
        });
    }

    private void getFocusArea() {
        showDialog();
        AreaWebService.getInstance().getFocusArea(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getFocusArea#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getFocusArea#onResponse() " + response);
                dismissDialog();
                try {
                    FocusAreaResJO resJO = FastJsonUtils.getSingleBean(response, FocusAreaResJO.class);
                    if (null == resJO) {
                        ToastUtils.show(getString(R.string.tips_request_failure));
                        return;
                    }
                    if (resJO.status == 0) {
                        ViewUtils.visible(mEditBtn);
                        ViewUtils.gone(mSaveBtn);

                        mPlants.clear();
                        if (null != resJO.data && !resJO.data.isEmpty()) {
                            mPlants.addAll(0, resJO.data);
                        }
                        mPlants.add(new AddPlantBean());
                        mAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getFocusArea#onResponse() " + e.getMessage());
                }
            }
        });
    }

}
