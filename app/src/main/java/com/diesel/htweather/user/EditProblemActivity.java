package com.diesel.htweather.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.base.BaseActivity;
import com.diesel.htweather.event.ProblemPhotoEvent;
import com.diesel.htweather.user.adapter.EditProblemAdapter;
import com.diesel.htweather.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/11
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class EditProblemActivity extends BaseActivity {

    @BindView(R.id.problem_et)
    EditText mProblemEt;

    @BindView(R.id.problem_photos_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.location_city_tv)
    TextView mLocationTv;

    private EditProblemAdapter mAdapter;

    private List<String> mPhotos = new ArrayList<>();

    private RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        mAdapter.registerAdapterDataObserver(mObserver);
    }

    @OnClick({R.id.back_btn, R.id.send_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.send_btn:
                String problem = mProblemEt.getText().toString();
                if (TextUtils.isEmpty(problem)) {
                    ToastUtils.show(getString(R.string.tips_input_problem));
                    return;
                }
                break;
        }
    }

    @Subscribe
    public void onProblemPhotoEvent(ProblemPhotoEvent event) {
        if (event.action == ProblemPhotoEvent.ACTION_ADD_PHOTO) {

        } else if (event.action == ProblemPhotoEvent.ACTION_DEL_PHOTO) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.unregisterAdapterDataObserver(mObserver);
        EventBus.getDefault().unregister(this);
    }
}
