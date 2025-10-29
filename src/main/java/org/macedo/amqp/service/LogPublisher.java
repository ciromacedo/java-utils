package org.macedo.amqp.service;

import org.macedo.amqp.enums.CommonQueues;
import org.macedo.log.LogLevel;
import org.macedo.log.LogMessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void registrar(LogMessageDTO dto) {
        // Gera routing key dinâmica baseada no nível do log
        // Exemplo: LogLevel.INFO -> "log.info"
        String routingKey = "log." + dto.getLevel().name().toLowerCase();

        rabbitTemplate.convertAndSend(
                CommonQueues.LOG_EXCHANGE.getExchangeName(),  // exchange: log.exchange
                routingKey,                                    // routing key: log.info, log.warn, etc
                dto                                            // payload: LogMessageDTO
        );
    }

    public void info(LogMessageDTO dto) {
        dto.setLevel(LogLevel.INFO);
        registrar(dto);
    }


    public void warn(LogMessageDTO dto) {
        dto.setLevel(LogLevel.WARN);
        registrar(dto);
    }


    public void error(LogMessageDTO dto) {
        dto.setLevel(LogLevel.ERROR);
        registrar(dto);
    }

    public void debug(LogMessageDTO dto) {
        dto.setLevel(LogLevel.DEBUG);
        registrar(dto);
    }
}