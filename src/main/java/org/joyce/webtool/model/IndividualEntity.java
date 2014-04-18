package org.joyce.webtool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by junyan Zhang on 14-4-17.
 */
@Entity
@Table(name = "individual", schema = "", catalog = "")
public class IndividualEntity extends UserEntity {
    @JsonIgnore
    @OneToMany(mappedBy = "individualEntity", fetch = FetchType.EAGER, cascade = {CascadeType.ALL} )
    private List<CardEntity> cardList = new ArrayList<>();

    public List<CardEntity> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardEntity> cardList) {
        this.cardList = cardList;
    }
}
