//package com.andmal.mq.mq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RMQlistener {
//
//    @RabbitHandler
//    @RabbitListener(queues = "queue.one")
//    public void processMsg(String msg) {
//        System.out.println(">>> listening to msg :: " + msg);
//    }
//    @RabbitHandler
//    @RabbitListener(queues = "hello")
//    public void listenToHello(String msg) {
//        System.out.println(">>> listening to HELLO msg :: " + msg);
//    }
//
//}
