package com.diesel.htweather.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.event.AddCropsEvent;
import com.diesel.htweather.event.DeletePlantEvent;
import com.diesel.htweather.event.RecyclerItemEvent;
import com.diesel.htweather.response.BaseResJO;
import com.diesel.htweather.response.PlantCategoryResJO;
import com.diesel.htweather.user.adapter.AddCropsAdapter;
import com.diesel.htweather.user.adapter.AddedPlantAdapter;
import com.diesel.htweather.user.adapter.PlantCategoryAdapter;
import com.diesel.htweather.user.model.PlantCategoryBean;
import com.diesel.htweather.util.FastJsonUtils;
import com.diesel.htweather.util.IntentExtras;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.webapi.PlantWebService;
import com.diesel.htweather.widget.DividerGridItemDecoration;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AddCropsActivity extends BaseActivity {

    @BindView(R.id.plant_category_view)
    RecyclerView mPlantCategoryView;

    @BindView(R.id.plant_name_view)
    RecyclerView mPlantNameView;

    @BindView(R.id.added_plants_view)
    RecyclerView mAddedPlantsView;

    @BindView(R.id.added_plants_layout)
    LinearLayout mAddedPlantsLayout;

    private int mCurrIndex;

    private int mAreaId;

    private PlantCategoryAdapter mAdapter;

    private AddCropsAdapter mCropsAdapter;

    private AddedPlantAdapter mAddedCropsAdapter;

    private List<PlantCategoryBean> mCategories = new ArrayList<>();

    private List<List<PlantCategoryResJO.CategoryEntity.CategoryListEntity>> mCropList = new ArrayList<>();

    private List<PlantCategoryResJO.CategoryEntity.CategoryListEntity> mAddedCrops = new ArrayList<>();

    private RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            if (null != mAddedCropsAdapter) {
                mAddedPlantsLayout.setVisibility(
                        mAddedCropsAdapter.getItemCount() > 0 ? View.VISIBLE : View.GONE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_crops);
        ButterKnife.bind(this);

        mAreaId = IntentExtras.getAreaId(getIntent());
        getPlantCategory();
//        initCategories();

        mAdapter = new PlantCategoryAdapter(mCategories);
        mPlantCategoryView.setLayoutManager(new LinearLayoutManager(this));
        mPlantCategoryView.setHasFixedSize(true);
        mPlantCategoryView.setAdapter(mAdapter);

        mCropsAdapter = new AddCropsAdapter();
        mPlantNameView.setLayoutManager(
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mPlantNameView.addItemDecoration(new DividerGridItemDecoration(mContext));
        mPlantNameView.setHasFixedSize(true);
        mPlantNameView.setAdapter(mCropsAdapter);

        initAddedPlants();
    }

    private void getPlantCategory() {
        showDialog();
        PlantWebService.getInstance().getPlantCategory(mAreaId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getPlantCategory#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show("该区域农作物信息获取失败");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getPlantCategory#onResponse() " + response);
                dismissDialog();
                try {
                    PlantCategoryResJO resJO = FastJsonUtils
                            .getSingleBean(response, PlantCategoryResJO.class);
                    if (null != resJO && resJO.status == 0) {
                        if (null != resJO.data && !resJO.data.isEmpty()) {
                            for (int i = 0; i < resJO.data.size(); i++) {
                                PlantCategoryBean bean = new PlantCategoryBean(resJO.data.get(i).name);
                                if (i == 0) {
                                    bean.isSelectedItem = true;
                                }
                                mCategories.add(bean);
                                mCropList.add(resJO.data.get(i).baseCropList);
                            }
                            mAdapter.notifyDataSetChanged();
                            mCropsAdapter.setData(mCropList.get(0));
                        }
                    } else {
                        ToastUtils.show(resJO.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "getPlantCategory#onResponse() " + e.getMessage());
                }
            }
        });
    }

    private void initAddedPlants() {
        mAddedCropsAdapter = new AddedPlantAdapter(mAddedCrops);
        mAddedCropsAdapter.registerAdapterDataObserver(mObserver);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAddedPlantsView.setLayoutManager(layoutManager);
        mAddedPlantsView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        mAddedPlantsView.setAdapter(mAddedCropsAdapter);
    }

    @OnClick({R.id.back_btn, R.id.save_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.save_btn:
                if (mAddedCrops.isEmpty()) {
                    ToastUtils.show("请选择农作物");
                    return;
                }
                focusCrops();
                break;
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
    protected void onDestroy() {
        super.onDestroy();
        mAddedCropsAdapter.unregisterAdapterDataObserver(mObserver);
    }

    @Subscribe
    public void onRecyclerItemEvent(RecyclerItemEvent event) {
        int position = event.position;
        if (position >= 0) {
            mCurrIndex = position;
            for (int i = 0; i < mCategories.size(); i++) {
                mCategories.get(i).isSelectedItem = i == position;
            }
            mAdapter.notifyDataSetChanged();
            mCropsAdapter.setData(mCropList.get(position));
        }
    }

    @Subscribe
    public void onDeletePlantEvent(DeletePlantEvent event) {
        int position = event.position;
        if (position >= 0) {
            PlantCategoryResJO.CategoryEntity.CategoryListEntity plantBean = mAddedCrops.get(position);
            mAddedCrops.remove(position);
            mAddedCropsAdapter.notifyDataSetChanged();
            for (int i = 0; i < mCropList.size(); i ++) {
                if (mCropList.get(i).contains(plantBean)) {
                    int index = mCropList.get(i).indexOf(plantBean);
                    PlantCategoryResJO.CategoryEntity.CategoryListEntity categoryListEntity
                            = mCropList.get(i).get(index);
                    categoryListEntity.isSelected = false;
                    mCropsAdapter.notifyDataSetChanged();
                    break;
                }
            }
        }
    }

    @Subscribe
    public void onAddCropsEvent(AddCropsEvent event) {
        if (!mAddedCrops.contains(event.mEntity)) {
            mAddedCrops.add(event.mEntity);
            mAddedCropsAdapter.notifyDataSetChanged();
            int index = mCropList.get(mCurrIndex).indexOf(event.mEntity);
            PlantCategoryResJO.CategoryEntity.CategoryListEntity categoryListEntity
                    = mCropList.get(mCurrIndex).get(index);
            categoryListEntity.isSelected = true;
            mCropsAdapter.notifyDataSetChanged();
        }
    }

    private void focusCrops() {
        showDialog();
        StringBuilder sb = new StringBuilder();
        for (PlantCategoryResJO.CategoryEntity.CategoryListEntity entity : mAddedCrops) {
            sb.append(entity.cropcategoryid).append(";");
        }
        String cropsId = sb.toString().substring(0, sb.toString().length()-1);
        PlantWebService.getInstance().focusCrops(String.valueOf(mAreaId), cropsId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "focusCrops#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "focusCrops#onResponse() " + response);
                dismissDialog();
                try {
                    BaseResJO resJo = FastJsonUtils.getSingleBean(response, BaseResJO.class);
                    if (null != resJo && resJo.status == 0) {
                        ToastUtils.show("农作物关注成功");
                        finish();
                    } else {
                        ToastUtils.show(resJo.msg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "focusCrops#onResponse() #Exception# " + e.getMessage());
                }
            }
        });
    }
}
