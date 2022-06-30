package com.coderunning.state;

import com.coderunning.OrderRepository;
import com.coderunning.context.OrderStateContext;
import com.coderunning.domain.Order;
import com.coderunning.enums.OrderStateEnum;
import com.coderunning.factory.OrderStateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WaitDeliverOrderState extends AbstractOrderState {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Enum type() {
        return OrderStateEnum.WAIT_DELIVER;
    }

    /**
     * 发货
     * @param context
     * @param order
     */
    public void deliver(OrderStateContext context, Order order) {
        OrderStateEnum newState = OrderStateEnum.WAIT_RECEIVE;
        //操作发货，并更新数据库
        order.setStatus(newState.getCode());
        orderRepository.update(order);
        //更新上下文状态
        context.setOrderState(OrderStateFactory.getState(newState));
        System.out.println("订单号："+ order.getOrderSn() + " 发货成功！状态流转至：" + newState.getDesc());
    }

    /**
     * 申请售后
     * @param context
     * @param order
     */
    public void applyRefund(OrderStateContext context, Order order) {
        OrderStateEnum newState = OrderStateEnum.REFUNDING;
        //操作发货，并更新数据库
        order.setStatus(newState.getCode());
        orderRepository.update(order);
        //更新上下文状态
        context.setOrderState(OrderStateFactory.getState(newState));
        System.out.println("订单号："+ order.getOrderSn() + " 申请售后！状态流转至：" + newState.getDesc());
    }
}

