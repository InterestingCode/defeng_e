package com.diesel.htweather;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diesel.htweather.base.BaseFragment;
import com.diesel.htweather.widget.LineChartSurfaceView;
import com.diesel.htweather.widget.Trend24HourView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Comments：个人中心
 *
 * @author Diesel
 *         Time: 2016/8/13
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class UserInfoFragment extends BaseFragment {

    @BindView(R.id.chart_view)
    LineChartSurfaceView mChartView;

    @BindView(R.id.truth_data_view)
    Trend24HourView mTruthDataView;

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<List<Integer>> mLinesList = getData();
        mChartView.setBloodPressure(mLinesList.get(0));
//        mChartView.getHolder().setFormat(PixelFormat.TRANSLUCENT);

        Integer[] temp = new Integer[]{32, 33, 33, 35, 35, 36, 35, 37, 39, 38, 34, 33};
        List<Integer> tempratures = Arrays.asList(temp);
        mTruthDataView.setTemperatures(tempratures);
    }

    private List<List<Integer>> getData() {
        List<List<Integer>> mLineList = new ArrayList<List<Integer>>();
        List<Integer> line1 = new ArrayList<Integer>();
        List<Integer> line2 = new ArrayList<Integer>();
        for (int i = 0; i < 200; i++) {
            line1.add(550 + (int) (Math.random() * 200));
            line2.add(550 - (int) (Math.random() * 200));
        }
        mLineList.add(line1);
        mLineList.add(line2);
        return mLineList;
    }
}
