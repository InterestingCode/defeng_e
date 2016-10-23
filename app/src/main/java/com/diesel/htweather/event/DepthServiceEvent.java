package com.diesel.htweather.event;

import com.diesel.htweather.depthservice.model.FacilitiesBean;

/**
 * Created by zhoujs on 2016/10/23.
 */

public class DepthServiceEvent {

    public final FacilitiesBean facilitiesBean;

    public DepthServiceEvent(FacilitiesBean facilitiesBean) {
        this.facilitiesBean = facilitiesBean;
    }
}
