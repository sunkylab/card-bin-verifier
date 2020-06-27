package com.mintbank.cardsystem.application.cardschemes.dto;


import lombok.Data;

@Data
public class CardSchemeDTO {

    private String scheme;
    private String type;
    private String bank;

    public CardSchemeDTO() {
    }

    public CardSchemeDTO(CardSchemeDTO cardSchemeDTO) {
        this.scheme = cardSchemeDTO.getScheme();
        this.type = cardSchemeDTO.getType();
        this.bank = cardSchemeDTO.getBank();
    }
}
