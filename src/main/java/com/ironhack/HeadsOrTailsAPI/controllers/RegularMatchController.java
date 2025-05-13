package com.ironhack.HeadsOrTailsAPI.controllers;

import com.ironhack.HeadsOrTailsAPI.models.Match;
import com.ironhack.HeadsOrTailsAPI.models.RegularMatch;
import com.ironhack.HeadsOrTailsAPI.models.User;
import com.ironhack.HeadsOrTailsAPI.repositories.RegularMatchRepository;
import com.ironhack.HeadsOrTailsAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/regularMatches")
public class RegularMatchController {

    @Autowired
    private RegularMatchRepository regularMatchRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/public")
    public List<RegularMatch> getAllMatches() {
        return regularMatchRepository.findAll();
    }

    @GetMapping("/public/{id}")
    public RegularMatch getMatchById(@PathVariable Long id) {
        return regularMatchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND){});
    }

    @GetMapping(value = "/public", params = {"headsUser", "headsWinner"})
    List<RegularMatch> findByHeadsUserAndHeadsWinner(@RequestParam(name = "headsUser") String headsUser, @RequestParam(name = "headsWinner") boolean headsWinner){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return regularMatchRepository.findByHeadsUserAndHeadsWinner(user.get(), headsWinner);
    }

    @GetMapping(value = "/public", params = {"tailsUser", "headsWinner"})
    List<RegularMatch> findByTailsUserAndHeadsWinner(@RequestParam(name = "") String tailsUser, @RequestParam(name = "headsWinner") boolean headsWinner){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return regularMatchRepository.findByTailsUserAndHeadsWinner(user.get(), headsWinner);
    }
    @GetMapping(value = "/public", params = {"headsUser"})
    List<RegularMatch> findByHeadsUser(@RequestParam(name = "headsUser") String headsUser){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return regularMatchRepository.findByHeadsUser(user.get());
    }

    @GetMapping(value = "/public", params = {"tailsUser"})
    List<RegularMatch> findByTailsUser(@RequestParam(name = "tailsUser") String tailsUser){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return regularMatchRepository.findByTailsUser(user.get());
    }

    @GetMapping(value = "/public", params = {"headsUser", "date"})
    List<RegularMatch> findByHeadsUserAndDate(@RequestParam(name = "headsUser") String headsUser, @RequestParam(name = "date") Date date){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return regularMatchRepository.findByHeadsUserAndDate(user.get(), date);
    }
    @GetMapping(value = "/public", params = {"tailsUser", "date"})
    List<RegularMatch> findByTailsUserAndDate(@RequestParam(name = "tailsUser") String tailsUser, @RequestParam(name = "date") Date date){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return regularMatchRepository.findByTailsUserAndDate(user.get(), date);
    }
    @GetMapping(value = "/public", params = {"headsUser", "startDate", "endDate"})
    List<RegularMatch> findByHeadsUserAndDateBetween(@RequestParam(name = "headsUser") String headsUser,@RequestParam(name = "startDate") Date startDate, @RequestParam(name = "endDate") Date endDate){
        Optional<User> user = userRepository.findById(headsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return regularMatchRepository.findByHeadsUserAndDateBetween(user.get(), startDate, endDate);
    }
    @GetMapping(value = "/public", params = {"tailsUser", "startDate", "endDate"})
    List<RegularMatch> findByTailsUserAndDateBetween(@RequestParam(name = "tailsUser")String tailsUser,@RequestParam(name = "startDate") Date startDate, @RequestParam(name = "endDate")Date endDate){
        Optional<User> user = userRepository.findById(tailsUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return regularMatchRepository.findByTailsUserAndDateBetween(user.get(), startDate, endDate);
    }


    @GetMapping(value = "/public", params = {"headsUser", "tailsUser"})
    List<RegularMatch> findByHeadsUserAndTailsUser(@RequestParam(name = "headsUser")String headsUser,@RequestParam(name = "tailsUser")String tailsUser){
        Optional<User> user = userRepository.findById(headsUser);
        Optional<User> user2 = userRepository.findById(tailsUser);
        if (user.isEmpty() || user2.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return regularMatchRepository.findByHeadsUserAndTailsUser(user.get(), user2.get());
    }
    @GetMapping(value = "/public", params = {"headsUser", "tailsUser", "date"})
    List<RegularMatch> findByHeadsUserAndTailsUserAndDate(@RequestParam(name = "headsUser") String headsUser, @RequestParam(name = "tailsUser")String tailsUser,@RequestParam(name = "date")Date date){
        Optional<User> user = userRepository.findById(headsUser);
        Optional<User> user2 = userRepository.findById(tailsUser);
        if (user.isEmpty() || user2.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return regularMatchRepository.findByHeadsUserAndTailsUserAndDate(user.get(), user2.get(), date);
    }
    @GetMapping(value = "/public", params = {"headsUser", "tailsUser", "startDate", "endDate"})
    List<RegularMatch> findByHeadsUserAndTailsUserAndDateBetween(@RequestParam(name = "headsUser")String headsUser, @RequestParam(name = "tailsUser")String tailsUser,@RequestParam(name = "startDate")Date startDate, @RequestParam(name = "endDate")Date endDate){
        Optional<User> user = userRepository.findById(headsUser);
        Optional<User> user2 = userRepository.findById(tailsUser);
        if (user.isEmpty() || user2.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {};
        }
        return regularMatchRepository.findByHeadsUserAndTailsUserAndDateBetween(user.get(), user2.get(), startDate, endDate);
    }
    @GetMapping(value = "/public", params = {"date"})
    List<RegularMatch> findByDate(@RequestParam(name = "date")Date date){
        return regularMatchRepository.findByDate(date);
    }
    @GetMapping(value = "/public", params = {"startDate", "endDate"})
    List<RegularMatch> findByDateBetween(@RequestParam(name = "startDate")Date startDate, @RequestParam(name = "endDate")Date endDate){
        return regularMatchRepository.findByDateBetween(startDate, endDate);
    }
    @GetMapping(value = "/public", params = {"headsBet"})
    List<RegularMatch> findByHeadsBet(@RequestParam(name = "headsBet") int headsBet){
        return regularMatchRepository.findByHeadsBet(headsBet);
    }
    @GetMapping(value = "/public", params = {"startHeadsBet", "endHeadsBet"})
    List<RegularMatch> findByHeadsBetBetween(@RequestParam(name = "startHeadsBet")int startHeadsBet, @RequestParam(name = "endHeadsBet")int endHeadsBet){
        return regularMatchRepository.findByHeadsBetBetween(startHeadsBet, endHeadsBet);
    }
    @GetMapping(value = "/public", params = {"tailsBet"})
    List<RegularMatch> findByTailsBet(@RequestParam(name = "tailsBet")int tailsBet){
        return regularMatchRepository.findByTailsBet(tailsBet);
    }
    @GetMapping(value = "/public", params = {"startTailsBet", "endTailsBet"})
    List<RegularMatch> findByTailsBetBetween(@RequestParam(name = "startTailsBet")int startTailsBet, @RequestParam(name = "endTailsBet")int endTailsBet){
        return regularMatchRepository.findByTailsBetBetween(startTailsBet, endTailsBet);
    }
    @GetMapping(value = "/public", params = {"headsWinner"})
    List<RegularMatch> findByHeadsWinner(@RequestParam(name = "headsWinner")boolean headsWinner){
        return regularMatchRepository.findByHeadsWinner(headsWinner);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RegularMatch createRegularMatch(@RequestBody RegularMatch match) {
        if(match.getDate() == null){
            match.setDate(new Date());
        }
        return regularMatchRepository.save(match);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegularMatch updateMatch(@PathVariable Long id, @RequestBody RegularMatch match) {
        Optional<RegularMatch> existingMatch = regularMatchRepository.findById(id);
        if(existingMatch.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {
            };
        }
        match.setId(id);
        return regularMatchRepository.save(match);
    }
}
