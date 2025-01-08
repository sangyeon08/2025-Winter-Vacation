package Winter_Vacation_2025;

import java.util.*;

public class IdPw {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> idPw = new HashMap<>();

        idPw.put("angel", 1004);
        idPw.put("quick", 8282);
        idPw.put("baby", 5959);

        while (true) {
            System.out.print("로그인 하실 생각 있으십니까? (Y / N) : ");
            String choice = sc.next();

            if (choice.equals("N")) {
                System.out.println("프로그램 강제종료");
                break;
            }

            if (choice.equals("Y")) {
                System.out.print("id : ");
                String userId = sc.next();
                System.out.print("password : ");
                int password = sc.nextInt();

                if (idPw.containsKey(userId)) { 
                    if (idPw.get(userId) == password) {
                        System.out.println("로그인 성공!");
                        break;
                    } else {
                        System.out.println("비밀번호가 틀렸습니다.");
                    }
                } else {
                    System.out.println("아이디가 존재하지 않습니다.");
                }
            } else {
                System.out.println("잘못된 입력입니다. Y 또는 N을 입력해주세요.");
            }
        }
        sc.close();
    }
}