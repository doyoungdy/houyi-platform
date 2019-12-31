/**    
 * @Title: DateTimeUtils.java
 * @Package com.houyi.common.redis.test.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月18日 下午2:57:46
 * @version updateDesc: time author desc
 */
package com.houyi.common.redis.test.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**  
 * @ClassName: DateTimeUtils  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月18日 下午2:57:46  
 *    
 */
public class DateTimeUtils {

    public static String defaultFormat = "yyyy-MM-dd HH:mm:ss";
    
    public static String getNowdateTime()
    {
        return getStrNow(defaultFormat);
    }
    
    public static String getStrNow(String pattern){
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }
}
