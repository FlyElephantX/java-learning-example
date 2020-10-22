package com.example.rabbitmq.consumer;

import com.example.rabbitmq.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumer {

    @RabbitListener(queues = {"fanout.queue.one"})
    public void fanoutQueueOneConsumer(Message message) {
        System.out.println("fanout.queue.one:" + message.toString());
    }

    @RabbitListener(queues = {"fanout.queue.two"})
    public void fanoutQueueTwoConsumer(Message message) {
        System.out.println("fanout.queue.two:" + message.toString());
    }

    @RabbitListener(queues = {"fanout.queue.three"})
    public void fanoutQueueThreeConsumer(Message message) {
        System.out.println("fanout.queue.three:" + message.toString());
    }
}
