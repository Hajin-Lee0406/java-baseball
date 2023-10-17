package baseball.baseball.controller;

import baseball.baseball.model.BaseballService;
import baseball.baseball.view.InputView;
import baseball.baseball.view.OutputView;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static baseball.baseball.model.constants.*;
import static camp.nextstep.edu.missionutils.Console.readLine;

public class BaseballController {
    private static final String CONTINUE_GAME = "1";
    private static final BaseballService baseballService = new BaseballService();
    private static final OutputView outputView = OutputView.getInstance();
    private static final InputView inputView = InputView.getInstance();

    private String GoalNumber;

    public static void run(){
        boolean valid = true;

        while (valid){
            String ranNum = baseballService.getGoalNumber();

            // 테스트용. 실제 동작 시, 지우고 동작한다.
            System.out.println(ranNum);

            outputView.printStartGame();

            game(ranNum);

            valid = continueGame();
        }
    }

    // 숫자 맞추기
    private static void game(String ranNum){
        boolean validInput = false;
        String validation;

        while (!validInput) {
            String str = inputView.getNumber();

            if (str.length() != 3) {
                throw new IllegalArgumentException(INVALID_INPUT);
            }

            validation = validation(ranNum, str);
            System.out.println(validation);

            validInput = validation.equals(SUCCESS);
        }
    }

    // 결과 검증
    private static String validation(String ranNum, String input){
        int strike = 0;
        int ball = 0;

        for(int i=0; i<3; i++){
            if(ranNum.charAt(i) == input.charAt(i)){
                strike ++;
            }
            if (input.contains(String.valueOf(ranNum.charAt(i))) && ranNum.charAt(i) != input.charAt(i)){
                ball++;
            }
        }

        if(strike == 0 && ball ==0) return NOTHING;
        if(strike == 0) return ball + BALL;
        if(ball == 0) return strike + STRIKE;
        return ball + BALL + " " + strike + STRIKE;
    }

    // 게임 끝내기
    private static boolean continueGame(){
        InputView.getIsEndGame();

        String str = readLine();

        return str.equals(CONTINUE_GAME);
    }
}
