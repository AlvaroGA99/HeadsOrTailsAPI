package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.Match;
import com.ironhack.HeadsOrTailsAPI.models.RankedMatch;
import com.ironhack.HeadsOrTailsAPI.models.RegularMatch;
import com.ironhack.HeadsOrTailsAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RankedMatchRepository extends JpaRepository<RankedMatch,Long> {

    List<RankedMatch> findByHeadsUserAndHeadsWinner(User user, boolean headsWinner);
    List<RankedMatch> findByTailsUserAndHeadsWinner(User user,boolean headsWinner);
    List<RankedMatch> findByHeadsUser(User user);
    List<RankedMatch> findByTailsUser(User user);
    List<RankedMatch> findByHeadsUserAndDate(User user,Date date);
    List<RankedMatch> findByTailsUserAndDate(User user, Date date);
    List<RankedMatch> findByHeadsUserAndDateBetween(User user,Date startDate, Date endDate);
    List<RankedMatch> findByTailsUserAndDateBetween(User user, Date startDate, Date endDate);

    List<RankedMatch> findByHeadsUserAndTailsUser(User user,User user2);
    List<RankedMatch> findByHeadsUserAndTailsUserAndDate(User user, User user2,Date date);
    List<RankedMatch> findByHeadsUserAndTailsUserAndDateBetween(User user, User user2,Date startDate, Date endDate);

    List<RankedMatch> findByDate(Date date);
    List<RankedMatch> findByDateBetween(Date startDate, Date endDate);
    List<RankedMatch> findByHeadsBet(int headsBet);
    List<RankedMatch> findByHeadsBetBetween(int startHeadsBet, int endHeadsBet);
    List<RankedMatch> findByTailsBet(int tailsBet);
    List<RankedMatch> findByTailsBetBetween(int startTailsBet, int endTailsBet);
    List<RankedMatch> findByHeadsWinner(boolean headsWinner);
    List<RankedMatch> findByEloRating(int eloRating);
    List<RankedMatch> findByIsPromotionMatch(boolean isPromotionMatch);
}
