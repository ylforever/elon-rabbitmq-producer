package com.elon.producer.service;

import com.google.common.base.Charsets;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ生产者服务类
 *
 * @author yzy
 * @since 2022-01-19
 */
@Component
public class RabbitMqProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducerService.class);

    private final static String QUEUE_NAME = "elon_queue";
    
    private final static String EXCHANGE_NAME = "elon_exchange";
 
    /**
     * 生产消息
     *
     * @param messageBody 消息体
     * @author elon
     */
    public void produceMessage(String messageBody) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.43.134");
        factory.setPort(5672);
        factory.setUsername("yzy");
        factory.setPassword("yzy614114");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, messageBody.getBytes());
            LOGGER.info("Sent {}", messageBody);
        } catch (Exception e) {
            LOGGER.info("Produce message fail.", e);
        }
    }

    /**
     * 生产消息发送到交换器.
     *
     * @param messageBody 消息体
     * @author elon
     */
    public void produceMessage2Exchange(String messageBody) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.43.134");
        factory.setPort(5672);
        factory.setUsername("yzy");
        factory.setPassword("yzy614114");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            channel.basicPublish(EXCHANGE_NAME, "", null, messageBody.getBytes(Charsets.UTF_8));
            LOGGER.info("Sent {}", messageBody);
        } catch (Exception e) {
            LOGGER.info("Produce message fail.", e);
        }
    }
}
