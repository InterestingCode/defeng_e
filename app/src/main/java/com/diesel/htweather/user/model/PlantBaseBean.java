package com.diesel.htweather.user.model;

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
public class PlantBaseBean {

    public static final int SHOW_STATUS_LIST = 101;

    public static final int SHOW_STATUS_DELETE = 102;

    public int showStatus = SHOW_STATUS_LIST;

    public void changeToList() {
        showStatus = SHOW_STATUS_LIST;
    }

    public void changeToDelete() {
        showStatus = SHOW_STATUS_DELETE;
    }

    public void changeToListStatus(boolean showList) {
        showStatus = showList ? SHOW_STATUS_LIST : SHOW_STATUS_DELETE;
    }

}
