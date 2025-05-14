package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.ERole;
import com.ironhack.HeadsOrTailsAPI.models.Role;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class RoleRepositoryCRUDTests {

    @Autowired
    RoleRepository roleRepository;

    Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setName(ERole.ROLE_USER);
        roleRepository.save(role);
    }

    @AfterEach
    void tearDown() {
        roleRepository.delete(role);
    }


    @Test
    void testSaveRole() {
        role.setName(ERole.ROLE_ADMIN);
        Role r = roleRepository.save(role);
        assertNotNull(r);
        assertNotNull(r.getId());
        assertNotNull(r.getId());
    }

    @Test
    void testFindAll(){
        List<Role> roles = roleRepository.findAll();
        assertNotNull(roles);
        assertFalse(roles.isEmpty());
    }

    @Test
    void testFindByID(){
        Optional<Role> r = roleRepository.findById(role.getId());
        assertTrue(r.isPresent());
        assertNotNull(r.get().getName());
        assertNotNull(r.get().getId());

    }

    @Test
    void testFindByName(){
        Optional<Role> optionalRole = roleRepository.findByName(ERole.ROLE_USER);
        assertTrue(optionalRole.isPresent());
        assertNotNull(optionalRole.get().getName());
        assertNotNull(optionalRole.get().getId());
    }

    @Test
    void testDeleteById(){
        roleRepository.deleteById(role.getId());
        Optional<Role> r = roleRepository.findById(role.getId());
        assertFalse(r.isPresent());
    }

    @Test
    void testDeleteAll(){
        roleRepository.deleteAll();
        List<Role> roles = roleRepository.findAll();
        assertTrue(roles.isEmpty());
    }

    @Test
    void testUpdate(){
        Optional<Role> r = roleRepository.findById(role.getId());
        assertTrue(r.isPresent());
        assertNotNull(r.get().getId());
        assertNotNull(r.get().getName());

        Role updatedRole = r.get();
        updatedRole.setName(ERole.ROLE_ADMIN);
        updatedRole = roleRepository.save(updatedRole);

        assertNotNull(updatedRole);
        assertNotNull(updatedRole.getId());
        assertNotNull(updatedRole.getName());
        assertEquals(ERole.ROLE_ADMIN, updatedRole.getName());
    }



}
