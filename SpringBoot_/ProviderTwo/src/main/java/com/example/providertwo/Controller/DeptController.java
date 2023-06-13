package com.example.providertwo.Controller;

import com.example.providertwo.Service.DeptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
  
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") int id) {
        Dept dept = deptService.get(id);
        // 发送消息到Kafka主题
        sendMessage("second", dept);
        return dept;
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        List<Dept> deptList = deptService.selectAll();
        // 发送消息到Kafka主题
        sendMessage("second",deptList);
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
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            // 处理序列化异常
            e.printStackTrace();
            return null;
        }
    }
}