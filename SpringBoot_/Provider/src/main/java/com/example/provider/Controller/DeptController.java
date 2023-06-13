package com.example.provider.Controller;

import com.example.provider.Service.DeptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* 服务提供者的控制层
* author:c语言中文网 c.biancheng.net
*/
@RestController
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

  
    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") int id) {

        Dept dept = deptService.get(id);
        // 发送消息到Kafka主题
        sendMessage("first",dept);
        return dept;
    }

    @GetMapping("/dept/list")
    public List<Dept> list() {
        List<Dept> deptList = deptService.selectAll();
        // 发送消息到Kafka主题
        sendMessage("first",deptList);
        return deptList;
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
            System.out.println("成功");
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            // 处理序列化异常
            System.out.println("异常");
            e.printStackTrace();
            return null;
        }
    }
}