package com.diesel.htweather.event;

import com.diesel.htweather.response.FocusAreaResJO;

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
public class DeleteFocusAreaEvent {

    public int mPosition;

    public FocusAreaResJO.FocusAreaEntity mBean;

    public DeleteFocusAreaEvent(int position, FocusAreaResJO.FocusAreaEntity bean) {
        this.mPosition = position;
        this.mBean = bean;
    }
}
