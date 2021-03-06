package com.twschool.practice.domain;

import com.twschool.practice.domain.GameStatus;

import java.util.List;

public class GuessNumberGame {
    private Answer answer;
    private GameStatus status = GameStatus.CONTINUED;
    private int MAX_TRY_TIMES = 6;
    private int leftTryTimes = MAX_TRY_TIMES;
    private RandomAnswerGenerator randomAnswerGenerator;

    public GuessNumberGame(RandomAnswerGenerator randomAnswerGenerator) {
        this.randomAnswerGenerator = randomAnswerGenerator;
        this.answer = answer;
    }

    public GuessNumberGame(Answer answer) {
        this.randomAnswerGenerator = randomAnswerGenerator;
        this.answer = answer;
    }

    public String guess(List<String> userAnswerNumbers) {
        String result = answer.check(userAnswerNumbers);
        decreaseTryTimes();
        modifyStatus(result);
        return result;
    }

    private void modifyStatus(String result) {
        boolean noTryTimes = leftTryTimes == 0;
        if (noTryTimes) {
            status = GameStatus.FAILED;
        }
        boolean succeed = result.equals("4A0B");
        if (succeed) {
            status = GameStatus.SUCCEED;
        }
    }

    private void decreaseTryTimes() {
        leftTryTimes --;
    }

    public GameStatus getStatus() {
        return status;
    }

    public int getLeftTryTimes() {
        return leftTryTimes;
    }

    public void setLeftTryTimes(int leftTryTimes) {
        this.leftTryTimes = leftTryTimes;
    }

    public int getMAX_TRY_TIMES() {
        return MAX_TRY_TIMES;
    }
}
