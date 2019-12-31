/**    
 * @Title: RedisTest.java
 * @Package com.houyi.common.redis.test
 * @Description: Redis测试总入口
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月10日 下午3:25:28
 * @version updateDesc: time author desc
 */
package com.houyi.common.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.houyi.common.RedisApp;



/**  
 * @ClassName: RedisTest  
 * @Description: Redis测试总入口
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月10日 下午3:25:28  
 *    
 */

@SpringBootTest(classes = RedisApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RedisTest {

    @Test
    public void redisTest() {
        System.out.println("Hello Redis.");
    }
}
