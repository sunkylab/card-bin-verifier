package com.mintbank.cardsystem;

import com.mintbank.cardsystem.application.binlist.service.BinListService;
import com.mintbank.cardsystem.application.binlist.service.implementation.BinListServiceImpl;
import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeDTO;
import com.mintbank.cardsystem.application.cardschemes.dto.CardSchemeLogDTO;
import com.mintbank.cardsystem.application.cardschemes.entity.CardSchemeLog;
import com.mintbank.cardsystem.application.cardschemes.repository.CardSchemeLogRepo;
import com.mintbank.cardsystem.application.cardschemes.repository.CardSchemeRepo;
import com.mintbank.cardsystem.application.cardschemes.service.CardSchemeService;
import com.mintbank.cardsystem.application.cardschemes.service.implementation.CardSchemeServiceImpl;
import com.mintbank.cardsystem.application.kafka.dto.Message;
import com.mintbank.cardsystem.application.kafka.service.KafkaService;
import com.mintbank.cardsystem.application.kafka.service.implementation.KafkaServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"binlist.service.url=https://lookup.binlist.net",})
public class ApplicationTests {



    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public CardSchemeService cardSchemeService() {
            return new CardSchemeServiceImpl();
        }

        @Bean
        public BinListService binListService() {
            return new BinListServiceImpl();
        }

    }

    @Autowired
    private CardSchemeService cardSchemeService;


    @Autowired
    private BinListService binListService;

    @MockBean
    private KafkaService kafkaService;


    @MockBean
    private CardSchemeLogRepo cardSchemeLogRepo;

    @MockBean
    private CardSchemeRepo cardSchemeRepo;


    @Value("${binlist.service.url}")
    private String binListUrl;


    @Test
    public void whenInvalidCardBinWithInnIsPassed() {
        // given
        String cardBinAndInn = "00000000";
        // when
        CardSchemeDTO cardScheme = null;
        try{
            cardScheme =  cardSchemeService.getCardScheme(cardBinAndInn);
        }catch (Exception e){e.printStackTrace();}
        // then
        Assert.assertTrue("CardScheme will be null",cardScheme==null);

    }

    @Test
    public void whenValidCardBinWithInnIsPassed() {
        // given
        String cardBinAndInn = "45717360";
        // when
        CardSchemeDTO cardScheme = null;
        try{
            cardScheme =  cardSchemeService.getCardScheme(cardBinAndInn);
        }catch (Exception e){e.printStackTrace();}
        // then
        Assert.assertTrue("CardScheme will not be null",cardScheme!=null);
        Assert.assertTrue("Card Bank is present ",cardScheme.getBank()!=null);
        Assert.assertTrue("Card Scheme is present ",cardScheme.getScheme()!=null);
        Assert.assertTrue("Card type is present ",cardScheme.getType()!=null);
    }

    @Test
    public void whenInvalidInputIsUsedForGetHitCount() {
        /// given
        int start  = 0;
        int limit  = 0;
        // when
        CardSchemeLogDTO cardSchemeLogDTO = null;
        try{
            cardSchemeLogDTO =  cardSchemeService.getHitCount(start,limit);
        }catch (Exception e){e.printStackTrace();}
        // then
        Assert.assertTrue("CardScheme will be null",cardSchemeLogDTO==null);
    }

    @Test
    public void whenValidInputIsUsedForGetHitCount() {
        /// given
        int start  = 1;
        int limit  = 1;
        // when
        CardSchemeLogDTO cardSchemeLogDTO = null;
        try{
            cardSchemeLogDTO =  cardSchemeService.getHitCount(start,limit);
        }catch (Exception e){
            e.printStackTrace();
        }
        // then
        Assert.assertTrue("CardScheme will not be null",cardSchemeLogDTO!=null);
    }

    @Test
    public void kafkaMessageTest() {
        /// given
        Message message  = new Message("Mint","VISA","DEBIT");
        boolean messageIsSent = false;
        // when
        CardSchemeLogDTO cardSchemeLogDTO = null;
        try{
            kafkaService.produceMessage(message);
            messageIsSent =true;
        }catch (Exception e){e.printStackTrace();}
        // then
        Assert.assertTrue("CardScheme will be null",messageIsSent==true);
    }
}
