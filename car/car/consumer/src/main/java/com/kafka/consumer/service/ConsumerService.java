package com.kafka.consumer.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@PreAuthorize("hasRole('VENDOR')")
public class ConsumerService {
    @KafkaListener(topics = "sample-topic"  )
    public void flightEventConsumer(String message) {
        log.info( "El consumidor consume Kafka message -> {}" , message);

        // escriba sus controladores y lógica de posprocesamiento, según su caso de uso
    }


}




