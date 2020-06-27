package com.mintbank.cardsystem.application.cardschemes.repository;

import com.mintbank.cardsystem.application.cardschemes.entity.CardScheme;
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
public interface CardSchemeRepo extends JpaRepository<CardScheme, Long> {

    CardScheme findByCardBin(String cardBin_Inn);

}
