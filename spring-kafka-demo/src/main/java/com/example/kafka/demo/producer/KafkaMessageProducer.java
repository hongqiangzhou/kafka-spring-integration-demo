package com.example.kafka.demo.producer;

import com.example.kafka.demo.model.RiderLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageProducer {

    private final KafkaTemplate<String, RiderLocation> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<String, RiderLocation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(RiderLocation riderLocation) {
        kafkaTemplate.send("first_topic", riderLocation);
        log.info("Sent message: " + riderLocation.getRiderId());
    }

}
