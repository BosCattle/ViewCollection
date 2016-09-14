package com.kevin.library.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Class: TimeUtils </br>
 * Description: 时间工具类 </br>
 * Creator: kevin </br>
 * Email: jiangtao103cp@gmail.com </br>
 * Date: 16/9/10 下午1:49</br>
 * Update: 16/9/10 下午1:49 </br>
 **/

public class TimeUtils {

    /**
     *
     * @param d {@link Double}
     * @return 保留两位小数
     */
    public static String getDoubleDecimal(double d) {
        return String.format(Locale.CHINA, "%.2f", d);
    }

    /**
     *
     * @param strDate {@link Long}
     * @return 格式化时间
     */
    public static String convertDate(long strDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);
        try {
            return simpleDateFormat.format(new Date(strDate * 1000));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
