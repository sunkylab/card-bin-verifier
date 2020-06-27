package com.mintbank.cardsystem.api.cardschemes.dto;

import com.mintbank.cardsystem.api.AbstractRespDTO;
import lombok.Data;

@Data
public class HitCountResponseDTO extends AbstractRespDTO {

    private int start;
    private int limit;
    private long size;

}
