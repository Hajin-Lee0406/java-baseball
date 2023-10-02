package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Application {
    public static void main(String[] args){

        boolean valid = true;
        while (valid){
            // 난수 생성하기
            String ranNum = getRandom();

            System.out.println(ranNum);
            System.out.println("숫자 야구 게임을 시작합니다");

            while (!game(ranNum));

            valid = continueGame();
        }
    }

    // 난수 생성
    private static String getRandom(){
        List<String> num = new ArrayList<>();
        for (int i=0; i<10; i++) num.add(String.valueOf(i));

        StringBuilder ranNum = new StringBuilder();
        for (int i=0; i<3; i++){
            String a = num.get(Randoms.pickNumberInRange(1, 9));
            ranNum.append(a);
            num.remove(a);
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
    private static boolean game(String ranNum){
        System.out.println("숫자를 입력해주세요 :");
        Scanner scan = new Scanner(System.in);
        String str = scan.next();

        String validation = validation(ranNum, str);
        System.out.println(validation);

        return validation.equals("3스트라이크");
    }

    // 게임 끝내기
    private static boolean continueGame(){
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료\n" + "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");

        Scanner scan = new Scanner(System.in);
        int str = scan.nextInt();

        if (str != 1 && str !=2){
            throw new IllegalArgumentException("입력값이 올바르지 않습니다.");
        }

        return str == 1;
    }

}
