package com.mintbank.cardsystem.application.binlist.service.implementation;


import com.google.gson.Gson;
import com.mintbank.cardsystem.application.binlist.dto.BinLookUpResponseDTO;
import com.mintbank.cardsystem.application.binlist.service.BinListService;
import com.mintbank.cardsystem.core.exceptions.AppBaseException;
import com.mintbank.cardsystem.core.utility.HttpCustomClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class BinListServiceImpl implements BinListService {

    private static final Logger logger = LoggerFactory.getLogger(BinListServiceImpl.class);


    @Value("${binlist.service.url}")
    private String binListUrl;

    @Override
    public BinLookUpResponseDTO doBinLookUp(String bin_inn) throws AppBaseException {

        String fullUrl = binListUrl.concat("/").concat(bin_inn.substring(0,8));
        HttpCustomClient customClient = new HttpCustomClient(fullUrl,null,"GET",null);

        BinLookUpResponseDTO lookUpResponseDTO = null;

        String httpResponse = customClient.makeHttpRequest();
        lookUpResponseDTO = new Gson().fromJson(httpResponse,BinLookUpResponseDTO.class);


        return lookUpResponseDTO;
    }
}
