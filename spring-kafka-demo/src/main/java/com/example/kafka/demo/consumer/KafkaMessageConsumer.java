package com.example.kafka.demo.consumer;

import com.example.kafka.demo.model.RiderLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageConsumer {

    @KafkaListener(topics = "first_topic", groupId = "my-first-application")
    public void consume(RiderLocation riderLocation) {
        log.info("Received: {}", riderLocation.toString());
    }
}
