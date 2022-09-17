package com.example.sbmessaging.mq;

import org.apache.camel.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;

@Configuration
public class MQConfig {
    public static final String QUE_NAME = "queue1";
    public static final String TOPIC_NAME = "main_topic";

    @Bean
    Queue queue1() {
        return new Queue(QUE_NAME);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topic) {
        return BindingBuilder.bind(queue).to(topic).with("contr.act.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
