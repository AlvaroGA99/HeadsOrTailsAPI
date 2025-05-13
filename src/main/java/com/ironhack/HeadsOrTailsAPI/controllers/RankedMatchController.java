package com.ironhack.HeadsOrTailsAPI.controllers;

import com.ironhack.HeadsOrTailsAPI.dtos.MatchDataDTO;
import com.ironhack.HeadsOrTailsAPI.dtos.RankedDataDTO;
import com.ironhack.HeadsOrTailsAPI.models.Match;
import com.ironhack.HeadsOrTailsAPI.models.RankedMatch;
import com.ironhack.HeadsOrTailsAPI.models.RankedMatch;
import com.ironhack.HeadsOrTailsAPI.models.User;
import com.ironhack.HeadsOrTailsAPI.repositories.RankedMatchRepository;
import com.ironhack.HeadsOrTailsAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rankedMatches")
public class RankedMatchController {


    @Autowired
    private RankedMatchRepository rankedMatchRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/public")
    public List<RankedMatch> getAllMatches() {
        return rankedMatchRepository.findAll();
    }

    @GetMapping("/public/{id}")
    public RankedMatch getMatchById(@PathVariable Long id) {
        return rankedMatchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND){});
    }

    @GetMapping(value = "/public", params = {"headsUser", "headsWinner"})
    List<RankedMatch> findByHeadsUserAndHeadsWinner(@RequestParam(name = "headsUser") String headsUser, @RequestParam(name = "headsWinner") boolean headsWinner){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return rankedMatchRepository.findByHeadsUserAndHeadsWinner(user.get(), headsWinner);
    }

    @GetMapping(value = "/public", params = {"tailsUser", "headsWinner"})
    List<RankedMatch> findByTailsUserAndHeadsWinner(@RequestParam(name = "") String tailsUser, @RequestParam(name = "headsWinner") boolean headsWinner){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return rankedMatchRepository.findByTailsUserAndHeadsWinner(user.get(), headsWinner);
    }
    @GetMapping(value = "/public", params = {"headsUser"})
    List<RankedMatch> findByHeadsUser(@RequestParam(name = "headsUser") String headsUser){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return rankedMatchRepository.findByHeadsUser(user.get());
    }

    @GetMapping(value = "/public", params = {"tailsUser"})
    List<RankedMatch> findByTailsUser(@RequestParam(name = "tailsUser") String tailsUser){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return rankedMatchRepository.findByTailsUser(user.get());
    }

    @GetMapping(value = "/public", params = {"headsUser", "date"})
    List<RankedMatch> findByHeadsUserAndDate(@RequestParam(name = "headsUser") String headsUser, @RequestParam(name = "date") Date date){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return rankedMatchRepository.findByHeadsUserAndDate(user.get(), date);
    }
    @GetMapping(value = "/public", params = {"tailsUser", "date"})
    List<RankedMatch> findByTailsUserAndDate(@RequestParam(name = "tailsUser") String tailsUser, @RequestParam(name = "date") Date date){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return rankedMatchRepository.findByTailsUserAndDate(user.get(), date);
    }
    @GetMapping(value = "/public", params = {"headsUser", "startDate", "endDate"})
    List<RankedMatch> findByHeadsUserAndDateBetween(@RequestParam(name = "headsUser") String headsUser,@RequestParam(name = "startDate") Date startDate, @RequestParam(name = "endDate") Date endDate){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return rankedMatchRepository.findByHeadsUserAndDateBetween(user.get(), startDate, endDate);
    }
    @GetMapping(value = "/public", params = {"tailsUser", "startDate", "endDate"})
    List<RankedMatch> findByTailsUserAndDateBetween(@RequestParam(name = "tailsUser")String tailsUser,@RequestParam(name = "startDate") Date startDate, @RequestParam(name = "endDate")Date endDate){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return rankedMatchRepository.findByTailsUserAndDateBetween(user.get(), startDate, endDate);
    }


    @GetMapping(value = "/public", params = {"headsUser", "tailsUser"})
    List<RankedMatch> findByHeadsUserAndTailsUser(@RequestParam(name = "headsUser")String headsUser,@RequestParam(name = "tailsUser")String tailsUser){
        Optional<User> user = userRepository.findById(headsUser);
        Optional<User> user2 = userRepository.findById(tailsUser);
        if (user.isEmpty() || user2.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return rankedMatchRepository.findByHeadsUserAndTailsUser(user.get(), user2.get());
    }
    @GetMapping(value = "/public", params = {"headsUser", "tailsUser", "date"})
    List<RankedMatch> findByHeadsUserAndTailsUserAndDate(@RequestParam(name = "headsUser") String headsUser, @RequestParam(name = "tailsUser")String tailsUser,@RequestParam(name = "date")Date date){
        Optional<User> user = userRepository.findById(headsUser);
        Optional<User> user2 = userRepository.findById(tailsUser);
        if (user.isEmpty() || user2.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return rankedMatchRepository.findByHeadsUserAndTailsUserAndDate(user.get(), user2.get(), date);
    }
    @GetMapping(value = "/public", params = {"headsUser", "tailsUser", "startDate", "endDate"})
    List<RankedMatch> findByHeadsUserAndTailsUserAndDateBetween(@RequestParam(name = "headsUser")String headsUser, @RequestParam(name = "tailsUser")String tailsUser,@RequestParam(name = "startDate")Date startDate, @RequestParam(name = "endDate")Date endDate){
        Optional<User> user = userRepository.findById(headsUser);
        Optional<User> user2 = userRepository.findById(tailsUser);
        if (user.isEmpty() || user2.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return rankedMatchRepository.findByHeadsUserAndTailsUserAndDateBetween(user.get(), user2.get(), startDate, endDate);
    }
    @GetMapping(value = "/public", params = {"date"})
    List<RankedMatch> findByDate(@RequestParam(name = "date")Date date){
        return rankedMatchRepository.findByDate(date);
    }
    @GetMapping(value = "/public", params = {"startDate", "endDate"})
    List<RankedMatch> findByDateBetween(@RequestParam(name = "startDate")Date startDate, @RequestParam(name = "endDate")Date endDate){
        return rankedMatchRepository.findByDateBetween(startDate, endDate);
    }
    @GetMapping(value = "/public", params = {"headsBet"})
    List<RankedMatch> findByHeadsBet(@RequestParam(name = "headsBet") int headsBet){
        return rankedMatchRepository.findByHeadsBet(headsBet);
    }
    @GetMapping(value = "/public", params = {"startHeadsBet", "endHeadsBet"})
    List<RankedMatch> findByHeadsBetBetween(@RequestParam(name = "startHeadsBet")int startHeadsBet, @RequestParam(name = "endHeadsBet")int endHeadsBet){
        return rankedMatchRepository.findByHeadsBetBetween(startHeadsBet, endHeadsBet);
    }
    @GetMapping(value = "/public", params = {"tailsBet"})
    List<RankedMatch> findByTailsBet(@RequestParam(name = "tailsBet")int tailsBet){
        return rankedMatchRepository.findByTailsBet(tailsBet);
    }
    @GetMapping(value = "/public", params = {"startTailsBet", "endTailsBet"})
    List<RankedMatch> findByTailsBetBetween(@RequestParam(name = "startTailsBet")int startTailsBet, @RequestParam(name = "endTailsBet")int endTailsBet){
        return rankedMatchRepository.findByTailsBetBetween(startTailsBet, endTailsBet);
    }
    @GetMapping(value = "/public", params = {"headsWinner"})
    List<RankedMatch> findByHeadsWinner(@RequestParam(name = "headsWinner")boolean headsWinner){
        return rankedMatchRepository.findByHeadsWinner(headsWinner);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RankedMatch createRankedMatch(@RequestBody RankedMatch match) {
        if(match.getDate() == null){
            match.setDate(new Date());
        }
        return rankedMatchRepository.save(match);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RankedMatch updateMatch(@PathVariable Long id, @RequestBody RankedMatch match) {
        Optional<RankedMatch> existingMatch = rankedMatchRepository.findById(id);
        if(existingMatch.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {
            };
        }
        match.setId(id);
        return rankedMatchRepository.save(match);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMatch(@PathVariable Long id, @RequestBody RankedDataDTO match) {
        Optional<RankedMatch> existingMatch = rankedMatchRepository.findById(id);
        if(existingMatch.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        existingMatch.get().setPromotionMatch(match.isPromotionMatch());
        existingMatch.get().setEloRating(match.getEloRating());
        rankedMatchRepository.save(existingMatch.get());
    }
}
