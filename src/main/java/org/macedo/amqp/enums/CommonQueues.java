package org.macedo.amqp.enums;

public enum CommonQueues {
    LOG_QUEUE("log.queue"),
    LOG_EXCHANGE("log.exchange"),
    ROUTING_KEY_LOG ("log.info");

    private final String queueName;

    CommonQueues(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }
}
