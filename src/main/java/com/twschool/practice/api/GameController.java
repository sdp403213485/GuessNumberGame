package com.twschool.practice.api;

import com.twschool.practice.domain.*;

import com.twschool.practice.service.CountPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GameController {
    @Autowired
    private CountPointsService countPointsService;

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
        user.setTotalPoints(0);
        RandomAnswerGenerator randomAnswerGenerator = new RandomAnswerGenerator();
        GuessNumberGame guessNumberGame = new GuessNumberGame(randomAnswerGenerator);
        List<String> userAnswer = Arrays.asList(userAnswerString.split(" "));
            guessNumberGame.guess(userAnswer);
            if (guessNumberGame.getStatus().equals(GameStatus.SUCCEED)){
                user.setTotalPoints(user.getTotalPoints()+3);
            }else if(guessNumberGame.getStatus().equals(GameStatus.FAILED) ) {
                user.setTotalPoints(user.getTotalPoints()-3);;
            }
        return user.getTotalPoints();
    }


    @GetMapping("/oneGuessByOneUser")
    public Map<String,Integer> oneGuessByOneUser( @RequestParam String guess){
        User user = new User();
        //user.setTotalPoints(0);
        List<String> userAnswerNumber = Arrays.asList(guess.split(" "));
        RandomAnswerGenerator randomAnswerGenerator = new RandomAnswerGenerator();
        Answer answer = new Answer(Arrays.asList("1 2 3 4".split(" ")));
        GuessNumberGame guessNumberGame = new GuessNumberGame(answer);
        guessNumberGame.guess(userAnswerNumber);
        if (guessNumberGame.getStatus().equals(GameStatus.SUCCEED)){
            user.setTotalPoints(user.getTotalPoints()+3);
            user.setContinueWinCount(user.getContinueWinCount()+1);
            if (user.getContinueWinCount() % 3 == 0){
                user.setTotalPoints(user.getTotalPoints()+2);
            }
            if (user.getContinueWinCount() % 5 == 0){
                user.setTotalPoints(user.getTotalPoints()+3);
            }
        }else {
            user.setTotalPoints(user.getTotalPoints()-3);
            user.setContinueWinCount(0);
        }
        Map<String,Integer> map = new HashMap<>();
        map.put("totalPoint",user.getTotalPoints());
        return map;
    }

    @GetMapping("/oneGameByOneUser")
    public int oneGameByOneUser(@RequestParam User user, @RequestParam String guess){
        user.setTotalPoints(0);
        List<String> userAnswerNumber = Arrays.asList(guess.split(" "));
        RandomAnswerGenerator randomAnswerGenerator = new RandomAnswerGenerator();
        GuessNumberGame guessNumberGame = new GuessNumberGame(randomAnswerGenerator);
        guessNumberGame.guess(userAnswerNumber);
        if (guessNumberGame.getStatus().equals(GameStatus.SUCCEED)){
            user.setTotalPoints(user.getTotalPoints()+3);
        }else if (guessNumberGame.getStatus().equals(GameStatus.FAILED)){
            user.setTotalPoints(user.getTotalPoints()-3);
        }
        return user.getTotalPoints();
    }

    @GetMapping("/countPoints")
    public int calculatePoints(@RequestParam User user, @RequestParam String guess) {
        int points = 0;
        List<String> userAnswerNumber = Arrays.asList(guess.split(" "));
        RandomAnswerGenerator randomAnswerGenerator = new RandomAnswerGenerator();
        GuessNumberGame guessNumberGame = new GuessNumberGame(randomAnswerGenerator);
        for (int i = 0; i < user.getPlayTimes(); i++) {
            String result = guessNumberGame.guess(userAnswerNumber);
            countPointsService.isContinueWin(result);
            if (guessNumberGame.getStatus().equals(GameStatus.SUCCEED)) {
                points = countPointsService.addPoint();
            } else if (guessNumberGame.getStatus().equals(GameStatus.FAILED)) {
                points = countPointsService.subPoint();
            }
        }
        return points;
    }

    public Map<String,Integer> oneGuess(@RequestParam User user, @RequestParam String guess){
        List<String> userAnswerNumber = Arrays.asList(guess.split(" "));
        RandomAnswerGenerator randomAnswerGenerator = new RandomAnswerGenerator();
        Answer answer = new Answer(Arrays.asList("1 2 3 4".split(" ")));
        GuessNumberGame guessNumberGame = new GuessNumberGame(answer);
        guessNumberGame.guess(userAnswerNumber);
        if (guessNumberGame.getStatus().equals(GameStatus.SUCCEED)){
            user.setTotalPoints(user.getTotalPoints()+3);
            user.setContinueWinCount(user.getContinueWinCount()+1);
            if (user.getContinueWinCount() % 3 == 0){
                user.setTotalPoints(user.getTotalPoints()+2);
            }
            if (user.getContinueWinCount() % 5 == 0){
                user.setTotalPoints(user.getTotalPoints()+3);
            }
        }else {
            user.setTotalPoints(user.getTotalPoints()-3);
            user.setContinueWinCount(0);
        }
        Map<String,Integer> map = new HashMap<>();
        map.put("totalPoint",user.getTotalPoints());
        return map;
    }

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



