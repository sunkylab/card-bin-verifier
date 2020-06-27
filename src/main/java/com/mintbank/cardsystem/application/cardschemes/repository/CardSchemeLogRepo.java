package com.mintbank.cardsystem.application.cardschemes.repository;

import com.mintbank.cardsystem.application.cardschemes.entity.CardSchemeLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Timmy
 * @date 11/6/2018
 */

@Repository
public interface CardSchemeLogRepo extends JpaRepository<CardSchemeLog, Long> {


    @Query(value = "select * from card_scheme_req_log ",nativeQuery = true)
    Page<CardSchemeLog> fetchRequestLogs(Pageable pageable);


    CardSchemeLog findByCardBin(String cardBin_Inn);


}
