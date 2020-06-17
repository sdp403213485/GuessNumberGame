package com.twschool.practice.api;

import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;
import com.twschool.practice.domain.RandomAnswerGenerator;
import com.twschool.practice.domain.User;
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

        @GetMapping("/OneWhetherWin")
        public int OneWhetherWon(@RequestParam String userAnswerString,@RequestParam User user){
        user.setTotalScore(0);
        RandomAnswerGenerator randomAnswerGenerator = new RandomAnswerGenerator();
        GuessNumberGame guessNumberGame = new GuessNumberGame(randomAnswerGenerator);
        List<String> userAnswer = Arrays.asList(userAnswerString.split(" "));
            guessNumberGame.guess(userAnswer);
            if (guessNumberGame.getStatus().equals(GameStatus.SUCCEED)){
                user.setTotalScore(user.getTotalScore()+3);
            }else if(guessNumberGame.getStatus().equals(GameStatus.FAILED) ) {
                user.setTotalScore(user.getTotalScore()-3);;
            }
        return user.getTotalScore();
    }

//    public int OneWhetherWon(@RequestParam String userAnswerString){
////        int  score = 0;
////        List<String> userAnswer = Arrays.asList(userAnswerString.split(" "));
////            guessNumberGame.guess(userAnswer);
////            if (guessNumberGame.getStatus().equals(GameStatus.SUCCEED)){
////                score = score +3;
////            }else if(guessNumberGame.getStatus().equals(GameStatus.FAILED) && guessNumberGame.getLeftTryTimes() == 0) {
////                score = score - 3;
////            }else{
////                score = score;
////            }
////        return score;
////    }

//    @GetMapping("/OneGameWhetherWon")
//    public int OneGameWhetherWon(@RequestParam User user,@RequestParam String guess){
//        RandomAnswerGenerator randomAnswerGenerator = new RandomAnswerGenerator();
//        GuessNumberGame guessNumberGame = new GuessNumberGame(randomAnswerGenerator);
//        for(int i = 0; i <= guessNumberGame.getMAX_TRY_TIMES(); i++){
//
//         }
//
//    }


}
