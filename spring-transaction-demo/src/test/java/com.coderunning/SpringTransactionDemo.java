package com.coderunning;

import com.coderunning.service.BuyGoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
public class SpringTransactionDemo {

    @Test
    public void init(){
        System.out.println("====");
    }


    @Test
    public void testSales() {

        String config = "applicationContext.xml";

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);

        BuyGoodsService buyGoodsService = applicationContext.getBean("buyGoodsService", BuyGoodsService.class);

        buyGoodsService.buy(11, 10);


    }
}
