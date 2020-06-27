package com.mintbank.cardsystem.application.cardschemes.service;

import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeDTO;
import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeLogDTO;
import com.mintbank.cardsystem.core.exceptions.AppBaseException;

public interface CardSchemeService {

    CardSchemeDTO getCardScheme(String bin_inn) throws AppBaseException;

    CardSchemeLogDTO getHitCount(int start, int limit) throws AppBaseException;

}
