package com.threefour.backend.Order;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public static String addnumbers(int a, int b){
        return "The Sum is" + (a+b);
    }
}
