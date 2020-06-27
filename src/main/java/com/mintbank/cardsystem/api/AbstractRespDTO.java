package com.mintbank.cardsystem.api;

import lombok.Data;

@Data
public abstract class AbstractRespDTO {

    private boolean success;
    private Object payload;
}
