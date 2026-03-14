package com.example.kafka.demo.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<String,String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("first_topic", message);
        log.info("Sent message: " + message);
    }

}
