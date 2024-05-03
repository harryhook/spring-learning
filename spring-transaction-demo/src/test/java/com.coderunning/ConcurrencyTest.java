package com.coderunning;

import com.coderunning.service.BuyGoodsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertTrue;

/**
 * @author haowei.chen
 * @since 2024/4/29 19:32
 */
@SpringBootTest
public class ConcurrencyTest {

    @Autowired
    private BuyGoodsService dataService;

    @Test
    public void testConcurrency() throws InterruptedException {
        final int numberOfThreads = 10; // 设定并发数量
        CountDownLatch doneLatch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            int data = i;
            new Thread(() -> {
                try {
                    dataService.buy(1, 1); // 执行业务逻辑
                } finally {
                    System.out.println("Thread " + data + " finished: " + doneLatch.getCount());
                    doneLatch.countDown(); // 完成一个任务，计数器减一
                }
            }).start();
        }

        doneLatch.await(); // 等待所有任务完成

        assertTrue(doneLatch.getCount() == 0); // 验证所有任务已完成
    }
}

