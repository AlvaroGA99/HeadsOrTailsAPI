package com.ironhack.HeadsOrTailsAPI.controllers;

import com.ironhack.HeadsOrTailsAPI.dtos.MatchDataDTO;
import com.ironhack.HeadsOrTailsAPI.models.Match;
import com.ironhack.HeadsOrTailsAPI.models.RankedMatch;
import com.ironhack.HeadsOrTailsAPI.models.RegularMatch;
import com.ironhack.HeadsOrTailsAPI.models.User;
import com.ironhack.HeadsOrTailsAPI.repositories.MatchRepository;
import com.ironhack.HeadsOrTailsAPI.repositories.RankedMatchRepository;
import com.ironhack.HeadsOrTailsAPI.repositories.RegularMatchRepository;
import com.ironhack.HeadsOrTailsAPI.repositories.UserRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matches")
public class MatchController {


    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/public")
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @GetMapping("/public/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND){});
    }

    @GetMapping(value = "/public", params = {"headsUser", "headsWinner"})
    List<Match> findByHeadsUserAndHeadsWinner(@RequestParam(name = "headsUser") String headsUser, @RequestParam(name = "headsWinner") boolean headsWinner){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUserAndHeadsWinner(user.get(), headsWinner);
    }

    @GetMapping(value = "/public", params = {"tailsUser", "headsWinner"})
    List<Match> findByTailsUserAndHeadsWinner(@RequestParam(name = "") String tailsUser,@RequestParam(name = "headsWinner") boolean headsWinner){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByTailsUserAndHeadsWinner(user.get(), headsWinner);
    }
    @GetMapping(value = "/public", params = {"headsUser"})
    List<Match> findByHeadsUser(@RequestParam(name = "headsUser") String headsUser){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUser(user.get());
    }

    @GetMapping(value = "/public", params = {"tailsUser"})
    List<Match> findByTailsUser(@RequestParam(name = "tailsUser") String tailsUser){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByTailsUser(user.get());
    }

    @GetMapping(value = "/public", params = {"headsUser", "date"})
    List<Match> findByHeadsUserAndDate(@RequestParam(name = "headsUser") String headsUser, @RequestParam(name = "date") Date date){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUserAndDate(user.get(), date);
    }
    @GetMapping(value = "/public", params = {"tailsUser", "date"})
    List<Match> findByTailsUserAndDate(@RequestParam(name = "tailsUser") String tailsUser, @RequestParam(name = "date") Date date){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByTailsUserAndDate(user.get(), date);
    }
    @GetMapping(value = "/public", params = {"headsUser", "startDate", "endDate"})
    List<Match> findByHeadsUserAndDateBetween(@RequestParam(name = "headsUser") String headsUser,@RequestParam(name = "startDate") Date startDate, @RequestParam(name = "endDate") Date endDate){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUserAndDateBetween(user.get(), startDate, endDate);
    }
    @GetMapping(value = "/public", params = {"tailsUser", "startDate", "endDate"})
    List<Match> findByTailsUserAndDateBetween(@RequestParam(name = "tailsUser")String tailsUser,@RequestParam(name = "startDate") Date startDate, @RequestParam(name = "endDate")Date endDate){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByTailsUserAndDateBetween(user.get(), startDate, endDate);
    }


