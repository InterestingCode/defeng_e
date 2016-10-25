package com.diesel.htweather.response;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/10/25
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class SearchAreaResJO extends BaseResJO {

    public int count;

    public List<AreaEntity> data;

    public static class AreaEntity {

        public int arId;

        public int ctId;

        public String arName;

        public int isHot;

        public int isOpen;
    }
}
