package com.example.rabbitmq.consumer;

import com.example.rabbitmq.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersConsumer {

    @RabbitListener(queues = {"headers.queue"})
    public void headersQueueConsumer(Message message) {
        System.out.println("headers.queue.one:" + message.toString());
    }
}
