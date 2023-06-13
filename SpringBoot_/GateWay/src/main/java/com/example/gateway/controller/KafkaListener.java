package com.example.gateway.controller;

import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    // 监听 Kafka 消息
    @org.springframework.kafka.annotation.KafkaListener(topics = "first")
    public void listen1(String message) {
        // 处理接收到的消息
        System.out.println("Received message from topic 'first': " + message);
        // 可以在此处执行您希望在网关中进行的业务逻辑
    }
    @org.springframework.kafka.annotation.KafkaListener(topics = "second")
    public void listen2(String message) {
        // 处理接收到的消息
        System.out.println("Received message from topic 'second': " + message);
        // 可以在此处执行您希望在网关中进行的业务逻辑
    }
}
