package com.twschool.practice.api;

import com.twschool.practice.domain.Answer;
import com.twschool.practice.domain.GuessNumberGame;
import com.twschool.practice.domain.RandomAnswerGenerator;
import com.twschool.practice.service.GameService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc

public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_guess_result() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/game")
                .contentType(MediaType.APPLICATION_JSON)
                .param("guess","1 2 3 4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.input").value( "1 2 3 4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("4A0B"));
    }

//    @Test
//    public void should_return_3_result() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/OneWhetherWin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .param("guess","1 2 3 4"))
//                .param( "user","1")
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.input").value( "1 2 3 4"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("4A0B"));
//    }

    @Test
    public void should_return_3_when_win_1() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/oneGuessByOneUser")
                .contentType(MediaType.APPLICATION_JSON)
                .param("totalPoints","0")
                .param("guess", "1 2 3 4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.input").value("1 2 3 4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(3));
    }



}
