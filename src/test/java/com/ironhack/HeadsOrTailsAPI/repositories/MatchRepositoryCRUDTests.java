package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.*;
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
public class MatchRepositoryCRUDTests {

     @Autowired
     MatchRepository matchRepository;
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
        tailsUser.setCoins(100);
        headsUser.setElo(1200);
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
        fail("Not yet implemented");

    }

    @Test
    void testFindAllRankedMatches(){
        fail("Not yet implemented");
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
        fail("Not yet implemented");

    }

    @Test
    void testFindByTailsId(){
        fail("Not yet implemented");
    }

    @Test
    void testFindByDate(){
        fail("Not yet implemented");
    }

    @Test
    void testFindByHeadsWinner(){
        fail("Not yet implemented");
    }

    @Test
    void testFindByHeadsBet(){
        fail("Not yet implemented");
    }

    @Test
    void testFindByTailsBet(){
        fail("Not yet implemented");
    }

    @Test
    void testFindByPromotionMatch(){
        fail("Not yet implemented");
    }

    @Test
    void testFindByEloRating(){
        fail("Not yet implemented");
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

        Match savedMatch = matchRepository.save(updatedMatch);
        assertEquals(savedMatch, updatedMatch);



    }



}
