package org.macedo.amqp.config;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.macedo.amqp.enums.CommonQueues;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQBaseConfig {

    @Bean
    public Queue logQueue() {
        return new Queue(CommonQueues.LOG_QUEUE.getQueueName(), true);
    }

    @Bean
    public TopicExchange logExchange() {
        return new TopicExchange(CommonQueues.LOG_EXCHANGE.getQueueName());
    }

    @Bean
    public Binding logBinding() {
        return BindingBuilder
                .bind(logQueue())
                .to(logExchange())
                .with(CommonQueues.ROUTING_KEY_LOG.getQueueName());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

}
