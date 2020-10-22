package com.example.rabbitmq.consumer;

import com.example.rabbitmq.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    @RabbitListener(queues = {"topic.queue.one"})
    public void topicQueueOneConsumer(Message message) {
        System.out.println("topic.queue.one:" + message.toString());
    }

    @RabbitListener(queues = {"topic.queue.two"})
    public void topicQueueTwoConsumer(Message message) {
        System.out.println("topic.queue.two:" + message.toString());
    }
}
