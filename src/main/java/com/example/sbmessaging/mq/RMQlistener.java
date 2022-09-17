package com.example.sbmessaging.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RMQlistener {

    @RabbitListener(queues = "queue1")
    public void processMsg(String msg) {
        System.out.println(">>> listening to msg :: " + msg);
    }
}
