package baseball.baseball.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static baseball.baseball.model.constants.*;

public class BaseballService {

    // 정답 난수 생성
    public String getGoalNumber() {
        StringBuilder ranNum = new StringBuilder();
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 9, 3);

        for (Integer number : numbers) {
            ranNum.append(number);
        }

        return ranNum.toString();
    }

    // 입력값 검증
    public String validation(String ranNum, String input) {
        int strike = 0;
        int ball = 0;

        for (int i = 0; i < 3; i++) {
            if (ranNum.charAt(i) == input.charAt(i)) {
                strike++;
            }
            if (input.contains(String.valueOf(ranNum.charAt(i))) && ranNum.charAt(i) != input.charAt(i)) {
                ball++;
            }
        }

        if (strike == 0 && ball == 0) return NOTHING;
        if (strike == 0) return ball + BALL;
        if (ball == 0) return strike + STRIKE;
        return ball + BALL + " " + strike + STRIKE;
    }
}
