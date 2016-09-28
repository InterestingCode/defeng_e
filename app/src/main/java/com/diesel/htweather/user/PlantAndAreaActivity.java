package com.diesel.htweather.user;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.event.ShowAddPlantDialogEvent;
import com.diesel.htweather.user.adapter.PlantAndAreaAdapter;
import com.diesel.htweather.user.model.AddPlantBean;
import com.diesel.htweather.user.model.PlantAndAreaBean;
import com.diesel.htweather.user.model.PlantBaseBean;
import com.diesel.htweather.util.DialogUtils;
import com.diesel.htweather.util.ToastUtils;
import com.diesel.htweather.util.ViewUtils;
import com.diesel.htweather.webapi.PlantWebService;
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

public class PlantAndAreaActivity extends BaseActivity {

    @BindView(R.id.edit_btn)
    ImageView mEditBtn;

    @BindView(R.id.save_btn)
    TextView mSaveBtn;

    @BindView(R.id.plant_and_area_recycler_view)
    RecyclerView mRecyclerView;

    private List<PlantBaseBean> mPlants = new ArrayList<>();

    private PlantAndAreaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_and_area);
        ButterKnife.bind(this);

        mAdapter = new PlantAndAreaAdapter(mPlants);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST,
                        R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setAdapter(mAdapter);

        getPlants();
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

    private void getPlants() {
        PlantAndAreaBean plant1 = new PlantAndAreaBean();
        plant1.plantName = "水稻";
        plant1.plantArea = "234";

        PlantAndAreaBean plant2 = new PlantAndAreaBean();
        plant2.plantName = "玉米";
        plant2.plantArea = "78";

        PlantAndAreaBean plant3 = new PlantAndAreaBean();
        plant3.plantName = "小麦";
        plant3.plantArea = "148";

        mPlants.add(plant1);
        mPlants.add(plant2);
        mPlants.add(plant3);
        mPlants.add(new AddPlantBean());

        mAdapter.notifyDataSetChanged();

        showDialog();
        PlantWebService.getInstance().getPlantsAndArea(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "getPlants#onError() " + e.getMessage());
                dismissDialog();
                ToastUtils.show(getString(R.string.tips_request_failure));
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "getPlants#onResponse() " + response);
                dismissDialog();
            }
        });
    }

    @Subscribe
    public void onShowAddPlantDialogEvent(ShowAddPlantDialogEvent event) {
        DialogUtils.showAddPlantDialog(this,
                new DialogUtils.DialogOnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                            String inputContent) {
                        String[] s = inputContent.split("&");
                        if (s.length == 2) {
                            PlantAndAreaBean plant = new PlantAndAreaBean();
                            plant.plantName = s[0];
                            plant.plantArea = s[1];
                            mPlants.add(mPlants.size() - 1, plant);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
