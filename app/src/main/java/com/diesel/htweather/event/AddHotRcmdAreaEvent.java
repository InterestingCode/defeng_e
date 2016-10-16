package com.diesel.htweather.event;

import com.diesel.htweather.response.LocationResJO;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/16
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class AddHotRcmdAreaEvent {

    public LocationResJO.AreaListEntity.HotAreaListEntity entity;

    public AddHotRcmdAreaEvent(LocationResJO.AreaListEntity.HotAreaListEntity entity) {
        this.entity = entity;
    }
}
