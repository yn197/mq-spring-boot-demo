package com.example.rabbitmq.demo;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RabbitmqDemoApplicationTests {
    @Resource
    AmqpTemplate amqpTemplate;

    @Test
    void contextLoads() {
        String msg = "Hello ,Spring boot amqp";
        amqpTemplate.convertAndSend("spring.test.exchange", "small.orange.cat", msg);
    }
}
