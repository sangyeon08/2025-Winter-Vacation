package Winter_Vacation_2025;
import java.util.*;
public class ArrayListEx {

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);
		for( int i = 0; i < 4; i++) {
			System.out.print("이름을 입력하세요 : ");
			String s = sc.next();
			a.add(s);
		}

		for( int i = 0; i < 4; i++) {
			String name = a.get(i);
			System.out.print(name + " ");
		}
		String longestname = "";
        for (String name : a) {
            if (name.length() > longestname.length()) {
                longestname = name;
            }
        }
        System.out.println("\n 가장 긴 이름 : " + longestname);
	}

}
