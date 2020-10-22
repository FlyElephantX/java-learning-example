package com.example.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues = "elephant.first")
    public String processFirst(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自elephant.first队列的消息：" + msg);
        return msg.toUpperCase();
    }

    @RabbitListener(queues = "elephant.two")
    public String processTwo(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自elephant.two队列的消息：" + msg);
        return msg.toUpperCase();
    }
}
