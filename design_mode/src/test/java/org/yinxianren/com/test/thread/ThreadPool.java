package org.yinxianren.com.test.thread;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPool {

    ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            60 * 5, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1), // 使用有界队列，避免OOM
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @Test
    public void test01() throws ExecutionException, InterruptedException {
        System.out.println("begin");

        for(int j = 0; j<=25;j++) {
            Future<Object> future = executorService.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    int i = 100;
                    int sum = 0;
                    while (i <= 1000) {
                        sum += i;
                        Thread.sleep(100);
                        i++;
                    }
                    return sum;
                }
            });

        }

//        //该方法是阻塞执行的
//        Object result = future.get();
//        System.out.println("result==>"+result);
//        System.out.println("end");
    }

}
