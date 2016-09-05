package com.diesel.htweather.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.event.RecyclerItemEvent;
import com.diesel.htweather.user.adapter.PlantCategoryAdapter;
import com.diesel.htweather.user.model.PlantBaseBean;
import com.diesel.htweather.user.model.PlantBean;
import com.diesel.htweather.user.model.PlantCategoryBean;
import com.heaven7.android.dragflowlayout.DragAdapter;
import com.heaven7.android.dragflowlayout.DragFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddWatchPlantActivity extends BaseActivity {

    @BindView(R.id.plant_category_view)
    RecyclerView mPlantCategoryView;

    @BindView(R.id.plant_name_view)
    DragFlowLayout mPlantNameView;

    @BindView(R.id.added_plants_view)
    RecyclerView mAddedPlantsView;

    @BindView(R.id.added_plants_layout)
    LinearLayout mAddedPlantsLayout;

    private PlantCategoryAdapter mAdapter;

    private List<PlantCategoryBean> mCategories = new ArrayList<>();

    private List<List<PlantBean>> mPlants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_watch_plant);
        ButterKnife.bind(this);

        initCategories();
        initDragView();
        switchPlants(0);
    }

    private void initCategories() {
        PlantCategoryBean bean = new PlantCategoryBean("大田作物");
        bean.isSelectedItem = true;
        mCategories.add(bean);
        List<PlantBean> plant1 = new ArrayList<>();
        plant1.add(new PlantBean("水稻"));
        plant1.add(new PlantBean("冬油菜"));
        plant1.add(new PlantBean("冬小麦"));
        plant1.add(new PlantBean("豌豆"));
        plant1.add(new PlantBean("春玉米"));
        plant1.add(new PlantBean("夏大豆"));
        mPlants.add(plant1);

        mCategories.add(new PlantCategoryBean("蔬菜"));
        List<PlantBean> plant2 = new ArrayList<>();
        plant2.add(new PlantBean("大白菜"));
        plant2.add(new PlantBean("抱子甘蓝"));
        plant2.add(new PlantBean("茼蒿"));
        plant2.add(new PlantBean("芜荽（大叶香菜、小叶香菜）"));
        plant2.add(new PlantBean("紫苏"));
        plant2.add(new PlantBean("豌豆芽"));
        mPlants.add(plant2);

        mCategories.add(new PlantCategoryBean("果蔬瓜果"));
        List<PlantBean> plant3 = new ArrayList<>();
        plant3.add(new PlantBean("西瓜"));
        plant3.add(new PlantBean("苹果"));
//        plant3.add(new PlantBean("苹果（红富士，红星，国光，秦冠，黄元帅）"));
        plant3.add(new PlantBean("无花果"));
        plant3.add(new PlantBean("奇异果"));
        plant3.add(new PlantBean("甘蔗"));
        plant3.add(new PlantBean("榴莲"));
        plant3.add(new PlantBean("柿子"));
        plant3.add(new PlantBean("荔枝"));
        mPlants.add(plant3);

        mCategories.add(new PlantCategoryBean("经济作物"));
        List<PlantBean> plant4 = new ArrayList<>();
        plant4.add(new PlantBean("棉花"));
        plant4.add(new PlantBean("大豆"));
        plant4.add(new PlantBean("花生"));
        plant4.add(new PlantBean("油菜"));
        plant4.add(new PlantBean("甜菜"));
        plant4.add(new PlantBean("茶树"));
        mPlants.add(plant4);

        mCategories.add(new PlantCategoryBean("特色作物及科普"));
        List<PlantBean> plant5 = new ArrayList<>();
        plant5.add(new PlantBean("水稻"));
        plant5.add(new PlantBean("冬油菜"));
        plant5.add(new PlantBean("冬小麦"));
        plant5.add(new PlantBean("豌豆"));
        plant5.add(new PlantBean("春玉米"));
        plant5.add(new PlantBean("夏大豆"));
        mPlants.add(plant5);

        mAdapter = new PlantCategoryAdapter(mCategories);
        mPlantCategoryView.setLayoutManager(new LinearLayoutManager(this));
        mPlantCategoryView.setHasFixedSize(true);
        mPlantCategoryView.setAdapter(mAdapter);
    }

    private void initDragView() {
        mPlantNameView.setOnItemClickListener(new DragFlowLayout.OnItemClickListener() {
            @Override
            public boolean performClick(DragFlowLayout dragFlowLayout, View child,
                    MotionEvent event, int dragState) {
                return false;
            }
        });
        mPlantNameView.setDragAdapter(new DragAdapter<PlantBean>() {
            @Override
            public int getItemLayoutId() {
                return R.layout.list_item_plant_name;
            }

            @Override
            public void onBindData(View itemView, int dragState, PlantBean data) {
                itemView.setTag(data);
                TextView textView = (TextView) itemView.findViewById(R.id.plant_name_tv);
                textView.setText(data.plantName);
            }

            @NonNull
            @Override
            public PlantBean getData(View itemView) {
                return (PlantBean) itemView.getTag();
            }
        });
    }

    private void switchPlants(int position) {
        mPlantNameView.getDragItemManager().clearItems();
        mPlantNameView.getDragItemManager().addItems(mPlants.get(position));
    }

    @OnClick({R.id.back_btn, R.id.save_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.save_btn:
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

    @Subscribe
    public void onRecyclerItemEvent(RecyclerItemEvent event) {
        int position = event.position;
        if (position >= 0) {
            for (int i = 0; i < mCategories.size(); i++) {
                mCategories.get(i).isSelectedItem = i == position;
            }
            mAdapter.notifyDataSetChanged();
            switchPlants(position);
        }
    }
}
