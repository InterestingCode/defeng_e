package com.diesel.htweather.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.diesel.htweather.R;

/**
 * Holds static drawables used in the sample app.
 *
 * <p> Using static set of drawables allows us to easily determine state of image request
 * by simply looking what kind of drawable is passed to image view.
 */
public class Drawables {

    public static void init(final Context context) {
        if (sPlaceholderDrawable == null) {
            sPlaceholderDrawable = ContextCompat.getDrawable(context, R.color.placeholder_color);
        }
        if (sErrorDrawable == null) {
            sErrorDrawable = ContextCompat.getDrawable(context, R.color.placeholder_color);
        }
    }

    public static Drawable sPlaceholderDrawable;

    public static Drawable sErrorDrawable;

    private Drawables() {
    }
}
