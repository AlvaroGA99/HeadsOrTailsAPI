package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.Match;
import com.ironhack.HeadsOrTailsAPI.models.RankedMatch;
import com.ironhack.HeadsOrTailsAPI.models.RegularMatch;
import com.ironhack.HeadsOrTailsAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByHeadsUser(User user);
    List<Match> findByTailsUser(User user);
    List<Match> findByDate(Date date);
    List<Match> findByHeadsBet(int headsBet);
    List<Match> findByTailsBet(int tailsBet);
    List<Match> findByHeadsWinner(boolean headsWinner);

}
