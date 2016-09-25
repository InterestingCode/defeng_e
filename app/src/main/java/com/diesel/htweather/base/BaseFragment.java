package com.diesel.htweather.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.diesel.htweather.widget.LoadingDialog;

/**
 * Comments：
 *
 * @author wangj
 *         Time: 2016/8/13
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class BaseFragment extends Fragment {

    public String TAG = this.getClass().getSimpleName();

    private LoadingDialog mDialog;

    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    protected void showDialog() {
        if (null == mDialog) {
            mDialog = new LoadingDialog(mActivity);
        }
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    protected void dismissDialog() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dismissDialog();
    }
}
