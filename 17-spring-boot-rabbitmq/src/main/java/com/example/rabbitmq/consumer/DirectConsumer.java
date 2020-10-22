package com.example.rabbitmq.consumer;

import com.example.rabbitmq.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectConsumer {

    @RabbitListener(queues = {"direct.queue"})
    public void directQueueConsumer(Message message) {
        System.out.println("direct.queue:" + message.toString());
    }
}
