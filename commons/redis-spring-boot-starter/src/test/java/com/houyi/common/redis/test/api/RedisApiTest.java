/**    
 * @Title: RedisApiStringTest.java
 * @Package com.houyi.common.redis.test.api
 * @Description: Redis存放
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月11日 下午2:32:28
 * @version updateDesc: time author desc
 */
package com.houyi.common.redis.test.api;

import org.junit.After;
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
import com.houyi.common.redis.template.RedisRepository;
import com.houyi.common.redis.test.RedisTestConts;


/**
 * @ClassName: RedisApiStringTest
 * @Description: Redis存放
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月11日 下午2:32:28
 * 
 */
@SpringBootTest(classes = RedisApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisApiTest {

    @Autowired
    private RedisRepository redisRepository;

    @Test
    public void redisGet() {
        String redisValue = (String) redisRepository.get(RedisTestConts.REDIS_VAR_PREFIX + "abc_str");

        Assert.assertEquals("the value of redis_test_abc_str is wrong.", "abc", redisValue);
    }

    @Test
    public void redisSet() {
        redisRepository.set(RedisTestConts.REDIS_VAR_PREFIX + "abc_str", "abcd");
        String redisValue = (String) redisRepository.get(RedisTestConts.REDIS_VAR_PREFIX + "abc_str");
        Assert.assertEquals("the value of redis_test_abc_str is wrong.", "abcd", redisValue);
    }

    @Test
    public void redisGetAfterExpire() throws InterruptedException {
        Thread.sleep(RedisTestConts.REDIS_VAR_TIME_OUT * 1000);

        String redisValue = (String) redisRepository.get(RedisTestConts.REDIS_VAR_PREFIX + "abc_str");

        Assert.assertEquals("the value of redis_test_abc_str is wrong.", null, redisValue);
    }

    /**
     * @Description: 测试前预先埋数据
     */
    @Before
    public void proRedisDataPut() {
        System.out.println("set redis_test_ value begin.");
        // 设置redis_test_abc_str="abc"，超时时间10s
        redisRepository.setExpire(RedisTestConts.REDIS_VAR_PREFIX + "abc_str", "abc", RedisTestConts.REDIS_VAR_TIME_OUT);

        System.out.println("set redis_test_ value end.");
    }

    /**
     * @Description: 测试完成后清理数据
     */
    @After
    public void proRedisDataCls() {
        System.out.println("clear redis_test_ value begin.");

        redisRepository.del(RedisTestConts.REDIS_VAR_PREFIX + "abc_str");

        System.out.println("clear redis_test_ value end.");
    }
}
