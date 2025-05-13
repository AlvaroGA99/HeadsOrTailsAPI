package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.Match;
import com.ironhack.HeadsOrTailsAPI.models.RankedMatch;
import com.ironhack.HeadsOrTailsAPI.models.RegularMatch;
import com.ironhack.HeadsOrTailsAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RegularMatchRepository extends JpaRepository<RegularMatch,Long> {
    List<RegularMatch> findByHeadsUserAndHeadsWinner(User user,boolean headsWinner);
    List<RegularMatch> findByTailsUserAndHeadsWinner(User user,boolean headsWinner);
    List<RegularMatch> findByHeadsUser(User user);
    List<RegularMatch> findByTailsUser(User user);
    List<RegularMatch> findByHeadsUserAndDate(User user,Date date);
    List<RegularMatch> findByTailsUserAndDate(User user, Date date);
    List<RegularMatch> findByHeadsUserAndDateBetween(User user,Date startDate, Date endDate);
    List<RegularMatch> findByTailsUserAndDateBetween(User user, Date startDate, Date endDate);

    List<RegularMatch> findByHeadsUserAndTailsUser(User user,User user2);
    List<RegularMatch> findByHeadsUserAndTailsUserAndDate(User user, User user2,Date date);
    List<RegularMatch> findByHeadsUserAndTailsUserAndDateBetween(User user, User user2,Date startDate, Date endDate);

    List<RegularMatch> findByDate(Date date);
    List<RegularMatch> findByDateBetween(Date startDate, Date endDate);
    List<RegularMatch> findByHeadsBet(int headsBet);
    List<RegularMatch> findByHeadsBetBetween(int startHeadsBet, int endHeadsBet);
    List<RegularMatch> findByTailsBet(int tailsBet);
    List<RegularMatch> findByTailsBetBetween(int startTailsBet, int endTailsBet);
    List<RegularMatch> findByHeadsWinner(boolean headsWinner);
}
