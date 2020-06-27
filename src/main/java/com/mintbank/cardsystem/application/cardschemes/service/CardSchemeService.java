package com.mintbank.cardsystem.application.cardschemes.service;

import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeLogDTO;
import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeResponseDTO;
import com.mintbank.cardsystem.core.exceptions.AppBaseException;

public interface CardSchemeService {

    CardSchemeResponseDTO getCardScheme(String bin_inn) throws AppBaseException;

    CardSchemeLogDTO getHitCount(int start, int limit) throws AppBaseException;

}
