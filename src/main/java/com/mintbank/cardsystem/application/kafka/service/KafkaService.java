package com.mintbank.cardsystem.application.kafka.service;


import com.mintbank.cardsystem.application.kafka.dto.Message;
import org.springframework.scheduling.annotation.Async;

public interface KafkaService {

    @Async
    void produceMessage(Message message);
}
