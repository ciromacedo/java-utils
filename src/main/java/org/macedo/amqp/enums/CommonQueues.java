package org.macedo.amqp.enums;

public enum CommonQueues {

    LOG_QUEUE("log.queue"),
    LOG_EXCHANGE("log.exchange"),
    ROUTING_KEY_LOG("log.#");

    private final String value;

    CommonQueues(String value) {
        this.value = value;
    }

    public String getQueueName() {
        return value;
    }

    public String getExchangeName() {
        return value;
    }

    public String getRoutingKey() {
        return value;
    }

    public String get() {
        return value;
    }
}