package com.ironhack.HeadsOrTailsAPI.controllers;
import com.ironhack.HeadsOrTailsAPI.dtos.UserCredentialDTO;
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
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/public/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable String username) {
        return userRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND) {
        });
    }

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public User getUserProfile() {
        return new User();
    }

    @GetMapping(value = "/public", params = {"elo"})
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByElo(@RequestParam(name="elo") int elo) {
        return userRepository.findByElo(elo);

    }

    @GetMapping(value = "/public", params = {"startElo", "endElo"})
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByEloBetween(@RequestParam(name="startElo") int startElo, @RequestParam(name="endElo") int endElo) {
       return userRepository.findByEloBetween(startElo,endElo);

    }

    @GetMapping(value = "/public", params = {"coins"})
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByCoins(@RequestParam(name="coins") int coins) {
        return userRepository.findByCoins(coins);

    }

    @GetMapping(value = "/public", params = {"startCoins", "endCoins"})
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByCoinsBetween(@RequestParam(name="startCoins") int startCoins, @RequestParam(name="endCoins") int endCoins) {
        return userRepository.findByCoinsBetween(startCoins,endCoins);

    }

    @GetMapping(value = "/public", params = {"role"})
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByRoles(@RequestParam(name="role") String role) {
        Optional<Role> optionalRole = roleRepository.findByName(ERole.valueOf(role));
        Role result_role;
        if(optionalRole.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            result_role = optionalRole.get();
        }
        return userRepository.findByRoles(result_role);

    }

    @GetMapping(params = {"date"})
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByPlayedOnDate(@RequestParam(name="date") String date) {
        return userRepository.findByPlayedOnDate(date);

    }

    @GetMapping(params = {"startDate", "endDate"})
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByPlayedOnDateBetween(@RequestParam(name="startDate") String startDate, @RequestParam(name="endDate") String endDate) {
        return userRepository.findByPlayedOnDateBetween(startDate,endDate);

    }

    @GetMapping(value = "/public", params = {"username"})
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByUsernameContaining(@RequestParam(name="username") String username) {
        return userRepository.findByUsernameContaining(username);

    }

    @GetMapping(params = {"date", "username"})
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByPlayedOnDateAndUsername(@RequestParam(name="date") String date, @RequestParam(name="username") String username) {
        return userRepository.findByPlayedOnDateAndUsername(date,username);

    }

    @GetMapping(params = {"startDate", "endDate", "username"})
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {
            };
        }
        user.setUsername(username);
        userRepository.save(user);
    }

    @PatchMapping("/{username}/updateNumericals")
    @ResponseStatus(HttpStatus.OK)
    public void updateNumericals(@RequestBody UserNumericalDTO dto, @PathVariable String username) {
        Optional<User> existingUser = userRepository.findById(username);
        if(existingUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {
            };
        }
        existingUser.get().setCoins(dto.getCoins());
        existingUser.get().setElo(dto.getElo());
        userRepository.save(existingUser.get());
    }

    @PatchMapping("/{username}/updatePassword")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@RequestBody UserCredentialDTO dto, @PathVariable String username) {
        Optional<User> existingUser = userRepository.findById(username);
        if(existingUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {
            };
        }

        if(dto.getPassword() == null || dto.getPassword().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        existingUser.get().setPassword(dto.getPassword());
        userRepository.save(existingUser.get());
    }


    //DELETE

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String username) {
        Optional<User> user = userRepository.findById(username);
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) {
            };
        }
        userRepository.deleteById(username);
    }

}
