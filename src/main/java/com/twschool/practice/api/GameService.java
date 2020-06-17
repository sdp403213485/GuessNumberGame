package com.twschool.practice.api;

import com.twschool.practice.domain.GuessNumberGame;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class GameService {
    private GuessNumberGame guessNumberGame;

    public GameService() {
    }

    public GameService(GuessNumberGame guessNumberGame) {
        this.guessNumberGame = guessNumberGame;
    }

    public String guess(String userAnswerString) {
        List<String> userAnswer = Arrays.asList(userAnswerString.split(" "));
        return guessNumberGame.guess(userAnswer);

    }


}
