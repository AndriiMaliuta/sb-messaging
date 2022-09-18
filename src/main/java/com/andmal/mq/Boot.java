package com.andmal.mq;

import com.andmal.mq.mq.MQConfig;
import com.andmal.mq.mq.MqSender;
import com.andmal.mq.mq.MyReceiverLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Boot implements CommandLineRunner {
    Logger LOG = LoggerFactory.getLogger(Boot.class);
    private final RabbitTemplate rabbitTemplate;
    private final MyReceiverLatch receiver;
    private final MqSender sender;

    public Boot(MyReceiverLatch receiver, RabbitTemplate rabbitTemplate, MqSender sender) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.sender = sender;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Sending message in BOOT class...");
        rabbitTemplate.convertAndSend(MQConfig.TOPIC_NAME, MQConfig.QUE_NAME, "Hello from RabbitMQ!"); // send directly
        sender.send();  // send with Sender
    }
}
