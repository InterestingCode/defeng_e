package com.diesel.htweather.event;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/11
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class ProblemPhotoEvent {

    public static final int ACTION_ADD_PHOTO = 1;
    public static final int ACTION_DEL_PHOTO = 2;

    public int action;

    public int position;

    public ProblemPhotoEvent(int action, int position) {
        this.action = action;
        this.position = position;
    }
}
