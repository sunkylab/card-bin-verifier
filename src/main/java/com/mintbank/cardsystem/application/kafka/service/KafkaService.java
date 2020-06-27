package com.mintbank.cardsystem.application.kafka.service;


import com.mintbank.cardsystem.application.kafka.dto.Message;

public interface KafkaService {

    void produceMessage(Message message);
}
