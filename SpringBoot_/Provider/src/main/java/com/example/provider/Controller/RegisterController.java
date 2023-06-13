package com.example.provider.Controller;

import com.example.provider.Service.RegisterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.entity.User;
import com.example.provider.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        if (!registerService.register(user)){
            sendMessage("first", Result.error("404", "注册失败"));
            return Result.error("404", "注册失败");
        }
        // 发送消息到Kafka主题
        sendMessage("first", Result.success());
        return Result.success();
    }

    public void sendMessage(String topic, Object message) {
        // 将消息对象转换为JSON字符串
        String jsonMessage = convertToJson(message);
        // 使用KafkaTemplate发送消息
        kafkaTemplate.send(new ProducerRecord<>(topic, jsonMessage));
    }

    public String convertToJson(Object message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            // 处理序列化异常
            e.printStackTrace();
            return null;
        }
    }
}
