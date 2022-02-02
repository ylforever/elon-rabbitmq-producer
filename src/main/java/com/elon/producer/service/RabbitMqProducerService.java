package com.elon.producer.service;

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

    private final static String QUEUE_NAME = "elon_queue3";

    private static Channel channel = null;
    static {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.43.134");
        factory.setPort(5672);
        factory.setUsername("yzy");
        factory.setPassword("yzy614114");
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (Exception e) {
            LOGGER.error("Init RabbitMq exception.", e);
        }
    }

    /**
     * 生产消息
     *
     * @param messageBody 消息体
     * @author yzy
     */
    public void produceMessage(String messageBody) {
        try {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, messageBody.getBytes());
            LOGGER.info("Sent {}", messageBody);
        } catch (Exception e) {
            LOGGER.info("Produce message fail.", e);
        }
    }
}
