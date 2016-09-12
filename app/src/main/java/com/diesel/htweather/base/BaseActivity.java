package com.diesel.htweather.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.diesel.htweather.widget.LoadingDialog;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/8/9
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class BaseActivity extends AppCompatActivity {

    protected String TAG = this.getClass().getSimpleName();

    private LoadingDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showDialog() {
        if (null == mDialog) {
            mDialog = new LoadingDialog(this);
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
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }
}
