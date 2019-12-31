/**    
 * @Title: RedisApiMapTest.java
 * @Package com.houyi.common.redis.test.api
 * @Description: Redis存放(Map) 
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月16日 下午2:26:43
 * @version updateDesc: time author desc
 */
package com.houyi.common.redis.test.api;

import java.util.Map;

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

/**  
 * @ClassName: RedisApiMapTest  
 * @Description: Redis存放(Map) 
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月16日 下午2:26:43  
 *    
 */
@SpringBootTest(classes = RedisApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisApiMapTest {
    
    @Autowired
    private RedisRepository redisRepository;
    
    @Test
    public void redisGetHSet() throws InterruptedException {
        Map<String, Object> hashMap = redisRepository.getHashValues("myMap");

        Assert.assertEquals("redis map test is wrong.", 2, hashMap.size());

        Integer hashValue = (Integer) redisRepository.getHashValue("myMap", "abc");
        Assert.assertEquals("redis map test is wrong.", 123, hashValue.intValue());
    }

    /**
     * @Description: 测试前预先埋数据
     */
    @Before
    public void proRedisDataPut() {
        System.out.println("set redis_test_ value begin.");
        
        redisRepository.putHashValue("myMap", "abc", 123);
        redisRepository.putHashValue("myMap", "abc1", 1234);

        System.out.println("set redis_test_ value end.");
    }

    /**
     * @Description: 测试完成后清理数据
     */
    @After
    public void proRedisDataCls() {
        System.out.println("clear redis_test_ value begin.");

        redisRepository.delHashValues("myMap", "abc", "abc1");
        redisRepository.delHashValues("myMap", "abc1");

        System.out.println("clear redis_test_ value end.");
    }
    
}
