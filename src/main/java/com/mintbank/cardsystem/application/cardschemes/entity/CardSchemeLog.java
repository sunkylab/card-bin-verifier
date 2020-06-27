package com.mintbank.cardsystem.application.cardschemes.entity;

import com.mintbank.cardsystem.core.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Data
@Table(name = "card_scheme_req_log")
@Entity
public class CardSchemeLog extends AbstractEntity {

    private String cardBin;

    @Version
    protected int version = 1;

}
