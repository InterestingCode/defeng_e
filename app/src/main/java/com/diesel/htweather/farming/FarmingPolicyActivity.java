package com.diesel.htweather.farming;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.event.RecyclerItemEvent;
import com.diesel.htweather.farming.adapter.FarmingPolicyAdapter;
import com.diesel.htweather.farming.adapter.MessageAdapter;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.IntentExtras;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FarmingPolicyActivity extends BaseActivity {

    public static final int TYPE_FARMING_INFO = 9001;

    public static final int TYPE_FARMING_POLICY = 9002;

    @BindView(R.id.farming_policy_recycler_view)
    XRecyclerView mRecyclerView;

    @BindView(R.id.header_title_tv)
    TextView mHeaderTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farming_policy);
        ButterKnife.bind(this);

        int farmingType = IntentExtras.getFarmingType(getIntent());
        if (farmingType == TYPE_FARMING_INFO) {
            mHeaderTitleTv.setText(R.string.farming_info);
        } else if (farmingType == TYPE_FARMING_POLICY) {
            mHeaderTitleTv.setText(R.string.farming_policy);
        }

        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST,
                        R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new FarmingPolicyAdapter());
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }

    @Subscribe
    public void onRecyclerItemEvent(RecyclerItemEvent event) {
        int position = event.position;
        ActivityNav.getInstance().startFarmingDetailsActivity(this);
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

}
