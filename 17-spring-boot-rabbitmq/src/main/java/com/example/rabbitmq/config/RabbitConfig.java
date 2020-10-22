package com.example.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queueFirst() {
        return new Queue("elephant.first", true);
    }

    @Bean
    public Queue queueTwo() {
        return new Queue("elephant.two", true);
    }

    @Bean
    TopicExchange topicExchange1() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindingFirst() {
        return BindingBuilder.bind(queueFirst()).to(topicExchange1()).with("routingKey.first");
    }

    @Bean
    public Binding bindingTwo() {
        return BindingBuilder.bind(queueTwo()).to(topicExchange1()).with("routingKey.two");
    }
}
