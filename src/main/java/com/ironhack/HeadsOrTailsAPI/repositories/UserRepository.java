package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.Role;
import com.ironhack.HeadsOrTailsAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByElo(int elo);
    List<User> findByCoins(int coins);
    List<User> findByRoles(Role roles);

}
