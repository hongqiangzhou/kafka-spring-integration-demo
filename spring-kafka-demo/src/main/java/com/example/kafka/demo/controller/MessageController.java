package com.example.kafka.demo.controller;

import com.example.kafka.demo.producer.KafkaMessageProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final KafkaMessageProducer producer;

    public MessageController(KafkaMessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String sendMessage(@RequestParam String message) {
        producer.sendMessage(message);
        return "Message sent to Kafka";
    }
}
