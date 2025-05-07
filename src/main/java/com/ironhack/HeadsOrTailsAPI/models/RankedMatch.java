package com.ironhack.HeadsOrTailsAPI.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("1")
@Data
public class RankedMatch extends Match{

    private int eloRating;

    private boolean isPromotionMatch;


    public RankedMatch(Long id, boolean headsWinner, int headsBet, int tailsBet,Date date, User headsUser, User tailsUser, int eloRating, boolean isPromotionMatch) {
        super(id, headsWinner, headsBet, tailsBet,date, headsUser, tailsUser);
        this.eloRating = eloRating;
        this.isPromotionMatch = isPromotionMatch;
    }




    public RankedMatch(){

    }
}
