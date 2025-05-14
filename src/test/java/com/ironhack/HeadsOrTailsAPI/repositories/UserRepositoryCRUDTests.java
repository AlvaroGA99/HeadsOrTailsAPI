package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.ERole;
import com.ironhack.HeadsOrTailsAPI.models.Role;
import com.ironhack.HeadsOrTailsAPI.models.User;
import jakarta.transaction.Transactional;
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

        assertNotNull(savedUser.getUsername());
        assertNotNull(savedUser.getPassword());
        assertNotNull(savedUser.getRoles());

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
        Optional<User> optionalUser = userRepository.findById(user.getUsername());

        assertTrue(optionalUser.isPresent());
        assertNotNull(optionalUser.get().getUsername());
        assertNotNull(optionalUser.get().getPassword());
        assertNotNull(optionalUser.get().getRoles());

        assertEquals(optionalUser.get().getUsername(),user.getUsername());
        assertEquals(optionalUser.get().getPassword(),user.getPassword());
        assertEquals(optionalUser.get().getCoins(),user.getCoins());
        assertEquals(optionalUser.get().getElo(),user.getElo());
        assertEquals(optionalUser.get().getRoles(),user.getRoles());


    }


    @Test
    void testFindByElo(){
        List<User> users = userRepository.findByElo(user.getElo());
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertNotNull(users.get(0));

        assertNotNull(users.get(0).getUsername());
        assertNotNull(users.get(0).getPassword());
        assertNotNull(users.get(0).getRoles());

        assertEquals(users.get(0).getUsername(),user.getUsername());
        assertEquals(users.get(0).getPassword(),user.getPassword());
        assertEquals(users.get(0).getCoins(),user.getCoins());
        assertEquals(users.get(0).getElo(),user.getElo());
        assertEquals(users.get(0).getRoles(),user.getRoles());
    }

    @Test
    void testFindByCoins(){
        List<User> users = userRepository.findByCoins(user.getCoins());
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertNotNull(users.get(0));

        assertNotNull(users.get(0).getUsername());
        assertNotNull(users.get(0).getPassword());
        assertNotNull(users.get(0).getRoles());

        assertEquals(users.get(0).getUsername(),user.getUsername());
        assertEquals(users.get(0).getPassword(),user.getPassword());
        assertEquals(users.get(0).getCoins(),user.getCoins());
        assertEquals(users.get(0).getElo(),user.getElo());
        assertEquals(users.get(0).getRoles(),user.getRoles());
    }

    @Test
    void testFindByRole(){
        List<User> users = userRepository.findByRoles(role);
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertNotNull(users.get(0));

        assertNotNull(users.get(0).getUsername());
        assertNotNull(users.get(0).getPassword());
        assertNotNull(users.get(0).getRoles());

        assertEquals(users.get(0).getUsername(),user.getUsername());
        assertEquals(users.get(0).getPassword(),user.getPassword());
        assertEquals(users.get(0).getCoins(),user.getCoins());
        assertEquals(users.get(0).getElo(),user.getElo());
        assertEquals(users.get(0).getRoles(),user.getRoles());
    }

    @Test
    void testDeleteById(){
        userRepository.deleteById(user.getUsername());
        Optional<User> optionalUser = userRepository.findById(user.getUsername());
        assertFalse(optionalUser.isPresent());
    }

    @Test
    void testDeleteAll(){
        userRepository.deleteAll();
        List<User> users = userRepository.findAll();
        assertTrue(users.isEmpty());
    }

    @Test
    void testUpdate(){
        Optional<User> optionalUser = userRepository.findById(user.getUsername());
        assertTrue(optionalUser.isPresent());
        assertNotNull(optionalUser.get().getUsername());
        assertNotNull(optionalUser.get().getPassword());
        assertNotNull(optionalUser.get().getRoles());

        User updatedUser = optionalUser.get();
        updatedUser.setUsername("updatedUsername");
        updatedUser.setPassword("updatedPassword");
        updatedUser.setCoins(200);
        updatedUser.setElo(1300);
        updatedUser = userRepository.save(updatedUser);

        assertNotNull(updatedUser);
        assertNotNull(updatedUser.getUsername());
        assertNotNull(updatedUser.getPassword());
        assertNotNull(updatedUser.getRoles());

        assertEquals("updatedUsername", updatedUser.getUsername());
        assertEquals("updatedPassword", updatedUser.getPassword());
        assertEquals(200, updatedUser.getCoins());
        assertEquals(1300, updatedUser.getElo());

    }
}
