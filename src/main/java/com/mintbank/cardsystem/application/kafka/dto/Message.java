package com.mintbank.cardsystem.application.kafka.dto;

import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeDTO;
import lombok.Data;

@Data
public class Message extends CardSchemeDTO {

    public Message(CardSchemeDTO cardSchemeDTO) {
        super(cardSchemeDTO);
    }

    public Message(String bank,String scheme,String type) {
        this.setBank(bank);
        this.setScheme(scheme);
        this.setType(type);
    }
}
