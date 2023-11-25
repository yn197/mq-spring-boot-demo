package com.example.rabbitmq.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 16074
 */
@SpringBootApplication
@Slf4j
public class RabbitmqDemoApplication {

    public static void main(String[] args) {
        log.info("Starting RabbitMQ启动成功了!");
        SpringApplication.run(RabbitmqDemoApplication.class, args);
    }

}
