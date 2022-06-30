package com.coderunning.service;

import com.coderunning.OrderRepository;
import com.coderunning.domain.Order;
import com.coderunning.enums.OrderStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    //模拟获取订单号，这里简单做，一般都是全局唯一的分布式id
    private static Long orderSn = 1L;
    public static String generateOrderSn() {
        return String.valueOf(orderSn++);
    }

    /**
     * 创建订单
     * @param buyerId
     * @param skuId
     * @return
     */
    public String create(Long buyerId, Long skuId) {
        Order order = new Order();
        order.setOrderSn(generateOrderSn());
        order.setBuyerId(buyerId);
        order.setSkuId(skuId);
        order.setStatus(OrderStateEnum.WAIT_PAY.getCode());
        orderRepository.insert(order);
        return order.getOrderSn();
    }

    /**
     * 发起支付
     * 订单发货
     * 订单收货
     * 与订单退款写法类似，暂时忽略...
     */

    /**
     * 售后申请
     * @param orderSn
     */
    void refund(String orderSn) {
        Order order = orderRepository.get(orderSn);
        //判断是否是待收货状态
        if (!Objects.equals(order.getStatus(), OrderStateEnum.WAIT_DELIVER.getCode())) {
            throw new RuntimeException("该状态下不支持该操作");
        }
        OrderStateEnum newState = OrderStateEnum.REFUNDING;
        //操作收货，并更新数据库
        order.setStatus(newState.getCode());
        orderRepository.update(order);
    }
}
