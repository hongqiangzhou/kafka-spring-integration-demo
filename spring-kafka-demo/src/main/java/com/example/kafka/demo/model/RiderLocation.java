package com.example.kafka.demo.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RiderLocation {
    private String riderId;
    private double latitude;
    private double longitude;
}