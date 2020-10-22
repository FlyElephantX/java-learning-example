package com.example.rabbitmq.producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Sender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送成功:" + correlationData);
        } else {
            System.out.println("消息发送失败:" + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message.getMessageProperties().getCorrelationId() + " 发送失败");
    }

    public void send(String msg){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("开始发送消息:" + msg.toLowerCase());
        String response = rabbitTemplate.convertSendAndReceive("topicExchange", "routingKey.first", msg, correlationId).toString();
        System.out.println("结束发送消息:" + msg.toLowerCase());
        System.out.println("消费者响应:" + response + " 消息处理完成");
    }
}