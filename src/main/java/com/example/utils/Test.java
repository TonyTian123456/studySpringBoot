package com.example.utils;

import com.example.rabbitmq.dataobject.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cicada on 2019/9/19.
 */
public class Test {

    public static void main(String[] args) {
        Order order1 = new Order();
        order1.setOrderStatus(0);
        order1.setOrderId("123456");
        order1.setOrderName("小米6");

        Order order2 = new Order();
        order2.setOrderStatus(1);
        order2.setOrderId("456789");
        order2.setOrderName("小米8");

        Order order3 = new Order();
        order3.setOrderStatus(1);
        order3.setOrderId("4567890");
        order3.setOrderName("小米11");

        Order order4 = new Order();
        order4.setOrderStatus(1);
        order4.setOrderId("4056789");
        order4.setOrderName("小米12");

        Order order5 = new Order();
        order5.setOrderStatus(1);
        order5.setOrderId("456789");
        order5.setOrderName("小米13");

        Order order6 = new Order();
        order6.setOrderStatus(1);
        order6.setOrderId("456789");
        order6.setOrderName("小米14");

        Order order7 = new Order();
        order7.setOrderStatus(1);
        order7.setOrderId("456789");
        order7.setOrderName("小米15");
        List<Order>  orders = new ArrayList<Order>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
        orders.add(order6);
        orders.add(order7);
//        List<String>  orderNames=orders.stream().map(Order::getOrderName).collect(Collectors.toList());
        List<String>  orderNames=orders.stream().map(Order::getOrderName).collect(Collectors.toList());
        for (String item : orderNames){
            System.out.print(item+" ");
        }
        System.out.println();
        for (Order item:orders){
            System.out.print(item.getOrderId()+" ");
        }
    }
}
