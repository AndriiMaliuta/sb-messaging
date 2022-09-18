package com.andmal.mq.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MqSender {
    private final RabbitTemplate rabbitTemplate;

    public MqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 10000, initialDelay = 2000)
    public void send() {
        String message = "Hello from Spring Boot!";
        this.rabbitTemplate.convertAndSend(MQConfig.QUE_NAME, message);
        System.out.println(" [>>] MqSender Sent '" + message + "'");
    }
}
