package com.ironhack.HeadsOrTailsAPI.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.HeadsOrTailsAPI.dtos.UserCredentialDTO;
import com.ironhack.HeadsOrTailsAPI.dtos.UserNumericalDTO;
import com.ironhack.HeadsOrTailsAPI.models.*;
import com.ironhack.HeadsOrTailsAPI.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserEndpointsMockMVC {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    User user;

    User user1;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setCoins(100);
        user.setElo(1200);
        Optional<Role> userrole = roleRepository.findByName(ERole.ROLE_USER);
        if (userrole.isPresent()) {
            user.setRoles(List.of(userrole.get()));
        }
        user1 = new User();
        user1.setUsername("testUser1");
        user1.setPassword("testPassword1");
        user1.setCoins(200);
        user1.setElo(2200);
        Optional<Role> adminrole = roleRepository.findByName(ERole.ROLE_ADMIN);
        if (adminrole.isPresent()) {
            user1.setRoles(List.of(adminrole.get()));
        }
        user = userRepository.save(user);
        user1 = userRepository.save(user1);

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/users/public"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();


        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"username\":\"testUser\""));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"username\":\"testUser1\""));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"elo\":1200"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"elo\":2200"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"coins\":100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"coins\":200"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"name\":\"ROLE_USER\""));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"name\":\"ROLE_ADMIN\""));


    }

    @Test
    public void testGetUserByUsername() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/users/public/testUser"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"username\":\"testUser\""));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"elo\":1200"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"coins\":100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"name\":\"ROLE_USER\""));

    }

    @Test
    public void testGetUserByUsernameNotFound() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/users/public/testUserNotFound"))
                .andExpect(status().isNotFound())
                .andReturn();

    }

    @Test
    public void testGetUserWithRequestParams() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/users/public")
                        .param("elo", "1200"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"username\":\"testUser\""));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"elo\":1200"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"coins\":100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"name\":\"ROLE_USER\""));

    }

    @Test
    public void testCreateUser() throws Exception{
        User user = new User();
        user.setUsername("testUser2");
        user.setPassword("testPassword2");
        user.setCoins(300);
        user.setElo(3200);
        Optional<Role> userrole = roleRepository.findByName(ERole.ROLE_USER);
        if (userrole.isPresent()) {
            user.setRoles(List.of(userrole.get()));
        }

        MvcResult mvcResult = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"username\":\"testUser2\""));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"elo\":3200"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"coins\":300"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"name\":\"ROLE_USER\""));

    }

    @Test
    public void testPutUser() throws Exception{
        User user4 = new User();
        user4.setUsername("testUser");
        user4.setPassword("testPassword");
        user4.setCoins(100);
        user4.setElo(1200);
        Optional<Role> userrole = roleRepository.findByName(ERole.ROLE_USER);
        if (userrole.isPresent()) {
            user4.setRoles(List.of(userrole.get()));
        }

        String body = objectMapper.writeValueAsString(user4);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/users/" + user4.getUsername())
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"username\":\"testUser\""));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"elo\":1200"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"coins\":100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"name\":\"ROLE_USER\""));

    }

    @Test
    public void testPutUserNotFound() throws Exception{

        User user4 = new User();
        user4.setUsername("testUser");
        user4.setPassword("testPassword");
        user4.setCoins(100);
        user4.setElo(1200);
        Optional<Role> userrole = roleRepository.findByName(ERole.ROLE_USER);
        if (userrole.isPresent()) {
            user4.setRoles(List.of(userrole.get()));
        }

        String body = objectMapper.writeValueAsString(user4);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/users/userNotFound")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isNotFound()).andReturn();
    }


    @Test
    public void testPatchUser() throws Exception{
        UserNumericalDTO userNumericalDTO = new UserNumericalDTO();
        userNumericalDTO.setCoins(100);
        userNumericalDTO.setElo(1200);

        String body = objectMapper.writeValueAsString(userNumericalDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/users/" + user.getUsername()+"/updateNumericals")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isOk()).andReturn();


    }

    @Test
    public void testPatchUserNotFound() throws Exception{
        UserNumericalDTO userNumericalDTO = new UserNumericalDTO();
        userNumericalDTO.setCoins(100);
        userNumericalDTO.setElo(1200);

        String body = objectMapper.writeValueAsString(userNumericalDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/users/userNotFound/updateNumericals")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isNotFound()).andReturn();

    }

    @Test
    public void testPatchUserBadRequest() throws Exception{
        UserCredentialDTO userCredentialDTO = new UserCredentialDTO();

        String body = objectMapper.writeValueAsString(userCredentialDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/users/" + user.getUsername()+"/updatePassword")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isBadRequest()).andReturn();


    }

    @Test
    public void testDeleteUser() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + user.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().isEmpty());
        assertTrue(userRepository.findById(user.getUsername()).isEmpty());

    }

    @Test
    public void testDeleteUserNotFound() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/users/userNotFound")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().isEmpty());
        assertTrue(userRepository.findById("userNotFound").isEmpty());

    }
}
