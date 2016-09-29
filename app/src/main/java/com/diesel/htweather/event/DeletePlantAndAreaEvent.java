package com.diesel.htweather.event;

import com.diesel.htweather.user.model.PlantAndAreaBean;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/4
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class DeletePlantAndAreaEvent {

    public int mPosition;
    public PlantAndAreaBean mBean;

    public DeletePlantAndAreaEvent(int position, PlantAndAreaBean bean) {
        this.mPosition = position;
        this.mBean = bean;
    }
}
