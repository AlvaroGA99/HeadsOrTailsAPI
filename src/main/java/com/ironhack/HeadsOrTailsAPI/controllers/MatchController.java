package com.ironhack.HeadsOrTailsAPI.controllers;

import com.ironhack.HeadsOrTailsAPI.dtos.MatchDataDTO;
import com.ironhack.HeadsOrTailsAPI.models.Match;
import com.ironhack.HeadsOrTailsAPI.models.RegularMatch;
import com.ironhack.HeadsOrTailsAPI.models.User;
import com.ironhack.HeadsOrTailsAPI.repositories.MatchRepository;
import com.ironhack.HeadsOrTailsAPI.repositories.RegularMatchRepository;
import com.ironhack.HeadsOrTailsAPI.repositories.UserRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private RegularMatchRepository matchRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/public")
    public List<RegularMatch> getAllMatches() {
        return matchRepository.findAll();
    }

    @GetMapping("/public/{id}")
    public RegularMatch getMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new HttpStatusCodeException(HttpStatus.NOT_FOUND){});
    }

    @GetMapping(value = "/public", params = {"headsUser", "headsWinner"})
    List<RegularMatch> findByHeadsUserAndHeadsWinner(@RequestParam(name = "headsUser") String headsUser, @RequestParam(name = "headsWinner") boolean headsWinner){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUserAndHeadsWinner(user.get(), headsWinner);
    }

    @GetMapping(value = "/public", params = {"tailsUser", "headsWinner"})
    List<RegularMatch> findByTailsUserAndHeadsWinner(@RequestParam(name = "") String tailsUser,@RequestParam(name = "headsWinner") boolean headsWinner){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByTailsUserAndHeadsWinner(user.get(), headsWinner);
    }
    @GetMapping(value = "/public", params = {"headsUser"})
    List<RegularMatch> findByHeadsUser(@RequestParam(name = "headsUser") String headsUser){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUser(user.get());
    }

    @GetMapping(value = "/public", params = {"tailsUser"})
    List<RegularMatch> findByTailsUser(@RequestParam(name = "tailsUser") String tailsUser){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByTailsUser(user.get());
    }

    @GetMapping(value = "/public", params = {"headsUser", "date"})
    List<RegularMatch> findByHeadsUserAndDate(@RequestParam(name = "headsUser") String headsUser, @RequestParam(name = "date") Date date){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUserAndDate(user.get(), date);
    }
    @GetMapping(value = "/public", params = {"tailsUser", "date"})
    List<RegularMatch> findByTailsUserAndDate(@RequestParam(name = "tailsUser") String tailsUser, @RequestParam(name = "date") Date date){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByTailsUserAndDate(user.get(), date);
    }
    @GetMapping(value = "/public", params = {"headsUser", "startDate", "endDate"})
    List<RegularMatch> findByHeadsUserAndDateBetween(@RequestParam(name = "headsUser") String headsUser,@RequestParam(name = "startDate") Date startDate, @RequestParam(name = "endDate") Date endDate){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUserAndDateBetween(user.get(), startDate, endDate);
    }
    @GetMapping(value = "/public", params = {"tailsUser", "startDate", "endDate"})
    List<RegularMatch> findByTailsUserAndDateBetween(@RequestParam(name = "tailsUser")String tailsUser,@RequestParam(name = "startDate") Date startDate, @RequestParam(name = "endDate")Date endDate){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByTailsUserAndDateBetween(user.get(), startDate, endDate);
    }


    @GetMapping(value = "/public", params = {"headsUser", "tailsUser"})
    List<RegularMatch> findByHeadsUserAndTailsUser(@RequestParam(name = "headsUser")String headsUser,@RequestParam(name = "tailsUser")String tailsUser){
        Optional<User> user = userRepository.findById(headsUser);
        Optional<User> user2 = userRepository.findById(tailsUser);
        if (user.isEmpty() || user2.isEmpty()) {
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUserAndTailsUser(user.get(), user2.get());
    }
    @GetMapping(value = "/public", params = {"headsUser", "tailsUser", "date"})
    List<RegularMatch> findByHeadsUserAndTailsUserAndDate(@RequestParam(name = "headsUser") String headsUser, @RequestParam(name = "tailsUser")String tailsUser,@RequestParam(name = "date")Date date){
        Optional<User> user = userRepository.findById(headsUser);
        Optional<User> user2 = userRepository.findById(tailsUser);
        if (user.isEmpty() || user2.isEmpty()) {
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUserAndTailsUserAndDate(user.get(), user2.get(), date);
    }
    @GetMapping(value = "/public", params = {"headsUser", "tailsUser", "startDate", "endDate"})
    List<RegularMatch> findByHeadsUserAndTailsUserAndDateBetween(@RequestParam(name = "headsUser")String headsUser, @RequestParam(name = "tailsUser")String tailsUser,@RequestParam(name = "startDate")Date startDate, @RequestParam(name = "endDate")Date endDate){
        Optional<User> user = userRepository.findById(headsUser);
        Optional<User> user2 = userRepository.findById(tailsUser);
        if (user.isEmpty() || user2.isEmpty()) {
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {};
        }
        return matchRepository.findByHeadsUserAndTailsUserAndDateBetween(user.get(), user2.get(), startDate, endDate);
    }
    @GetMapping(value = "/public", params = {"date"})
    List<RegularMatch> findByDate(@RequestParam(name = "date")Date date){
        return matchRepository.findByDate(date);
    }
    @GetMapping(value = "/public", params = {"startDate", "endDate"})
    List<RegularMatch> findByDateBetween(@RequestParam(name = "startDate")Date startDate, @RequestParam(name = "endDate")Date endDate){
        return matchRepository.findByDateBetween(startDate, endDate);
    }
    @GetMapping(value = "/public", params = {"headsBet"})
    List<RegularMatch> findByHeadsBet(@RequestParam(name = "headsBet") int headsBet){
        return matchRepository.findByHeadsBet(headsBet);
    }
    @GetMapping(value = "/public", params = {"startHeadsBet", "endHeadsBet"})
    List<RegularMatch> findByHeadsBetBetween(@RequestParam(name = "startHeadsBet")int startHeadsBet, @RequestParam(name = "endHeadsBet")int endHeadsBet){
        return matchRepository.findByHeadsBetBetween(startHeadsBet, endHeadsBet);
    }
    @GetMapping(value = "/public", params = {"tailsBet"})
    List<RegularMatch> findByTailsBet(@RequestParam(name = "tailsBet")int tailsBet){
        return matchRepository.findByTailsBet(tailsBet);
    }
    @GetMapping(value = "/public", params = {"startTailsBet", "endTailsBet"})
    List<RegularMatch> findByTailsBetBetween(@RequestParam(name = "startTailsBet")int startTailsBet, @RequestParam(name = "endTailsBet")int endTailsBet){
        return matchRepository.findByTailsBetBetween(startTailsBet, endTailsBet);
    }
    @GetMapping(value = "/public", params = {"headsWinner"})
    List<RegularMatch> findByHeadsWinner(@RequestParam(name = "headsWinner")boolean headsWinner){
        return matchRepository.findByHeadsWinner(headsWinner);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RegularMatch createMatch(@RequestBody RegularMatch match) {
        return matchRepository.save(match);
    }
//
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMatch(@PathVariable Long id, @RequestBody MatchDataDTO match) {
        Optional<RegularMatch> existingMatch = matchRepository.findById(id);
        if(existingMatch.isEmpty()){
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {
            };
        }
        if(match.getDate() == null){
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {
            };
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
        Optional<RegularMatch> existingMatch = matchRepository.findById(id);
        if(existingMatch.isEmpty()){
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {
            };
        }
        matchRepository.delete(existingMatch.get());
    }
}
