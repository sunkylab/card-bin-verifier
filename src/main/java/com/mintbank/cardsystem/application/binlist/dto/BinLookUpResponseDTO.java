package com.mintbank.cardsystem.application.binlist.dto;


import lombok.Data;

@Data
public class BinLookUpResponseDTO {

    private CardNumberPropsDTO number;
    private String scheme;
    private String type;
    private String brand;
    private String prepaid;

    private CountryDTO country;
    private BankDTO bank;

}
