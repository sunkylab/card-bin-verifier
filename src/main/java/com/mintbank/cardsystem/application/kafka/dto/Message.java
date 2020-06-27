package com.mintbank.cardsystem.application.kafka.dto;

import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeDTO;
import lombok.Data;

@Data
public class Message extends CardSchemeDTO {

    public Message(CardSchemeDTO cardSchemeDTO) {
        super(cardSchemeDTO);
    }
}
