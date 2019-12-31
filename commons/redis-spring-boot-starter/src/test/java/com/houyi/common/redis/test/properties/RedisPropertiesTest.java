/**    
 * @Title: RedisProperties.java
 * @Package com.houyi.common.redis.test.properties
 * @Description: Redis属性获取接口测试 
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月10日 上午9:49:23
 * @version updateDesc: time author desc
 */
package com.houyi.common.redis.test.properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.houyi.common.RedisApp;

/**  
 * @ClassName: RedisProperties  
 * @Description: Redis属性获取接口测试 
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月10日 上午9:49:23  
 *    
 */
@SpringBootTest(classes = RedisApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RedisPropertiesTest {

    @Autowired
    private RedisProperties redisProperties;
    
    @Test
    public void redisIPPropertiesTest() {
        Assert.assertEquals("Redis's ip is wrong.", "10.7.91.65", redisProperties.getHost());
    }
    
    @Test
    public void redisPortPropertiesTest() {
        Assert.assertEquals("Redis's port is wrong.", 6379, redisProperties.getPort());
    }
    
    @Test
    public void redisTimeoutPropertiesTest() {
        Assert.assertEquals("Redis's timeout is wrong.", 5l, redisProperties.getTimeout().getSeconds());
        
    }
    
    /**
     * @Description: 最大连接数量
     */
    @Test
    public void redisPoolMaxActiveTest() {
        Pool redisPool = redisProperties.getJedis().getPool();
        Assert.assertEquals("The max_Active of Redis pool set is wrong.", 1000, redisPool.getMaxActive());
    }
    
    /**
     * @Description: 最大等待时间
     */
    @Test
    public void redisPoolMaxWaitTest() {
        Pool redisPool = redisProperties.getJedis().getPool();
        Assert.assertEquals("The max_wait of Redis pool set is wrong.", 1, redisPool.getMaxWait().getSeconds());
    }

    /**
     * @Description: 最大空闲数量
     */
    @Test
    public void redisPoolMinIdleTest() {
        Pool redisPool = redisProperties.getJedis().getPool();
        Assert.assertEquals("The min_Idle of Redis pool set is wrong.", 50, redisPool.getMinIdle());
    }
    
    /**
     * @Description: 最小空闲数量
     */
    @Test
    public void redisPoolMaxIdleTest() {
        Pool redisPool = redisProperties.getJedis().getPool();
        Assert.assertEquals("The max_Idle of Redis pool set is wrong.", 50, redisPool.getMaxIdle());
    }
    
    
}
