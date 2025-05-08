package com.ironhack.HeadsOrTailsAPI.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryCRUDTests {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;
}
