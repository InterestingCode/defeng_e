package com.diesel.htweather.db.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *
 *         Time: 2016/9/26
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public final class FocusArea {

    public static List<FocusArea> getFocusArea() {
        List<FocusArea> focusAreas = new ArrayList<>();

        Cursor cursor = null;
        SQLiteDatabase db = null;

        return focusAreas;
    }

}
