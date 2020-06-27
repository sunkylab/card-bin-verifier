package com.mintbank.cardsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"binlist.service.url=https://lookup.binlist.net",})
public class ApplicationTests {

    @TestConfiguration
    static class ApplicationTestsContextConfiguration {

    }




    @Test
    public void getCardSchemeTest() {
        // given

        // when

        // then
    }

    @Test
    public void whenInvalidCardBinWithInnIsPassed() {
        // given

        // when

        // then

    }

    @Test
    public void whenValidCardBinWithInnIsPassed() {
        // given

        // when

        // then
    }

    @Test
    public void getHitCountTest() {
        // given

        // when

        // then
    }

    @Test
    public void kafkaMessageTest() {
        // given

        // when

        // then
    }
}
