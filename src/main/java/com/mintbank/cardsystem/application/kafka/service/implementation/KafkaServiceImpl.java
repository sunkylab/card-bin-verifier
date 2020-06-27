package com.mintbank.cardsystem.application.kafka.service.implementation;

import com.google.gson.Gson;
import com.mintbank.cardsystem.application.cardschemes.service.implementation.CardSchemeServiceImpl;
import com.mintbank.cardsystem.application.kafka.dto.Message;
import com.mintbank.cardsystem.application.kafka.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public class KafkaServiceImpl implements KafkaService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topic}")
    private String topicName;

    @Override
    public void produceMessage(Message message) {

        String jsonString = new Gson().toJson(message);
        try{
            this.sendMessage(jsonString);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void sendMessage(String message) {

        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Sent message {} with topic {} ",message,result.getRecordMetadata().topic());
            }
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Unable to send message {} due to {} ",message,ex.getMessage());
            }
        });
    }
}
