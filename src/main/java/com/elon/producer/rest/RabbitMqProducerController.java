package com.elon.producer.rest;

import com.elon.producer.service.RabbitMqProducerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/rabbitmq-producer")
@Api(tags = "RabbitMQ消息生产者")
public class RabbitMqProducerController {
    @Autowired
    private RabbitMqProducerService rabbitMqProducerService;

    @PostMapping("/produce-message")
    public void produceMessage(@RequestBody String messageBody){
        rabbitMqProducerService.produceMessage(messageBody);
    }
}
