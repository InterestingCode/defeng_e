package com.diesel.htweather.util;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/10/24.
 */

public class PhoneUtils {

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }
}
