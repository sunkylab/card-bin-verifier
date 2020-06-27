package com.mintbank.cardsystem.application.cardschemes.service.implementation;

import com.mintbank.cardsystem.application.binlist.dto.BinLookUpResponseDTO;
import com.mintbank.cardsystem.application.binlist.service.BinListService;
import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeDTO;
import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeLogDTO;
import com.mintbank.cardsystem.application.cardschemes.entity.CardScheme;
import com.mintbank.cardsystem.application.cardschemes.entity.CardSchemeLog;
import com.mintbank.cardsystem.application.cardschemes.repository.CardSchemeLogRepo;
import com.mintbank.cardsystem.application.cardschemes.repository.CardSchemeRepo;
import com.mintbank.cardsystem.application.cardschemes.service.CardSchemeService;
import com.mintbank.cardsystem.application.kafka.dto.Message;
import com.mintbank.cardsystem.application.kafka.service.KafkaService;
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
    CardSchemeRepo cardSchemeRepo;

    @Autowired
    BinListService binListService;

    @Autowired
    KafkaService kafkaService;

    @Override
    public CardSchemeDTO getCardScheme(String bin_inn) throws AppBaseException {

        CardSchemeDTO cardSchemeDTO = new CardSchemeDTO();

        //Check if card bin info has been persisted
        CardScheme cardScheme = cardSchemeRepo.findByCardBin(bin_inn);
        if(cardScheme==null){
            //Verify Card using BINLIST service
            BinLookUpResponseDTO lookUpResponseDTO  = binListService.doBinLookUp(bin_inn);
            if(lookUpResponseDTO.getBank()==null){
                throw new AppBaseException("Bin is invalid");
            }
            cardSchemeDTO.setBank(lookUpResponseDTO.getBank().getName());
            cardSchemeDTO.setScheme(lookUpResponseDTO.getScheme());
            cardSchemeDTO.setType(lookUpResponseDTO.getType());
        }else{
            cardSchemeDTO.setBank(cardScheme.getBank());
            cardSchemeDTO.setScheme(cardScheme.getScheme());
            cardSchemeDTO.setType(cardScheme.getType());
        }

        //log user's request count to table
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

        //produce kafka message to notify card verification done
        Message message = new Message(cardSchemeDTO);
        kafkaService.produceMessage(message);


        return cardSchemeDTO;
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
