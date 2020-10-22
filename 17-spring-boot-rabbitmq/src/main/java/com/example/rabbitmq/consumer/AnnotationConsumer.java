package com.example.rabbitmq.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotationConsumer {

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue(name = "topic.queue.annotation"),
                            exchange = @Exchange(name = "topic.exchange", type = ExchangeTypes.TOPIC),
                            key = {"routing"}
                    )
            }
    )
    public void topicQueueOneConsumer(Message message) {
        System.out.println("topic.queue.annotation:" + message.toString());
    }

}
