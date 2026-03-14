package com.example.kafka.demo.controller;

import com.example.kafka.demo.model.RiderLocation;
import com.example.kafka.demo.producer.KafkaMessageProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final KafkaMessageProducer producer;

    public MessageController(KafkaMessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String sendMessage(@RequestBody RiderLocation riderLocation) {
        producer.sendMessage(riderLocation);
        return "Message sent to Kafka";
    }
}
