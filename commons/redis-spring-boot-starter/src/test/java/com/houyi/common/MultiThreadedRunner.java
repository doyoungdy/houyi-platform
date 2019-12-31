/**    
 * @Title: MultiThreadedRunner.java
 * @Package com.houyi.common
 * @Description: TODO(用一句话描述该文件做什么)
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月18日 下午2:51:07
 * @version updateDesc: time author desc
 */
package com.houyi.common;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**  
 * @ClassName: MultiThreadedRunner  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author AB049844 AB049844taozhishen@ab-insurance.com
 * @date 2019年4月18日 下午2:51:07  
 *    
 */
public class MultiThreadedRunner extends SpringJUnit4ClassRunner {

    private AtomicInteger numThreads;

    public static int maxThreads = 10;
    
    /**  
     * <p>Title: </p>  
     * <p>Description: </p>  
     * @param clazz
     * @throws InitializationError  
     */
    public MultiThreadedRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
        numThreads = new AtomicInteger(0);
    }

    @Override
    protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
        while (numThreads.get() > maxThreads) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Interrupted: " + method.getName());
                e.printStackTrace();
                return; // The user may have interrupted us; this won't happen normally
            }
        }
        numThreads.incrementAndGet();
        // 用线程执行父类runChild（method, notifier）
        new Thread(new Test(method, notifier)).start();
    }

    // childrenInvoker() call runChild(Object, RunNotifier) on each object returned by getChildren()
    // evaluate() run the action, 调用父类BlockJUnit4ClassRunner的evaluate()
    @Override
    protected Statement childrenInvoker(final RunNotifier notifier) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                MultiThreadedRunner.super.childrenInvoker(notifier).evaluate();
                // wait for all child threads (tests) to complete
                while (numThreads.get() > 0) {
                    Thread.sleep(1000);
                }
            }
        };
    }

    class Test implements Runnable {
        private final FrameworkMethod method;
        private final RunNotifier notifier;

        public Test(final FrameworkMethod method, final RunNotifier notifier) {
            this.method = method;
            this.notifier = notifier;
        }

        @Override
        public void run() {
            System.err.println(method.getName());
            MultiThreadedRunner.super.runChild(method, notifier);
            numThreads.decrementAndGet();
        }
    }
}
