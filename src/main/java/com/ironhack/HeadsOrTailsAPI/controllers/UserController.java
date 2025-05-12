package com.ironhack.HeadsOrTailsAPI.controllers;
import com.ironhack.HeadsOrTailsAPI.dtos.UserNumericalDTO;
import com.ironhack.HeadsOrTailsAPI.models.ERole;
import com.ironhack.HeadsOrTailsAPI.models.Role;
import com.ironhack.HeadsOrTailsAPI.models.User;
import com.ironhack.HeadsOrTailsAPI.repositories.RoleRepository;
import com.ironhack.HeadsOrTailsAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(String username) {
        return userRepository.findById(username).orElseThrow(() -> new HttpStatusCodeException(HttpStatus.NOT_FOUND) {
        });
    }

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public User getUserProfile() {
        return new User();
    }

    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByElo(@RequestParam(name="elo") int elo) {
        return userRepository.findByElo(elo);

    }

    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByEloBetween(@RequestParam(name="startElo") int startElo, @RequestParam(name="endElo") int endElo) {
       return userRepository.findByEloBetween(startElo,endElo);

    }

    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByCoins(@RequestParam(name="coins") int coins) {
        return userRepository.findByCoins(coins);

    }

    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByCoinsBetween(@RequestParam(name="startCoins") int startCoins, @RequestParam(name="endCoins") int endCoins) {
        return userRepository.findByCoinsBetween(startCoins,endCoins);

    }

    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByRoles(@RequestParam(name="role") String role) {
        Optional<Role> optionalRole = roleRepository.findByName(ERole.valueOf(role));
        Role result_role;
        if(optionalRole.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }else{
            result_role = optionalRole.get();
        }
        return userRepository.findByRoles(result_role);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByPlayedOnDate(@RequestParam(name="date") String date) {
        return userRepository.findByPlayedOnDate(date);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByPlayedOnDateBetween(@RequestParam(name="startDate") String startDate, @RequestParam(name="endDate") String endDate) {
        return userRepository.findByPlayedOnDateBetween(startDate,endDate);

    }

    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByUsernameContaining(@RequestParam(name="username") String username) {
        return userRepository.findByUsernameContaining(username);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByPlayedOnDateAndUsername(@RequestParam(name="date") String date, @RequestParam(name="username") String username) {
        return userRepository.findByPlayedOnDateAndUsername(date,username);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByPlayedOnDateBetweenAndUsername(@RequestParam(name="startDate") String startDate, @RequestParam(name="endDate") String endDate, @RequestParam(name="username") String username) {
        return userRepository.findByPlayedOnDateBetweenAndUsername(startDate,endDate,username);

    }

    // POST


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // PUT

    @PutMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable String username, @RequestBody User user) {
        Optional<User> existingUser = userRepository.findById(username);
        if(existingUser.isEmpty()){
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {
            };
        }
        user.setUsername(username);
        userRepository.save(user);
    }

    @PatchMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updateNumericals(@RequestBody UserNumericalDTO dto, @PathVariable String username) {
        Optional<User> existingUser = userRepository.findById(username);
        if(existingUser.isEmpty()){
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {
            };
        }
        existingUser.get().setCoins(dto.getCoins());
        existingUser.get().setElo(dto.getElo());
        userRepository.save(existingUser.get());
    }

    @PatchMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@RequestBody String password, @PathVariable String username) {
        Optional<User> existingUser = userRepository.findById(username);
        if(existingUser.isEmpty()){
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {
            };
        }
        existingUser.get().setPassword(password);
        userRepository.save(existingUser.get());
    }


    //DELETE

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String username) {
        Optional<User> user = userRepository.findById(username);
        if(user.isEmpty()){
            throw new HttpStatusCodeException(HttpStatus.NOT_FOUND) {
            };
        }
        userRepository.deleteById(username);
    }

}
