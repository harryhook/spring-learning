package com.coderunning.state;

import com.coderunning.context.OrderStateContext;
import com.coderunning.domain.Order;

public abstract class AbstractOrderState {

    public abstract Enum type();

    /**
     * 发起支付
     *
     * @param context
     * @param order
     */
    public void pay(OrderStateContext context, Order order) {
        throw new RuntimeException("该状态下不支持该操作");
    }

    /**
     * 订单发货
     *
     * @param context
     * @param order
     */
    public void deliver(OrderStateContext context, Order order) {
        throw new RuntimeException("该状态下不支持该操作");
    }

    /**
     * 订单收货
     *
     * @param context
     * @param order
     */
    public void receive(OrderStateContext context, Order order) {
        throw new RuntimeException("该状态下不支持该操作");
    }

    /**
     * 售后申请
     *
     * @param context
     * @param order
     */
    public void applyRefund(OrderStateContext context, Order order) {
        throw new RuntimeException("该状态下不支持该操作");
    }

    /**
     * 退款完成
     *
     * @param context
     * @param order
     */
    public void finishRefund(OrderStateContext context, Order order) {
        throw new RuntimeException("该状态下不支持该操作");
    }
}
