package com.diesel.htweather.farming.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.diesel.htweather.R;
import com.diesel.htweather.farming.model.TruthDataBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/8/28
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class TruthDataSettingHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.truth_data_check_box)
    CheckBox mTruthDataCheckBox;

    @BindView(R.id.truth_data_type_tv)
    TextView mTruthDataTypeTv;

    @BindView(R.id.highest_value_et)
    EditText mHighestValueEt;

    @BindView(R.id.lowest_value_et)
    EditText mLowestValueEt;

    public TruthDataSettingHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(TruthDataBean bean) {
        mTruthDataTypeTv.setText(bean.name);
        mHighestValueEt.setText(bean.highestValue+"");
        mLowestValueEt.setText(bean.lowestValue+"");
    }
}
