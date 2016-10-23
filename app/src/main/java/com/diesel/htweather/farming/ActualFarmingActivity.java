package com.diesel.htweather.farming;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.response.FarmingResJO;
import com.diesel.htweather.util.IntentExtras;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActualFarmingActivity extends BaseActivity {

    @BindView(R.id.farming_title_tv)
    TextView mFarmingTitleTv;

    @BindView(R.id.farming_time_and_source_tv)
    TextView mFarmingTimeAndSourceTv;

    @BindView(R.id.farming_browse_tv)
    TextView mFarmingBrowseTv;

//    @BindView(R.id.news_content_tv)
//    TextView mNewsContentTv;

    @BindView(R.id.header_title_tv)
    TextView mHeaderTitleTv;

    @BindView(R.id.web_view)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_farming);
        ButterKnife.bind(this);

        initWebView();
        FarmingResJO.ObjEntity.WeatherCropCollEntity.TimelyCropsNewsListEntity data
                = IntentExtras.getActualFarmingData(getIntent());
        if (null != data) {
            mFarmingTitleTv.setText(data.title);
            mFarmingTimeAndSourceTv.setText(data.sourceWay + " " + data.sendTime);
            mFarmingBrowseTv.setText(getString(R.string.browse_number, data.counts));
            mWebView.loadData(data.content, "text/html; charset=UTF-8", null);
        }
    }

    protected void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // 优化跳转闪烁的效果（支持3.0以上的系统）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
