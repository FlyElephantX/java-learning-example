package com.example.rabbitmq;

import com.example.rabbitmq.producer.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class RabbitmqApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    void contextLoads() {
    }

    @Test
    public void sendTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            String msg = new Date().toString();
            sender.send(msg);
            Thread.sleep(1000);
        }
    }

}
