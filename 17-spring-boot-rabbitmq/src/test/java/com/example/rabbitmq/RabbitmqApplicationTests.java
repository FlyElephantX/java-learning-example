package com.example.rabbitmq;

import com.example.rabbitmq.model.Message;
import com.example.rabbitmq.producer.Sender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class RabbitmqApplicationTests {

    @Autowired
    private Sender sender;

    @Resource
    private RabbitTemplate rabbitTemplate;
    private Message<String> message;

    @BeforeEach
    public void buildMessage() {
        message = new Message<>();
        message.id = UUID.randomUUID().toString();
        message.content = "elephant-springboot-rabbitmq";
    }

//    @Test
//    public void sendTest() throws InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            String msg = new Date().toString();
//            sender.send(msg);
//            Thread.sleep(1000);
//        }
//    }

    @Test
    public void directProducer() {
        rabbitTemplate.convertAndSend("direct.exchange", "direct.queue", message);
    }

    @Test
    public void fanoutProducer() {
        rabbitTemplate.convertAndSend("fanout.exchange", "", message);
    }

    @Test
    public void topicProducer() {
        rabbitTemplate.convertAndSend("topic.exchange", "routing-key", message);
    }

    @Test
    public void headersProducer() {
        rabbitTemplate.convertAndSend("headers.exchange", "", message,
                m -> {
                    m.getMessageProperties().getHeaders().put("headers-key", null);
                    return m;
                });
    }

    @Test
    public void annomationProducer() {
        rabbitTemplate.convertAndSend("topic.exchange", "routing", message);
    }
}
