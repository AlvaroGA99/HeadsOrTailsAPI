package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.Role;
import com.ironhack.HeadsOrTailsAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByElo(int elo);
    List<User> findByCoins(int coins);
    List<User> findByRoles(Role roles);

    @Query("SELECT DISTINCT u FROM User u JOIN u.headsMatches m WHERE m.date = :date")
    List<User> findByPlayedOnDate(@Param("date")String date);

    @Query("SELECT DISTINCT u FROM User u JOIN u.headsMatches m WHERE m.date BETWEEN :startDate AND :endDate")
    List<User> findByPlayedOnDateBetween(@Param("date")String startDate, @Param("endDate")String endDate);

    List<User> findByUsernameContaining(String username);

    @Query("SELECT DISTINCT u FROM User u JOIN u.headsMatches m WHERE m.date = :date AND u.username LIKE :username")
    List<User> findByPlayedOnDateAndUsername(@Param("date")String date, @Param("username")String username);

    @Query("SELECT DISTINCT u FROM User u JOIN u.headsMatches m WHERE m.date BETWEEN :startDate AND :endDate AND u.username LIKE :username")
    List<User> findByPlayedOnDateBetweenAndUsername(@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("username")String username);

}
