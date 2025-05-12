package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.Match;
import com.ironhack.HeadsOrTailsAPI.models.RankedMatch;
import com.ironhack.HeadsOrTailsAPI.models.RegularMatch;
import com.ironhack.HeadsOrTailsAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RegularMatchRepository extends JpaRepository<RegularMatch,Long> {
    List<Match> findByHeadsUserAndHeadsWinner(User user,boolean headsWinner);
    List<Match> findByTailsUserAndHeadsWinner(User user,boolean headsWinner);
    List<Match> findByHeadsUser(User user);
    List<Match> findByTailsUser(User user);
    List<Match> findByHeadsUserAndDate(User user,Date date);
    List<Match> findByTailsUserAndDate(User user, Date date);
    List<Match> findByHeadsUserAndDateBetween(User user,Date startDate, Date endDate);
    List<Match> findByTailsUserAndDateBetween(User user, Date startDate, Date endDate);

    List<Match> findByHeadsUserAndTailsUser(User user,User user2);
    List<Match> findByHeadsUserAndTailsUserAndDate(User user, User user2,Date date);
    List<Match> findByHeadsUserAndTailsUserAndDateBetween(User user, User user2,Date startDate, Date endDate);

    List<Match> findByDate(Date date);
    List<Match> findByDateBetween(Date startDate, Date endDate);
    List<Match> findByHeadsBet(int headsBet);
    List<Match> findByHeadsBetBetween(int startHeadsBet, int endHeadsBet);
    List<Match> findByTailsBet(int tailsBet);
    List<Match> findByTailsBetBetween(int startTailsBet, int endTailsBet);
    List<Match> findByHeadsWinner(boolean headsWinner);
}
