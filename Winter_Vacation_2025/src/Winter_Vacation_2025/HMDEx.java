package Winter_Vacation_2025;
import java.util.*;

public class HMDEx {
    public static void main(String[] args) {
        HashMap<String, String> dic = new HashMap<String, String>();

        dic.put("baby", "아기");
        dic.put("love", "사랑");
        dic.put("apple", "사과");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("찾고 싶은 단어는? ");
            String eng = scanner.nextLine();
            if (eng.equals("종료")) {
                System.out.println("종료합니다...");
                break;
            }
            String kor = dic.get(eng);
            if (kor == null) {
                System.out.println(eng + "는 없는 단어입니다.");
            } else {
                System.out.println(eng + "의 뜻은 " + kor + "입니다.");
            }
        }
        scanner.close();
    }
}