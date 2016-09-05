package com.diesel.htweather.user.model;

import com.heaven7.android.dragflowlayout.IDraggable;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/5
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class PlantBean implements IDraggable {

    public String plantName;

    public PlantBean(String name) {
        plantName = name;
    }

    @Override
    public boolean isDraggable() {
        return false;
    }
}
