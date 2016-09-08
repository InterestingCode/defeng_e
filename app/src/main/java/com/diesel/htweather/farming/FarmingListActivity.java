package com.diesel.htweather.farming;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.event.RecyclerItemEvent;
import com.diesel.htweather.farming.adapter.FarmingPolicyAdapter;
import com.diesel.htweather.farming.adapter.MessageAdapter;
import com.diesel.htweather.farming.model.FarmingInfoPolicyBean;
import com.diesel.htweather.util.ActivityNav;
import com.diesel.htweather.util.IntentExtras;
import com.diesel.htweather.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FarmingListActivity extends BaseActivity {

    public static final int TYPE_ACTUAL_FARMING = 9000;

    public static final int TYPE_FARMING_INFO = 9001;

    public static final int TYPE_FARMING_POLICY = 9002;

    @BindView(R.id.farming_policy_recycler_view)
    XRecyclerView mRecyclerView;

    @BindView(R.id.header_title_tv)
    TextView mHeaderTitleTv;

    @BindView(R.id.farming_policy_banner_iv)
    ImageView mBannerIv;

    private List<FarmingInfoPolicyBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farming_policy);
        ButterKnife.bind(this);

        int farmingType = IntentExtras.getFarmingType(getIntent());
        if (farmingType == TYPE_FARMING_INFO) {
            mHeaderTitleTv.setText(R.string.farming_info);
            mBannerIv.setImageResource(R.drawable.banner_farming_info);

            for (int i = 0; i < 6; i ++) {
                FarmingInfoPolicyBean bean = new FarmingInfoPolicyBean();
                switch (i) {
                    case 0:
                        bean.coverResId = R.drawable.test_info_1;
                        bean.title = "生产指南|要想蔬菜种得好 德哥献计少不了";
                        break;
                    case 1:
                        bean.coverResId = R.drawable.test_info_2;
                        bean.title = "生产指南|要想冬草莓唱得好，缓苗期的管理不可少";
                        break;
                    case 2:
                        bean.coverResId = R.drawable.test_info_3;
                        bean.title = "气象情报|难熬的八月已过去 总结分析这段回忆";
                        break;
                    case 3:
                        bean.coverResId = R.drawable.test_info_4;
                        bean.title = "气象科普|空中有朵雨做的云，雨的这些小秘密你知道嘛？";
                        break;
                    case 4:
                        bean.coverResId = R.drawable.test_info_5;
                        bean.title = "收货专题|高温天影响四川水稻生产 政府保障措施送上保护伞";
                        break;
                    case 5:
                        bean.coverResId = R.drawable.test_info_6;
                        bean.title = "气象情报|“秋老虎”肆虐，四川8月份中下旬高温灾害总结";
                        break;
                }
                mList.add(bean);
            }

        } else if (farmingType == TYPE_FARMING_POLICY) {
            mHeaderTitleTv.setText(R.string.farming_policy);
            mBannerIv.setImageResource(R.drawable.banner_depth_service);

            for (int i = 0; i < 7; i ++) {
                FarmingInfoPolicyBean bean = new FarmingInfoPolicyBean();
                switch (i) {
                    case 0:
                        bean.coverResId = R.drawable.test_policy_1;
                        bean.title = "[政策信息]国务院：关于实施支持农业转移人口...";
                        break;
                    case 1:
                        bean.coverResId = R.drawable.test_policy_2;
                        bean.title = "[政策信息]新一轮支农政策：农业保险";
                        break;
                    case 2:
                        bean.coverResId = R.drawable.test_policy_3;
                        bean.title = "[补贴聚焦]专家解析农业补贴“三合一”";
                        break;
                    case 3:
                        bean.coverResId = R.drawable.test_policy_4;
                        bean.title = "[权威聚焦]你可知道农村搞土地确权的好处？";
                        break;
                    case 4:
                        bean.coverResId = R.drawable.test_policy_5;
                        bean.title = "你知道农村土地怎么流转吗？";
                        break;
                    case 5:
                        bean.coverResId = R.drawable.test_policy_6;
                        bean.title = "农村土地经营权流转交易市场运行规范";
                        break;
                    case 6:
                        bean.coverResId = R.drawable.test_policy_7;
                        bean.title = "好消息，玉米补贴每亩170元左右";
                        break;
                }
                mList.add(bean);
            }
        }

        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST,
                        R.drawable.recycler_view_1px_divider_shape));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new FarmingPolicyAdapter(mList));
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
