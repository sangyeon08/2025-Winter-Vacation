package Winter_Vacation_2025;

import java.util.Scanner;
import java.util.Random;

public class Dice {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random random = new Random();

		System.out.print("사용자의 닉네임을 입력해주세요! : ");
		String name = sc.nextLine();

		int life = 10;
		int score = 0;

		while (life > 0) {
			int robotnum = random.nextInt(6) + 1;
			int usernum = random.nextInt(6) + 1;

			System.out.println("로봇의 주사위 값: " + robotnum);
			System.out.println(name + "의 주사위 값: " + usernum);

			if (robotnum > usernum) {
				life--;
				System.out.println("라이프가 1 감소했습니다.");
			} else if (robotnum < usernum) {
				score += 10;
				System.out.println("점수가 10점 증가했습니다.");
			} else {
				System.out.println("무승부입니다!");
			}
			if (score == 100) {
				break;
			}
			System.out.println("현재 라이프: " + life);
			System.out.println("현재 점수: " + score);
			System.out.print("Continue? ( 1 : Yes 2 : No ) : ");
			int answer = sc.nextInt();
			if (answer == 1) {
				continue;
			} else {
				break;
			}
		}

		if (score == 100)
			System.out.println("축하드립니다!! 최종 승리하셨습니다.");
		else
			System.out.println("게임 종료! 최종 점수: " + score);
	}
}