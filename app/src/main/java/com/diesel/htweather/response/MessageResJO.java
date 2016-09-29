package com.diesel.htweather.response;

import java.util.List;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/25
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class MessageResJO extends BaseResJO {

    /**
     * count : 1
     * data : [{"content":"而威尔儿","channelName":"","umId":1,"channelId":0,"addTime":"2016.09.25 16:28","isRead":1}]
     */

    public int count;

    /**
     * content : 而威尔儿
     * channelName :
     * umId : 1
     * channelId : 0
     * addTime : 2016.09.25 16:28
     * isRead : 1
     */

    public List<MessageEntity> data;

    public static class MessageEntity {

        public String content;

        public String channelName;

        public int umId;

        public int channelId;

        public String addTime;

        public int isRead;
    }
}
