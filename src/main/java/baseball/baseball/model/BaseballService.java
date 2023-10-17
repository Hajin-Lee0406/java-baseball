package baseball.baseball.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class BaseballService {

    // 정답 난수 생성
    public String getGoalNumber(){
        StringBuilder ranNum = new StringBuilder();
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 9, 3);

        for (Integer number : numbers) {
            ranNum.append(number);
        }

        return ranNum.toString();
    }
}
