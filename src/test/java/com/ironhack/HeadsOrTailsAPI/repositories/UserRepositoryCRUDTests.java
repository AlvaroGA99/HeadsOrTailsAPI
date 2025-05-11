package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.ERole;
import com.ironhack.HeadsOrTailsAPI.models.Role;
import com.ironhack.HeadsOrTailsAPI.models.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
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
        role = roleRepository.save(role);

        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        HashSet<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);
        user.setCoins(100);
        user.setElo(1200);


        user = userRepository.save(user);
    }



    @Test
    void testSaveUser() {
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getUsername());
        assertNotNull(savedUser.getPassword());
        assertNotNull(savedUser.getRoles());

        assertEquals(savedUser.getId(),user.getId());
        assertEquals(savedUser.getUsername(),user.getUsername());
        assertEquals(savedUser.getPassword(),user.getPassword());
        assertEquals(savedUser.getCoins(),user.getCoins());
        assertEquals(savedUser.getElo(),user.getElo());
        assertEquals(savedUser.getRoles(),user.getRoles());

    }

    @Test
    void testFindAll(){
        List<User> users = userRepository.findAll();
        assertNotNull(users);
        assertFalse(users.isEmpty());

    }

    @Test
    @Transactional
    void testFindByID(){
        Optional<User> optionalUser = userRepository.findById(user.getId());
        assertTrue(optionalUser.isPresent());
        assertNotNull(optionalUser.get().getId());
        assertNotNull(optionalUser.get().getUsername());
        assertNotNull(optionalUser.get().getPassword());
        assertNotNull(optionalUser.get().getRoles());

        assertEquals(optionalUser.get().getId(),user.getId());
        assertEquals(optionalUser.get().getUsername(),user.getUsername());
        assertEquals(optionalUser.get().getPassword(),user.getPassword());
        assertEquals(optionalUser.get().getCoins(),user.getCoins());
        assertEquals(optionalUser.get().getElo(),user.getElo());
        assertEquals(optionalUser.get().getRoles(),user.getRoles());


    }

    @Test
    void testFindByUsername(){
        fail("Not yet implemented");
    }

    @Test
    void testFindByElo(){
        fail("Not yet implemented");
    }

    @Test
    void testFindByCoins(){
        fail("Not yet implemented");
    }

    @Test
    void testFindByRole(){
        fail("Not yet implemented");
    }

    @Test
    void testDeleteById(){
        fail("Not yet implemented");
    }

    @Test
    void testDeleteAll(){
        fail("Not yet implemented");
    }

    @Test
    void testUpdate(){
        fail("Not yet implemented");
    }
}
