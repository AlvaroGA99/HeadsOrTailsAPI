package com.ironhack.HeadsOrTailsAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDataDTO {
    private boolean headsWinner;

    private int headsBet;

    private int tailsBet;

    private Date date;
}
