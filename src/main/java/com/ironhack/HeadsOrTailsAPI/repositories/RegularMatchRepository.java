package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.Match;
import com.ironhack.HeadsOrTailsAPI.models.RankedMatch;
import com.ironhack.HeadsOrTailsAPI.models.RegularMatch;
import com.ironhack.HeadsOrTailsAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RegularMatchRepository extends JpaRepository<RegularMatch,Long> {
    List<RegularMatch> findByHeadsUser(User user);
    List<RegularMatch> findByTailsUser(User user);
    List<RegularMatch> findByDate(Date date);
    List<RegularMatch> findByHeadsBet(int headsBet);
    List<RegularMatch> findByTailsBet(int tailsBet);
    List<RegularMatch> findByHeadsWinner(boolean headsWinner);
}
