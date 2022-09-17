package com.andmal.mq;

import com.andmal.mq.mq.MQConfig;
import com.andmal.mq.mq.MqSender;
import com.andmal.mq.mq.MyReceiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Boot implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    private final MyReceiver receiver;
    private final MqSender sender;

    public Boot(MyReceiver receiver, RabbitTemplate rabbitTemplate, MqSender sender) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.sender = sender;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(MQConfig.TOPIC_NAME, MQConfig.QUE_NAME, "Hello from RabbitMQ!");
        sender.send();
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
