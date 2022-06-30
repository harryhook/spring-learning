package com.coderunning;

import com.coderunning.domain.Order;

public class OrderRepository {

    public void save(Order order) {
        System.out.println("保存订单");
    }

    public void insert(Order order) {
    }

    public Order get(String orderSn) {
        return new Order();
    }

    public void update(Order order) {
    }
}
