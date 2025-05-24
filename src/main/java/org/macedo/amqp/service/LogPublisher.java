package org.macedo.amqp.service;

import org.macedo.amqp.enums.CommonQueues;
import org.macedo.log.LogMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class LogPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void registrar(LogMessageDTO dto) {
        rabbitTemplate.convertAndSend(
                CommonQueues.LOG_EXCHANGE.getQueueName(),    // exchange
                CommonQueues.ROUTING_KEY_LOG.getQueueName(), // routing key
                dto
        );
    }
}
