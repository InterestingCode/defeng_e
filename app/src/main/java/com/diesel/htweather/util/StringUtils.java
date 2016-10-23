package com.diesel.htweather.util;

import android.text.TextUtils;

import com.diesel.htweather.constant.Consts;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Comments：字符串相关的工具类
 *
 * @author Diesel
 *
 *         Time: 2016/8/9
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class StringUtils {

    public static String inputStreamToString(InputStream in) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int count = -1;
        try {
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
            }
            return new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean hasContain(List<String> list, String content) {
        if (list != null && list.size() > 0) {
            for (String l : list) {
                if (!TextUtils.isEmpty(l) && l.equalsIgnoreCase(content)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 正则验证
     *
     * @param context 要验证的内容
     * @param regExp  验证规则
     * @return 通过true，否则false
     */
    public static boolean regExpVerify(String context, String regExp) {
        Pattern p = Pattern.compile(regExp);
        Matcher matcher = p.matcher(context);
        return matcher.find();
    }

    public static boolean mobileVerify(String telephone) {
        return !TextUtils.isEmpty(telephone) && telephone.length() == 11;
    }
}
