package com.ironhack.HeadsOrTailsAPI.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MatchRepositoryCRUDTests {

     @Autowired
     MatchRepository matchRepository;
     @Autowired
     UserRepository userRepository;

}
