package com.mintbank.cardsystem.application.cardschemes.entity;

import com.mintbank.cardsystem.core.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "card_scheme")
@Entity
public class CardScheme extends AbstractEntity {

    @Column(unique = true)
    private String cardBin;

    private String scheme;
    private String type;
    private String bank;

}
