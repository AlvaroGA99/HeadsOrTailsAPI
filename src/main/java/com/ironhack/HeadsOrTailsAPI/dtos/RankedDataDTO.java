package com.ironhack.HeadsOrTailsAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankedDataDTO {
    private int eloRating;
    private boolean isPromotionMatch;
}
