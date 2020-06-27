package com.mintbank.cardsystem.api.cardschemes.controller;


import com.mintbank.cardsystem.api.cardschemes.dto.HitCountResponseDTO;
import com.mintbank.cardsystem.api.cardschemes.dto.VerifyBinResponseDTO;
import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeDTO;
import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeLogDTO;
import com.mintbank.cardsystem.application.cardschemes.service.CardSchemeService;
import com.mintbank.cardsystem.core.exceptions.AppBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/card-scheme")
@RestController
public class CardSchemeController {

    private static Logger logger = LoggerFactory.getLogger(CardSchemeController.class);

    @Autowired
    CardSchemeService cardSchemeService;



    @GetMapping("/verify/{bin_inn}")
    public ResponseEntity<?> verifyBinAndInn(@PathVariable("bin_inn") String binInn) {

        VerifyBinResponseDTO binResponseDTO = new VerifyBinResponseDTO();
        ResponseEntity<?> responseEntity;

        try{
            CardSchemeDTO schemeResponseDTO = cardSchemeService.getCardScheme(binInn);
            binResponseDTO.setSuccess(true);
            binResponseDTO.setPayload(schemeResponseDTO);
            responseEntity = new ResponseEntity(binResponseDTO, HttpStatus.OK);

        }catch (AppBaseException e){
            logger.error("application exception:{}",e.getMessage());
            binResponseDTO.setSuccess(false);
            responseEntity =  new ResponseEntity(binResponseDTO, HttpStatus.BAD_REQUEST);
        }catch (Exception e){

            e.printStackTrace();
            responseEntity =  new ResponseEntity(binResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }

    @GetMapping("/stats")
    public ResponseEntity<?> getHitCount(@RequestParam ("start") int start,
                              @RequestParam ("limit") int limit) {
        HitCountResponseDTO hitCountResponseDTO = new HitCountResponseDTO();
        ResponseEntity<?> responseEntity;

        try{
            CardSchemeLogDTO cardSchemeLogDTO = cardSchemeService.getHitCount(start,limit);

            hitCountResponseDTO.setSuccess(true);
            hitCountResponseDTO.setStart(start);
            hitCountResponseDTO.setLimit(cardSchemeLogDTO.getSize());
            hitCountResponseDTO.setSize(cardSchemeLogDTO.getTotalCount());

            hitCountResponseDTO.setPayload(cardSchemeLogDTO.getCardSchemeMap());
            responseEntity = new ResponseEntity(hitCountResponseDTO, HttpStatus.OK);

        }catch (AppBaseException e){
            logger.error("Application exception {}",e.getMessage());
            hitCountResponseDTO.setSuccess(false);
            responseEntity =  new ResponseEntity(hitCountResponseDTO, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity =  new ResponseEntity(hitCountResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }

}
