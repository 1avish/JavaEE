package com.example.provider.Controller;

import com.example.provider.Service.UpdateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.entity.User;
import com.example.provider.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateController {
    @Autowired
    private UpdateService updateService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PutMapping("/update")
    public Result<?> update(@RequestBody User user) {
        Result<?> result = updateService.update(user);
        // 发送消息到Kafka主题
        sendMessage("first", result);
        return result;
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
