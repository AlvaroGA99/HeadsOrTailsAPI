package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.RankedMatch;
import com.ironhack.HeadsOrTailsAPI.models.RegularMatch;
import com.ironhack.HeadsOrTailsAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RankedMatchRepository extends JpaRepository<RankedMatch,Long> {

    List<RankedMatch> findByHeadsUser(User user);
    List<RankedMatch> findByTailsUser(User user);
    List<RankedMatch> findByDate(Date date);
    List<RankedMatch> findByHeadsBet(int headsBet);
    List<RankedMatch> findByTailsBet(int tailsBet);
    List<RankedMatch> findByHeadsWinner(boolean headsWinner);
    List<RankedMatch> findByEloRating(int eloRating);
    List<RankedMatch> findByIsPromotionMatch(boolean isPromotionMatch);
}
