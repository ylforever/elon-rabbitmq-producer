package com.elon.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * RabbitMQ 生产者引用启动类
 *
 * @author elon
 * @since 2022-01-19
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ElonRabbitMqProducerApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElonRabbitMqProducerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ElonRabbitMqProducerApplication.class);
        LOGGER.info("Start up rabbitmq producer success.");
    }
}
