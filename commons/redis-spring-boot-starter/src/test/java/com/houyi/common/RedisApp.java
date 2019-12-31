/**    
 * @Title: RedisApp.java
 * @Package com.houyi.common
 * @Description: 模拟启动redis客户端 
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月9日 下午3:23:17
 * @version updateDesc: time author desc
 */
package com.houyi.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RestController;


/**  
 * @ClassName: RedisApp  
 * @Description: 模拟启动redis客户端 
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月9日 下午3:23:17  
 *    
 */
@EnableRedisHttpSession
@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class RedisApp {
    
    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class, args);
    }
    
}
