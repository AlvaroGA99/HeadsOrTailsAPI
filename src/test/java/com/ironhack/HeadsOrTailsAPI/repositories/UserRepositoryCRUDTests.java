package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.ERole;
import com.ironhack.HeadsOrTailsAPI.models.Role;
import com.ironhack.HeadsOrTailsAPI.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryCRUDTests {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    User user;
    Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setName(ERole.ROLE_USER);
        roleRepository.save(role);

        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setCoins(100);
        user.setRoles(Set.of(role));
        user.setElo(1200);


        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        roleRepository.delete(role);
        userRepository.delete(user);
    }


    @Test
    void testSaveUser() {

    }

    @Test
    void testFindAll(){

    }

    @Test
    void testFindByID(){


    }

    @Test
    void testFindByUsername(){

    }

    @Test
    void testFindByElo(){

    }

    @Test
    void testFindByCoins(){

    }

    @Test
    void testFindByRole(){

    }

    @Test
    void testDeleteById(){

    }

    @Test
    void testDeleteAll(){

    }

    @Test
    void testUpdate(){

    }
}
