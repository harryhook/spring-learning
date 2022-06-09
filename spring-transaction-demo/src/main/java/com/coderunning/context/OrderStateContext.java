package com.coderunning.context;

import com.coderunning.domain.Order;
import com.coderunning.state.AbstractOrderState;

public class OrderStateContext {

    private AbstractOrderState orderState;

    public OrderStateContext() {}

    public AbstractOrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(AbstractOrderState orderState) {
        this.orderState = orderState;
    }

    /**
     * 发起支付
     *
     * @param order
     */
    void pay(Order order) {
        orderState.pay(this, order);
    }

    /**
     * 订单发货
     *
     * @param order
     */
    void deliver(Order order) {
        orderState.deliver(this, order);
    }

    /**
     * 订单收货
     *
     * @param order
     */
    void receive(Order order) {
        orderState.receive(this, order);
    }

    /**
     * 申请售后
     *
     * @param order
     */
    void applyRefund(Order order) {
        orderState.applyRefund(this, order);
    }

    /**
     * 退款完成
     *
     * @param order
     */
    void finishRefund(Order order) {
        orderState.finishRefund(this, order);
    }
}
