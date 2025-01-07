package AfterSchoolC1;
import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("두 수를 입력하세요 : ");

            try {
                double a = sc.nextDouble();
                double b = sc.nextDouble();

                System.out.print("연산자를 입력하세요 (+, -, *, /) : ");
                String operator = sc.next();

                double result = 0;
                switch (operator) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        if (b == 0) {
                            throw new ArithmeticException("0으로 나눌 수 없습니다.");
                        }
                        result = a / b;
                        break;
                        
                    default:
                        throw new NumberFormatException("잘못된 연산자입니다.");
                }

                System.out.println("결과: " + result);
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력하세요.");
                sc.nextLine(); // 이거 안 넣으면 이상한게 입력됨
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("잘못된 연산자입니다. (+, -, *, / 중 하나를 입력하세요.)");
            }
            System.out.print("계속하시겠습니까? (1 : Yes 2 : No) ");
            int answer = sc.nextInt();
            if( answer == 2) {
            	break;
            }
        }
    }
}
