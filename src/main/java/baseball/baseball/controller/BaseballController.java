package baseball.baseball.controller;

import baseball.baseball.model.BaseballService;
import baseball.baseball.view.InputView;
import baseball.baseball.view.OutputView;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class BaseballController {
    private static final String CONTINUE_GAME = "1";
    private static final OutputView outputView = OutputView.getInstance();
    private static final InputView inputView = InputView.getInstance();

    public static void run(){
        boolean valid = true;

        while (valid){
            String ranNum = getRandom();

            System.out.println(ranNum); // 테스트. 실제 동작 시, 지우고 동작시킨다.
            outputView.printStartGame();

            game(ranNum);

            valid = continueGame();
        }
    }

    // 난수 생성
    private static String getRandom(){
        StringBuilder ranNum = new StringBuilder();
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 9, 3);
        for (Integer number : numbers) {
            ranNum.append(number);
        }

        return ranNum.toString();
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

        if(strike == 0 && ball ==0) return "낫싱";
        if(strike == 0) return ball + "볼";
        if(ball == 0) return strike + "스트라이크";
        return ball + "볼" + " " + strike + "스트라이크";
    }

    // 숫자 맞추기
    private static void game(String ranNum){
        boolean validInput = false;
        String validation;

        while (!validInput) {
            String str = inputView.getNumber();

            if (str.length() != 3) {
                throw new IllegalArgumentException("입력값이 올바르지 않습니다");
            }

            validation = validation(ranNum, str);
            System.out.println(validation);

            validInput = validation.equals("3스트라이크");
        }
    }

    // 게임 끝내기
    private static boolean continueGame(){
        InputView.getIsEndGame();

        String str = readLine();

        return str.equals(CONTINUE_GAME);
    }
}
