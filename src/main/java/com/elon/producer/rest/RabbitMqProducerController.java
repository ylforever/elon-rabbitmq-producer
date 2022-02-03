package com.elon.producer.rest;

import com.elon.producer.service.RabbitMqProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "发送消息到队列")
    public void produceMessage(@RequestBody String messageBody){
        rabbitMqProducerService.produceMessage(messageBody);
    }

    @PostMapping("/produce_message_2_exchange")
    @ApiOperation(value = "生产消息发送到交换器")
    public void produceMessage2Exchange(@RequestBody String messageBody){
        rabbitMqProducerService.produceMessage2Exchange(messageBody);
    }
}