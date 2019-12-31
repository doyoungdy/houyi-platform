/**    
 * @Title: RedisConts.java
 * @Package com.houyi.common.redis.test
 * @Description: Redis测试用例相关常量
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月11日 上午10:28:45
 * @version updateDesc: time author desc
 */
package com.houyi.common.redis.test;

/**  
 * @ClassName: RedisConts  
 * @Description: Redis测试用例相关常量
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月11日 上午10:28:45  
 *    
 */
public interface RedisTestConts {
    
    /**
     * redis测试用例存放在redis中的key的前缀
     */
    static final String REDIS_VAR_PREFIX = "redis_test_";
    

    /**
     * redis测试用例存放在redis中的key的前缀
     */
    static final int REDIS_VAR_TIME_OUT = 5;
}
