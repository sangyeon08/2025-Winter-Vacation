package Winter_Vacation_2025;

import java.util.Scanner;

public class ExchangeRate {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("선택지 입력 ( 0 : 종료 1 : 달러를 한화로 변동 2 : 한화를 달러로 변동 ) : ");
			int answer = sc.nextInt();
			if(answer ==1) {
				System.out.print("환율 입력 : ");
				double rate = sc.nextDouble();
				System.out.print("달러 입력 : ");
				int money = sc.nextInt();
				System.out.println("한화 : " + money*rate);
			}else if(answer == 2) {
				System.out.print("환율 입력 : ");
				double rate = sc.nextDouble();
				System.out.print("한화 입력 : ");
				int money = sc.nextInt();
				System.out.println("달러 : " +money/rate);
			}else if(answer == 0) {
				System.out.println("안녕히가십시오");
				break;
			}else {
				System.out.println("올바른 값을 입력하세요");
			}
			
		}

	}

}
