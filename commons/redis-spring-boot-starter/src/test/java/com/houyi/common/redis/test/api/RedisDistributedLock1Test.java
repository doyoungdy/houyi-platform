/**    
 * @Title: RedisDistributedLock1Test.java
 * @Package com.houyi.common.redis.test.api
 * @Description: Redis分布式锁测试 
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月18日 下午2:32:27
 * @version updateDesc: time author desc
 */
package com.houyi.common.redis.test.api;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.houyi.common.MultiThreadedRunner;
import com.houyi.common.RedisApp;
import com.houyi.common.redis.lock.RedisDistributedLock;
import com.houyi.common.redis.template.RedisRepository;
import com.houyi.common.redis.test.RedisTestConts;
import com.houyi.common.redis.test.utils.DateTimeUtils;

/**  
 * @ClassName: RedisDistributedLock1Test  
 * @Description: Redis分布式锁测试 
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月18日 下午2:32:27  
 *    
 */
@SpringBootTest(classes = RedisApp.class)
@RunWith(MultiThreadedRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisDistributedLock1Test {
    
    @Autowired
    RedisDistributedLock redisDistributedLock;
    
    @Autowired
    private RedisRepository redisRepository;
    
    @Test
    public void redisGet() throws InterruptedException {
        System.out.println(DateTimeUtils.getNowdateTime() + ":test redisGet begin.");
        String redisValue = (String) redisRepository.get(RedisTestConts.REDIS_VAR_PREFIX + "abc_str");

        Assert.assertEquals("the value of redis_test_abc_str is wrong.", "abc", redisValue);
        
        Thread.sleep(3000);
        System.out.println(DateTimeUtils.getNowdateTime() + ":test redisGet1 getValue begin.");
        redisValue = (String) redisRepository.get(RedisTestConts.REDIS_VAR_PREFIX + "abc_str");
        System.out.println(DateTimeUtils.getNowdateTime() + ":test redisGet1 getValue end.");
        Assert.assertEquals("the value of redis_test_abc_str is wrong.", "abc", redisValue);
        
        Thread.sleep(RedisTestConts.REDIS_VAR_TIME_OUT * 1000);
        System.out.println(DateTimeUtils.getNowdateTime() + ":test redisGet2 getValue begin.");
        redisValue = (String) redisRepository.get(RedisTestConts.REDIS_VAR_PREFIX + "abc_str");
        System.out.println(DateTimeUtils.getNowdateTime() + ":test redisGet2 getValue end.");
        Assert.assertEquals("the value of redis_test_abc_str is wrong.", "abc", redisValue);
        
        System.out.println(DateTimeUtils.getNowdateTime() + ":test redisGet end.");
    }
}
