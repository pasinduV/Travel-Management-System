package com.payment.payment_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Queue names (should match hotel service)
    public static final String BOOKING_QUEUE = "booking.queue";
    public static final String PAYMENT_QUEUE = "payment.queue";
    public static final String BOOKING_CONFIRMATION_QUEUE = "booking.confirmation.queue";

    // Exchange names
    public static final String BOOKING_EXCHANGE = "booking.exchange";

    // Routing keys
    public static final String BOOKING_ROUTING_KEY = "booking.created";
    public static final String PAYMENT_ROUTING_KEY = "payment.process";
    public static final String CONFIRMATION_ROUTING_KEY = "booking.confirmed";
    public static final String PAYMENT_SUCCESS_ROUTING_KEY = "payment.success";
    public static final String PAYMENT_FAILED_ROUTING_KEY = "payment.failed";

    // Exchange
    @Bean
    public TopicExchange bookingExchange() {
        return new TopicExchange(BOOKING_EXCHANGE);
    }

    // Queues
    @Bean
    public Queue bookingQueue() {
        return QueueBuilder.durable(BOOKING_QUEUE).build();
    }

    @Bean
    public Queue paymentQueue() {
        return QueueBuilder.durable(PAYMENT_QUEUE).build();
    }

    @Bean
    public Queue bookingConfirmationQueue() {
        return QueueBuilder.durable(BOOKING_CONFIRMATION_QUEUE).build();
    }

    // Bindings
    @Bean
    public Binding bookingBinding() {
        return BindingBuilder
                .bind(bookingQueue())
                .to(bookingExchange())
                .with(BOOKING_ROUTING_KEY);
    }

    @Bean
    public Binding paymentBinding() {
        return BindingBuilder
                .bind(paymentQueue())
                .to(bookingExchange())
                .with(PAYMENT_ROUTING_KEY);
    }

    @Bean
    public Binding confirmationBinding() {
        return BindingBuilder
                .bind(bookingConfirmationQueue())
                .to(bookingExchange())
                .with(CONFIRMATION_ROUTING_KEY);
    }

    // Message converter
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    // Listener container factory
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter());
        return factory;
    }
}
