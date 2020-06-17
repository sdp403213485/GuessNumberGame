package com.twschool.practice.api;

import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;
import com.twschool.practice.domain.RandomAnswerGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GameController {

    @GetMapping("/game")
    public Map<String, String> guess(@RequestParam String guess) {
        Map<String,String> map = new HashMap<>();
        map.put("input", guess);
        map.put("result", "4A0B");
        return map;
    }

//    @GetMapping("/FristWin")
//    public int FristWin(@RequestParam String guess){
//        RandomAnswerGenerator randomAnswerGenerator = new RandomAnswerGenerator();
//        GuessNumberGame guessNumberGame = new GuessNumberGame(randomAnswerGenerator);
//
//        return 1;
//    }

        @GetMapping("/GameWhetherWon")
    public int FristWin(@RequestParam String userAnswerString){
        int  score = 0;
        RandomAnswerGenerator randomAnswerGenerator = new RandomAnswerGenerator();
        GuessNumberGame guessNumberGame = new GuessNumberGame(randomAnswerGenerator);
        List<String> userAnswer = Arrays.asList(userAnswerString.split(" "));
        for(int i = 0 ; i<= guessNumberGame.getMAX_TRY_TIMES(); i++){
            guessNumberGame.guess(userAnswer);
            if (guessNumberGame.getStatus().equals(GameStatus.SUCCEED)){
                score = score +3;
            }else if(guessNumberGame.getStatus().equals(GameStatus.FAILED) && guessNumberGame.getLeftTryTimes() == 0) {
                score = score - 3;
            }
        }
        return score;
    }


}
