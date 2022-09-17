package com.andmal.mq;

import com.andmal.mq.mq.MQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
public class DateController {
    private final AmqpTemplate amqpTemplate;
    private final RabbitMessagingTemplate rabbitTemplate;
//    private final Queue queue;

    @Autowired
    public DateController(AmqpTemplate amqpTemplate, RabbitMessagingTemplate rabbitTemplate) {
        this.amqpTemplate = amqpTemplate;
        this.rabbitTemplate = rabbitTemplate;
//        this.queue = queue;
    }

    @GetMapping("/dates")
    public Mono<String> getDate() {
        Message message = new Message("'/dates' page opened".getBytes(), new MessageProperties());
        this.amqpTemplate.convertAndSend(MQConfig.QUE_NAME, message);
        return Mono.just(LocalDateTime.now().toString());
    }
}
