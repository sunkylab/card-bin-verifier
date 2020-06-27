package com.mintbank.cardsystem.application.binlist.service;


import com.mintbank.cardsystem.application.binlist.dto.BinLookUpResponseDTO;
import com.mintbank.cardsystem.core.exceptions.AppBaseException;

public interface BinListService {

    BinLookUpResponseDTO doBinLookUp(String bin_inn) throws AppBaseException;
}
