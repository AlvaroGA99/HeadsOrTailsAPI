package com.ironhack.HeadsOrTailsAPI.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.HeadsOrTailsAPI.dtos.RankedDataDTO;
import com.ironhack.HeadsOrTailsAPI.models.Match;
import com.ironhack.HeadsOrTailsAPI.models.RankedMatch;
import com.ironhack.HeadsOrTailsAPI.models.RegularMatch;
import com.ironhack.HeadsOrTailsAPI.repositories.MatchRepository;
import com.ironhack.HeadsOrTailsAPI.repositories.RankedMatchRepository;
import com.ironhack.HeadsOrTailsAPI.repositories.RegularMatchRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
public class MatchEndpointsMockMVC {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private RegularMatchRepository regularMatchRepository;

    @Autowired
    private RankedMatchRepository rankedMatchRepository;

    RankedMatch rankedMatch;

    RegularMatch regularMatch;


    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
         regularMatch= new RegularMatch();
        regularMatch.setDate(new Date());
        regularMatch.setHeadsBet(100);
        regularMatch.setTailsBet(200);
        regularMatch.setHeadsWinner(true);
         rankedMatch= new RankedMatch();
        rankedMatch.setDate(new Date());
        rankedMatch.setHeadsBet(300);
        rankedMatch.setTailsBet(400);
        rankedMatch.setHeadsWinner(false);
        rankedMatch.setEloRating(1200);
        rankedMatch.setPromotionMatch(true);
        matchRepository.saveAll(List.of(regularMatch, rankedMatch));
    }

    @AfterEach
    void tearDown() {
        matchRepository.deleteAll();
    }

    @Test
    public void testGetAllMatches() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/matches/public"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();


        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsWinner\":true"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsWinner\":false"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsBet\":100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"tailsBet\":200"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsBet\":300"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"tailsBet\":400"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"eloRating\":1200"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"promotionMatch\":true"));


        //assertTrue(mvcResult.getResponse().getContentAsString().contains("Databases"));
    }

    @Test
    public void testGetMatchById() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/matches/public/" + regularMatch.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsWinner\":true"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsBet\":100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"tailsBet\":200"));

    }

    @Test
    public void testGetMatchWithRequestParam() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/matches/public?headsBet=100"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsWinner\":true"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsBet\":100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"tailsBet\":200"));
    }

    @Test
    public void testGetMatchByIdNotFound() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/matches/public/100"))
                .andExpect(status().isNotFound())
                .andReturn();
    }


    @Test
    public void testCreateRegularMatch() throws Exception{
        RegularMatch regularMatch2 = new RegularMatch();
        regularMatch2.setHeadsBet(100);
        regularMatch2.setTailsBet(200);
        regularMatch2.setHeadsWinner(true);
        String body = objectMapper.writeValueAsString(regularMatch2);
        MvcResult mvcResult = mockMvc.perform(post("/regularMatches")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsWinner\":true"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsBet\":100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"tailsBet\":200"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("\"date\":null"));
    }

    @Test
    public void testCreateRankedMatch() throws Exception{
        RankedMatch rankedMatch2 = new RankedMatch();
        rankedMatch2.setDate(new Date());
        rankedMatch2.setHeadsBet(100);
        rankedMatch2.setTailsBet(200);
        rankedMatch2.setHeadsWinner(true);
        rankedMatch2.setEloRating(1200);
        rankedMatch2.setPromotionMatch(true);
        String body = objectMapper.writeValueAsString(rankedMatch2);
        MvcResult mvcResult = mockMvc.perform(post("/rankedMatches")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsWinner\":true"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsBet\":100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"tailsBet\":200"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"eloRating\":1200"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"promotionMatch\":true"));
    }


    @Test
    public void testPutRegularMatch() throws Exception{
        RegularMatch regularMatch2 = new RegularMatch();
        regularMatch2.setHeadsBet(100);
        regularMatch2.setTailsBet(200);
        regularMatch2.setHeadsWinner(true);
        String body = objectMapper.writeValueAsString(regularMatch2);
        MvcResult mvcResult = mockMvc.perform(put("/regularMatches/" + regularMatch.getId())
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsWinner\":true"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"headsBet\":100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"tailsBet\":200"));


    }

    @Test
    public void testPutRegularMatchNotFound() throws Exception{
        RegularMatch regularMatch2 = new RegularMatch();
        regularMatch2.setHeadsBet(100);
        regularMatch2.setTailsBet(200);
        regularMatch2.setHeadsWinner(true);
        String body = objectMapper.writeValueAsString(regularMatch2);
        MvcResult mvcResult = mockMvc.perform(put("/regularMatches/7089")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isNotFound()).andReturn();


    }

    @Test
    public void testPatchRankedMatch() throws Exception{
        RankedDataDTO rankedDataDTO = new RankedDataDTO();
        rankedDataDTO.setEloRating(1300);
        rankedDataDTO.setPromotionMatch(false);

        String body = objectMapper.writeValueAsString(rankedDataDTO);
        MvcResult mvcResult = mockMvc.perform(patch("/rankedMatches/" + rankedMatch.getId())
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().isEmpty());

    }

    @Test
    public void testPatchRankedMatchNotFound() throws Exception{
        RankedDataDTO rankedDataDTO = new RankedDataDTO();
        rankedDataDTO.setEloRating(1300);
        rankedDataDTO.setPromotionMatch(false);

        String body = objectMapper.writeValueAsString(rankedDataDTO);
        MvcResult mvcResult = mockMvc.perform(patch("/rankedMatches/7089")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isNotFound()).andReturn();

    }


    @Test
    public void testDeleteMatch() throws Exception{
        MvcResult mvcResult = mockMvc.perform(delete("/matches/" + rankedMatch.getId()))
                .andExpect(status().isNoContent())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void testDeleteMatchNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/matches/7089"))
                .andExpect(status().isNotFound())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().isEmpty());
    }
}
