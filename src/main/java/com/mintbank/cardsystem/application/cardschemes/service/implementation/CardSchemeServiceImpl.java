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

    @Autowired
    CardSchemeLogRepo cardSchemeLogRepo;

    @Autowired
    BinListService binListService;

    @Override
    public CardSchemeResponseDTO getCardScheme(String bin_inn) throws AppBaseException {

        CardSchemeResponseDTO cardSchemeResponseDTO = new CardSchemeResponseDTO();

        BinLookUpResponseDTO lookUpResponseDTO  = binListService.doBinLookUp(bin_inn);

        cardSchemeResponseDTO.setBank(lookUpResponseDTO.getBank().getName());
        cardSchemeResponseDTO.setScheme(lookUpResponseDTO.getScheme());
        cardSchemeResponseDTO.setType(lookUpResponseDTO.getType());

        CardSchemeLog  cardSchemeLog = cardSchemeLogRepo.findByCardBin(bin_inn);
        if(cardSchemeLog!=null){
            cardSchemeLog.setUpdateOn(new Date());
            /**Updates version count**/
            cardSchemeLogRepo.save(cardSchemeLog);
        }else{
            cardSchemeLog = new CardSchemeLog();
            /**sets version count to 1**/
            cardSchemeLog.setCardBin(bin_inn);
            cardSchemeLogRepo.save(cardSchemeLog);
        }


        return cardSchemeResponseDTO;
    }

    @Override
    public CardSchemeLogDTO getHitCount(int start, int limit) throws AppBaseException {

        CardSchemeLogDTO cardSchemeLogDTO = new CardSchemeLogDTO();
        Pageable pageable = PageRequest.of(start-1,limit);

        Page<CardSchemeLog> schemeRequestLogPage = cardSchemeLogRepo.fetchRequestLogs(pageable);

        cardSchemeLogDTO.setPage(start-1);
        cardSchemeLogDTO.setSize(limit);
        cardSchemeLogDTO.setTotalCount(schemeRequestLogPage.getTotalElements());
        cardSchemeLogDTO.setHasNextRecord(schemeRequestLogPage.hasNext());

        Map<String,Integer> stringMap = new HashMap<>();

        schemeRequestLogPage.getContent().forEach(cardSchemeLog -> {
            stringMap.put(cardSchemeLog.getCardBin(),cardSchemeLog.getVersion());
        });

        cardSchemeLogDTO.setCardSchemeMap(stringMap);



        return cardSchemeLogDTO;
    }
}
