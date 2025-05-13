package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MatchRepositoryCRUDTests {

     @Autowired
     MatchRepository matchRepository;

     @Autowired
     RegularMatchRepository regularMatchRepository;

     @Autowired
        RankedMatchRepository rankedMatchRepository;

     @Autowired
     UserRepository userRepository;

     RegularMatch regularMatch;
     RankedMatch rankedMatch;
     User headsUser;
     User tailsUser;


    @BeforeEach
    void setUp() {
        headsUser = new User();
        tailsUser = new User();
        headsUser.setUsername("testUser1");
        tailsUser.setUsername("testUser2");
        headsUser.setPassword("testPassword1");
        tailsUser.setPassword("testPassword2");
        headsUser.setCoins(100);
        tailsUser.setCoins(1000);
        headsUser.setElo(12000);
        tailsUser.setElo(1200);

        headsUser = userRepository.save(headsUser);
        tailsUser = userRepository.save(tailsUser);
        regularMatch = new RegularMatch();
        rankedMatch = new RankedMatch();

        regularMatch.setHeadsUser(headsUser);
        regularMatch.setTailsUser(tailsUser);
        regularMatch.setHeadsWinner(true);
        regularMatch.setDate(new Date());
        regularMatch.setHeadsBet(100);
        regularMatch.setTailsBet(200);

        rankedMatch.setHeadsUser(headsUser);
        rankedMatch.setTailsUser(tailsUser);
        rankedMatch.setPromotionMatch(false);
        rankedMatch.setHeadsWinner(true);
        rankedMatch.setEloRating(1430);
        rankedMatch.setDate(new Date());
        regularMatch.setHeadsBet(100);
        regularMatch.setTailsBet(200);

        regularMatch = matchRepository.save(regularMatch);
        rankedMatch = matchRepository.save(rankedMatch);

    }

    @AfterEach
    void tearDown() {
        matchRepository.delete(regularMatch);
        matchRepository.delete(rankedMatch);
        userRepository.delete(headsUser);
        userRepository.delete(tailsUser);
    }


    @Test
    void testSaveMatch() {

        RegularMatch savedRegularMatch = matchRepository.save(regularMatch);
        RankedMatch savedRankedMatch = matchRepository.save(rankedMatch);

        //The primitive types cant be null because they always have default values

        assertNotNull(savedRegularMatch);
        assertNotNull(savedRegularMatch.getId());
        assertNotNull(savedRegularMatch.getHeadsUser());
        assertNotNull(savedRegularMatch.getTailsUser());
        assertNotNull(savedRegularMatch.getDate());

        assertNotNull(savedRankedMatch);
        assertNotNull(savedRankedMatch.getId());
        assertNotNull(savedRankedMatch.getHeadsUser());
        assertNotNull(savedRankedMatch.getTailsUser());
        assertNotNull(savedRankedMatch.getDate());


        assertEquals(savedRankedMatch,rankedMatch);
        assertEquals(savedRegularMatch,regularMatch);



    }

    @Test
    void testFindAll(){
        List<Match> matches = matchRepository.findAll();
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertTrue(matches.contains(regularMatch));
        assertTrue(matches.contains(rankedMatch));

    }

    @Test
    void testFindAllRegularMatches(){
        List<RegularMatch> matches = regularMatchRepository.findAll();
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertTrue(matches.contains(regularMatch));


    }

    @Test
    void testFindAllRankedMatches(){
        List<RankedMatch> matches = rankedMatchRepository.findAll();
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertTrue(matches.contains(rankedMatch));
    }

    @Test
    void testFindByID(){
        Optional<Match> match = matchRepository.findById(regularMatch.getId());
        assertTrue(match.isPresent());
        assertEquals(match.get(), regularMatch);

        Optional<Match> rankedMatchOptional = matchRepository.findById(rankedMatch.getId());
        assertTrue(rankedMatchOptional.isPresent());
        assertEquals(rankedMatchOptional.get(), rankedMatch);

    }

    @Test
    void testFindByHeadsId(){
        List<Match> matches = matchRepository.findByHeadsUser(headsUser);
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertEquals(headsUser.getUsername(), matches.get(0).getHeadsUser().getUsername());


    }

    @Test
    void testFindByTailsId(){
        List<Match> matches = matchRepository.findByTailsUser(tailsUser);
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertEquals(tailsUser.getUsername(), matches.get(0).getTailsUser().getUsername());
    }

    @Test
    void testFindByDate(){
        List<Match> matches = matchRepository.findByDate(regularMatch.getDate());
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertEquals(regularMatch.getDate(), matches.get(0).getDate());
    }

    @Test
    void testFindByHeadsWinner(){
        List<Match> matches = matchRepository.findByHeadsWinner(regularMatch.isHeadsWinner());
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertEquals(regularMatch.isHeadsWinner(), matches.get(0).isHeadsWinner());
    }

    @Test
    void testFindByHeadsBet(){
        List<Match> matches = matchRepository.findByHeadsBet(regularMatch.getHeadsBet());
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertEquals(regularMatch.getHeadsBet(), matches.get(0).getHeadsBet());
    }

    @Test
    void testFindByTailsBet(){
        List<Match> matches = matchRepository.findByTailsBet(regularMatch.getTailsBet());
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertEquals(regularMatch.getTailsBet(), matches.get(0).getTailsBet());
    }

    @Test
    void testFindByPromotionMatch(){
        List<RankedMatch> matches = rankedMatchRepository.findByIsPromotionMatch(rankedMatch.isPromotionMatch());
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertEquals(rankedMatch.isPromotionMatch(), matches.get(0).isPromotionMatch());
    }

    @Test
    void testFindByEloRating(){
        List<RankedMatch> matches = rankedMatchRepository.findByEloRating(rankedMatch.getEloRating());
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertEquals(rankedMatch.getEloRating(), matches.get(0).getEloRating());
    }

    @Test
    void testDeleteById(){
        matchRepository.delete(regularMatch);
        matchRepository.delete(rankedMatch);
        Optional<Match> regularMatchOptional = matchRepository.findById(regularMatch.getId());
        Optional<Match> rankedMatchOptional = matchRepository.findById(rankedMatch.getId());
        assertFalse(regularMatchOptional.isPresent());
        assertFalse(rankedMatchOptional.isPresent());


    }

    @Test
    void testDeleteAll(){
        matchRepository.deleteAll();
        List<Match> matches = matchRepository.findAll();
        assertTrue(matches.isEmpty());

    }

    @Test
    void testUpdate(){
        Optional<Match> matchOptional = matchRepository.findById(regularMatch.getId());
        assertTrue(matchOptional.isPresent());
        assertNotNull(matchOptional.get().getId());
        assertNotNull(matchOptional.get().getHeadsUser());
        assertNotNull(matchOptional.get().getTailsUser());
        assertNotNull(matchOptional.get().getDate());

        Match updatedMatch = matchOptional.get();
        updatedMatch.setHeadsWinner(false);
        updatedMatch.setHeadsBet(200);
        updatedMatch.setTailsBet(100);
        updatedMatch.setHeadsUser(tailsUser);
        updatedMatch.setTailsUser(headsUser);

        Match savedMatch = matchRepository.save(updatedMatch);
        assertEquals(savedMatch, updatedMatch);



    }



}
