/**    
 * @Title: RedisApiListTest.java
 * @Package com.houyi.common.redis.test.api
 * @Description: TODO(用一句话描述该文件做什么)
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月18日 下午4:20:07
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.houyi.common.RedisApp;
import com.houyi.common.redis.template.RedisRepository;

/**  
 * @ClassName: RedisApiListTest  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月18日 下午4:20:07  
 *    
 */
@SpringBootTest(classes = RedisApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisApiListTest {
    @Autowired
    private RedisRepository redisRepository;
    
    @Test
    public void redisListAdd()
    {
        redisRepository.leftPush("abcList", "aaa");
        String leftPop = (String) redisRepository.leftPop("abcList");
        
        Assert.assertEquals("the value of redis_test_abc_str is wrong.", "aaa", leftPop);
        
        redisRepository.in("abcList", "bbb");
        
        Long length = redisRepository.length("abcList");
        Assert.assertEquals("the value of redis_test_abc_str is wrong.", 1, length.intValue());
        
        String rightPop = (String) redisRepository.rightPop("abcList");
        
        Assert.assertEquals("the value of redis_test_abc_str is wrong.", "bbb", rightPop);
    }
}
