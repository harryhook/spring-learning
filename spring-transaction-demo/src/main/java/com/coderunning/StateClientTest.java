package com.coderunning;

import com.coderunning.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

import javax.annotation.Resource;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
public class StateClientTest {

    @Resource
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(StateClientTest.class, args);

        //1.创建一笔订单
        String orderSn = orderService.create(1L, 1L);

        //2.执行支付操作
        orderService.pay(orderSn);

        //3.执行发货操作
        orderService.deliver(orderSn);

        //4.执行收货操作
        orderService.receive(orderSn);

        //5.尝试执行下支付操作，会失败
        orderService.pay(orderSn);
    }
}

