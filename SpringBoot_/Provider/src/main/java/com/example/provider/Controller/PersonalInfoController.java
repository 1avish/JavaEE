package com.example.provider.Controller;

import com.example.provider.Service.PersonalInfoService;
import com.example.provider.util.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonalInfoController {

    @Autowired
    private PersonalInfoService personalInfoService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @GetMapping("/getInfo/{id}")
    public Result<?> info(@PathVariable("id") int id){

        Result<?> result = personalInfoService.getInfo(id);
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
