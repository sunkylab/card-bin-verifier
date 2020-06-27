package com.mintbank.cardsystem.application.binlist.service.implementation;


import com.mintbank.cardsystem.application.binlist.dto.BinLookUpResponseDTO;
import com.mintbank.cardsystem.application.binlist.service.BinListService;
import com.mintbank.cardsystem.core.exceptions.AppBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class BinListServiceImpl implements BinListService {

    private static final Logger logger = LoggerFactory.getLogger(BinListServiceImpl.class);



    @Override
    public BinLookUpResponseDTO doBinLookUp(String bin_inn) throws AppBaseException {
        return null;
    }
}
