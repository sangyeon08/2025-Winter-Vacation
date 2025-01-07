package Winter_Vacation_2025;

import java.util.Scanner;
import java.util.Random;

public class Dice02 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();
        int choice;
        while (true) {
            System.out.println("=====2인용 주사위 게임=====");
            System.out.print("첫번째 플레이어의 이름 입력 : ");
            String player1 = sc.nextLine();
            System.out.print("두번째 플레이어의 이름 입력 : ");
            String player2 = sc.nextLine();

            int totalScore1 = 0; 
            int totalScore2 = 0; 
            
            do {
                int num1 = ran.nextInt(6) + 1;
                int num11 = ran.nextInt(6) + 1;
                int num2 = ran.nextInt(6) + 1;
                int num22 = ran.nextInt(6) + 1;

                int answer1 = num1 + num11;
                int answer2 = num2 + num22;

                System.out.println(player1 + "의 첫번째 주사위 : " + num1);
                System.out.println(player1 + "의 두번째 주사위 : " + num11);
                System.out.println(player2 + "의 첫번째 주사위 : " + num2);
                System.out.println(player2 + "의 두번째 주사위 : " + num22);

                totalScore1 += answer1;
                totalScore2 += answer2;

                System.out.println(player1 + "의 총 점수: " + totalScore1);
                System.out.println(player2 + "의 총 점수: " + totalScore2);

                if (answer1 > 10 && answer2 > 10) {
                    System.out.println("무승부입니다!");
                } else if (answer1 > answer2) {
                    System.out.println(player1 + " 승리!");
                } else if (answer1 < answer2) {
                    System.out.println(player2 + " 승리!");
                } else {
                    System.out.println("무승부입니다!");
                }

                System.out.print("다시 게임을 하시겠습니까? (1: Yes, 2: No): ");
                choice = sc.nextInt();
                sc.nextLine();

                if (choice != 1 && choice != 2) {
                    System.out.println("잘못된 입력입니다. 1 또는 2를 입력해주세요.");
                    continue;
                }
            } while (choice == 1);

            System.out.println("게임 종료");
            break;
        }
    }
}