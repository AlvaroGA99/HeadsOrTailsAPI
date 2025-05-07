package com.ironhack.HeadsOrTailsAPI.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("0")
@Data
public class RegularMatch extends Match{

    public RegularMatch(Long id, boolean headsWinner, int headsBet, int tailsBet, Date date, User headsUser, User tailsUser) {
        super(id, headsWinner, headsBet, tailsBet, date, headsUser, tailsUser);
    }

    public RegularMatch(){

    }
}
