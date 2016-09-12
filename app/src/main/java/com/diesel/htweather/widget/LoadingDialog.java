package com.diesel.htweather.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.diesel.htweather.R;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/12
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class LoadingDialog extends Dialog {

    private static final int DEFAULT_WIDTH = 150; // 默认宽度

    private static final int DEFAULT_HEIGHT = 80;// 默认高度

    private Context mContext;

    private UberProgressView mProgressView;

    private TextView mLoadingTxtTv;

    public LoadingDialog(Context context) {
        this(context, R.style.LoadingDialogStyle);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);

        mContext = context;
        setContentView(R.layout.loading_dialog_layout);
        mProgressView = (UberProgressView) findViewById(R.id.progress_bar);
        mLoadingTxtTv = (TextView) findViewById(R.id.progress_dialog_tv);

//        Window window = getWindow();
//        WindowManager.LayoutParams params = window.getAttributes();
//        float density = getDensity(context);
//        params.width = (int) (DEFAULT_WIDTH * density);
//        params.height = (int) (DEFAULT_HEIGHT * density);
//        params.gravity = Gravity.CENTER;
//        window.setAttributes(params);
    }

    private float getDensity(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.density;
    }

    @Override
    public void show() {
        if (null != mContext && !((Activity) mContext).isFinishing()) {
            super.show();
        }
    }
}
