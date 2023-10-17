package baseball.baseball.controller;

import baseball.baseball.model.BaseballService;
import baseball.baseball.view.InputView;
import baseball.baseball.view.OutputView;

import static baseball.baseball.model.constants.*;

public class BaseballController {
    private static final BaseballService baseballService = new BaseballService();
    private static final OutputView outputView = OutputView.getInstance();
    private static final InputView inputView = InputView.getInstance();


    public static void run() {
        boolean valid = true;

        while (valid) {
            String ranNum = baseballService.getGoalNumber();

            // 테스트용. 실제 동작 시, 지우고 동작한다.
            System.out.println(ranNum);

            outputView.printStartGame();

            game(ranNum);

            valid = isContinueGame();
        }
    }

    // 숫자 맞추기
    private static void game(String ranNum) {
        boolean validInput = false;
        String validation;

        while (!validInput) {
            String str = inputView.getNumber();

            if (str.length() != 3) {
                throw new IllegalArgumentException(INVALID_INPUT);
            }

            validation = baseballService.validation(ranNum, str);
            outputView.printValidation(validation);

            validInput = validation.equals(SUCCESS + STRIKE);
        }
    }

    // 게임 끝내기
    private static boolean isContinueGame() {
        String str = InputView.getisContinueGame();

        return str.equals(CONTINUE_GAME);
    }
}