    @GetMapping(value = "/public", params = {"headsUser", "tailsUser"})
    List<Match> findByHeadsUserAndTailsUser(@RequestParam(name = "headsUser")String headsUser,@RequestParam(name = "tailsUser")String tailsUser){
        Optional<User> user = userRepository.findById(headsUser);
        Optional<User> user2 = userRepository.findById(tailsUser);
        if (user.isEmpty() || user2.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUserAndTailsUser(user.get(), user2.get());
    }
    @GetMapping(value = "/public", params = {"headsUser", "tailsUser", "date"})
    List<Match> findByHeadsUserAndTailsUserAndDate(@RequestParam(name = "headsUser") String headsUser, @RequestParam(name = "tailsUser")String tailsUser,@RequestParam(name = "date")Date date){
        Optional<User> user = userRepository.findById(headsUser);
        Optional<User> user2 = userRepository.findById(tailsUser);
        if (user.isEmpty() || user2.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUserAndTailsUserAndDate(user.get(), user2.get(), date);
    }
    @GetMapping(value = "/public", params = {"headsUser", "tailsUser", "startDate", "endDate"})
    List<Match> findByHeadsUserAndTailsUserAndDateBetween(@RequestParam(name = "headsUser")String headsUser, @RequestParam(name = "tailsUser")String tailsUser,@RequestParam(name = "startDate")Date startDate, @RequestParam(name = "endDate")Date endDate){
        Optional<User> user = userRepository.findById(headsUser);
        Optional<User> user2 = userRepository.findById(tailsUser);
        if (user.isEmpty() || user2.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUserAndTailsUserAndDateBetween(user.get(), user2.get(), startDate, endDate);
    }
    @GetMapping(value = "/public", params = {"date"})
    List<Match> findByDate(@RequestParam(name = "date")Date date){
        return matchRepository.findByDate(date);
    }
    @GetMapping(value = "/public", params = {"startDate", "endDate"})
    List<Match> findByDateBetween(@RequestParam(name = "startDate")Date startDate, @RequestParam(name = "endDate")Date endDate){
        return matchRepository.findByDateBetween(startDate, endDate);
    }
    @GetMapping(value = "/public", params = {"headsBet"})
    List<Match> findByHeadsBet(@RequestParam(name = "headsBet") int headsBet){
        return matchRepository.findByHeadsBet(headsBet);
    }
    @GetMapping(value = "/public", params = {"startHeadsBet", "endHeadsBet"})
    List<Match> findByHeadsBetBetween(@RequestParam(name = "startHeadsBet")int startHeadsBet, @RequestParam(name = "endHeadsBet")int endHeadsBet){
        return matchRepository.findByHeadsBetBetween(startHeadsBet, endHeadsBet);
    }
    @GetMapping(value = "/public", params = {"tailsBet"})
    List<Match> findByTailsBet(@RequestParam(name = "tailsBet")int tailsBet){
        return matchRepository.findByTailsBet(tailsBet);
    }
    @GetMapping(value = "/public", params = {"startTailsBet", "endTailsBet"})
    List<Match> findByTailsBetBetween(@RequestParam(name = "startTailsBet")int startTailsBet, @RequestParam(name = "endTailsBet")int endTailsBet){
        return matchRepository.findByTailsBetBetween(startTailsBet, endTailsBet);
    }
    @GetMapping(value = "/public", params = {"headsWinner"})
    List<Match> findByHeadsWinner(@RequestParam(name = "headsWinner")boolean headsWinner){
        return matchRepository.findByHeadsWinner(headsWinner);
    }


//
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMatch(@PathVariable Long id, @RequestBody MatchDataDTO match) {
        Optional<Match> existingMatch = matchRepository.findById(id);
        if(existingMatch.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(match.getDate() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(match.getHeadsUser() != null && !match.getHeadsUser().isEmpty() ){
            Optional<User> user = userRepository.findById(match.getHeadsUser());
            if (user.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
            }
            existingMatch.get().setHeadsUser(user.get());
        }
        if(match.getTailsUser() != null && !match.getTailsUser().isEmpty()){
            Optional<User> user = userRepository.findById(match.getTailsUser());
            if (user.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
            }
            existingMatch.get().setTailsUser(user.get());
        }
        existingMatch.get().setDate(match.getDate());
        existingMatch.get().setHeadsBet(match.getHeadsBet());
        existingMatch.get().setTailsBet(match.getTailsBet());
        existingMatch.get().setHeadsWinner(match.isHeadsWinner());
        matchRepository.save(existingMatch.get());
    }
//
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(@PathVariable Long id) {
        Optional<Match> existingMatch = matchRepository.findById(id);
        if(existingMatch.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {
            };
        }
        matchRepository.delete(existingMatch.get());
    }
}
