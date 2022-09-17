package com.example.sbmessaging.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = "queue1")
public class RMQlistener {

    @RabbitHandler
    public void processMsg(String msg) {
        System.out.println(">>> listening to msg :: " + msg);
    }
}
