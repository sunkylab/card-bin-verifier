package com.mintbank.cardsystem.application.cardschemes.service.implementation;

import com.mintbank.cardsystem.application.binlist.dto.BinLookUpResponseDTO;
import com.mintbank.cardsystem.application.binlist.service.BinListService;
import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeLogDTO;
import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeResponseDTO;
import com.mintbank.cardsystem.application.cardschemes.entity.CardSchemeLog;
import com.mintbank.cardsystem.application.cardschemes.repository.CardSchemeLogRepo;
import com.mintbank.cardsystem.application.cardschemes.service.CardSchemeService;
import com.mintbank.cardsystem.core.exceptions.AppBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CardSchemeServiceImpl implements CardSchemeService {

    private static final Logger logger = LoggerFactory.getLogger(CardSchemeServiceImpl.class);


    @Override
    public CardSchemeResponseDTO getCardScheme(String bin_inn) throws AppBaseException {
        return null;
    }

    @Override
    public CardSchemeLogDTO getHitCount(int start, int limit) throws AppBaseException {
        return null;
    }
}
