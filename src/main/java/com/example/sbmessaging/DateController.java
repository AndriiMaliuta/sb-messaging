package com.example.sbmessaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
public class DateController {
    private final AmqpTemplate amqpTemplate;
    private final RabbitMessagingTemplate rabbitTemplate;

    public DateController(AmqpTemplate amqpTemplate, RabbitMessagingTemplate rabbitTemplate) {
        this.amqpTemplate = amqpTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/dates")
    public Mono<String> getDate() {
        Message message = new Message("/dates page opened".getBytes(), new MessageProperties());
        amqpTemplate.send(message);
        return Mono.just(LocalDateTime.now().toString());
    }
}
