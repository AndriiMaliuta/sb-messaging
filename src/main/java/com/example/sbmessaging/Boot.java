package com.example.sbmessaging;

import com.example.sbmessaging.mq.MQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Boot implements CommandLineRunner {
    private final AmqpTemplate amqpTemplate;
    private final RabbitTemplate rabbitTemplate;

    public Boot(AmqpTemplate amqpTemplate, RabbitTemplate rabbitTemplate) {
        this.amqpTemplate = amqpTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        var msgBody = "sending message in Boot" + LocalDateTime.now();
        Message message = new Message(msgBody.getBytes(), new MessageProperties());
//        amqpTemplate.send("contr.act", message);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_NAME, "contr.act.one", msgBody);

    }
}
