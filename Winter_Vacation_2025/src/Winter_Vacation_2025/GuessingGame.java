package Winter_Vacation_2025;

import java.util.Scanner;
import java.util.Random;

public class GuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            System.out.println("========숫자 맞히기 게임========");
            System.out.println("범위는 1~100입니다.");

            int answer = random.nextInt(100) + 1; // 처음엔 Math.random()*100+1 로 진행했지만 더 간단한 방법을 찾아보던 중 이 친구 발견!
            int guess;
            int num = 0;

            do {
                System.out.print("예측 : ");
                guess = scanner.nextInt();
                num++;
                if (guess > answer) {
                    System.out.println("Down");
                } else if (guess < answer) {
                    System.out.println("Up");
                } else {
                    System.out.println("정답입니다!!!!");
                    System.out.println("추측한 횟수 : " + num);
                    break;
                }
            } while (true);

            System.out.print("다시 하시겠습니까? ( 1 : Yes, 2 : No ) : ");
            int choice = scanner.nextInt();

            if (choice != 1) {
                System.out.println("즐거운 시간이 되셨길 바랍니다. 바이바이!");
                break;
            }
        }
    }
}