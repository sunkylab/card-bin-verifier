package com.mintbank.cardsystem.application.cardschemes.dto;


import lombok.Data;

import java.util.Map;

@Data
public class CardSchemeLogDTO {

    private boolean hasNextRecord;
    private long totalCount;
    private int page;
    private int size;

    private Map<String,Integer> cardSchemeMap;

}
