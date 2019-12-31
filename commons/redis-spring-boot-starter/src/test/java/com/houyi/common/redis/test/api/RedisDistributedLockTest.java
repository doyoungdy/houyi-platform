/**    
 * @Title: RedisDistributedLockTest.java
 * @Package com.houyi.common.redis.test.api
 * @Description: Redis分布式锁测试 
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月18日 下午2:18:02
 * @version updateDesc: time author desc
 */
package com.houyi.common.redis.test.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.houyi.common.RedisApp;
import com.houyi.common.redis.lock.RedisDistributedLock;
import com.houyi.common.redis.template.RedisRepository;
import com.houyi.common.redis.test.RedisTestConts;
import com.houyi.common.redis.test.utils.DateTimeUtils;

/**  
 * @ClassName: RedisDistributedLockTest  
 * @Description: Redis分布式锁测试 
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月18日 下午2:18:02  
 *    
 */
@SpringBootTest(classes = RedisApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisDistributedLockTest {

    @Autowired
    RedisDistributedLock redisDistributedLock;
    
    @Autowired
    private RedisRepository redisRepository;
    
    @Test
    public void redisSet() throws InterruptedException {
        System.out.println(DateTimeUtils.getNowdateTime() + ":test redisSet begin.");
        Thread.sleep(1000);
        // 拿锁
        System.out.println(DateTimeUtils.getNowdateTime() + ":test redisSet get lock begin.");
        boolean lockResult = redisDistributedLock.lock(RedisTestConts.REDIS_VAR_PREFIX + "abc_str");
        System.out.println(DateTimeUtils.getNowdateTime() + ":test redisSet get lock end.lockResult:" + lockResult);
        
        Thread.sleep(RedisTestConts.REDIS_VAR_TIME_OUT * 1000);
        
        redisRepository.set(RedisTestConts.REDIS_VAR_PREFIX + "abc_str", "abcd");
        String redisValue = (String) redisRepository.get(RedisTestConts.REDIS_VAR_PREFIX + "abc_str");
        Assert.assertEquals("the value of redis_test_abc_str is wrong.", "abcd", redisValue);

        // 释放锁
        System.out.println(DateTimeUtils.getNowdateTime() + ":test redisSet release lock begin.");
        redisDistributedLock.releaseLock(RedisTestConts.REDIS_VAR_PREFIX + "abc_str");
        System.out.println(DateTimeUtils.getNowdateTime() + ":test redisSet release lock end.");

        System.out.println(DateTimeUtils.getNowdateTime() + ":test redisSet end.");
    }
    
    /**
     * @Description: 测试前预先埋数据
     */
    @Before
    public void proRedisDataPut() {
        System.out.println("set redis_test_ value begin.");
        // 设置redis_test_abc_str="abc"
        redisRepository.set(RedisTestConts.REDIS_VAR_PREFIX + "abc_str", "abc");
        System.out.println("set redis_test_ value end.");
    }

/*    *//**
     * @Description: 测试完成后清理数据
     *//*
    @After
    public void proRedisDataCls() {
        System.out.println("clear redis_test_ value begin.");

        redisRepository.del(RedisTestConts.REDIS_VAR_PREFIX + "abc_str");

        System.out.println("clear redis_test_ value end.");
    }*/
}
