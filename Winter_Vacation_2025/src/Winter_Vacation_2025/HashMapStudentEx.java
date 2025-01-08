package Winter_Vacation_2025;

import java.util.*;

class Student01 {
    int id;
    String tel;

    public Student01(int id, String tel) {
        this.id = id;
        this.tel = tel;
    }
}

public class HashMapStudentEx {

    public static void main(String[] args) {
        HashMap<String, Student01> map = new HashMap<>();  

     
        map.put("황기태", new Student01(1, "010-1111-1111"));
        map.put("이재문", new Student01(2, "010-2222-2222"));
        map.put("김남윤", new Student01(3, "010-3333-3333"));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("검색할 이름 : ");
            String name = sc.nextLine();
            if (name.equals("종료")) {
                System.out.println("종료합니다...");
                break;
            }
            Student01 student01 = map.get(name); 
            if (student01 == null)
                System.out.println(name + " 없는 사람입니다.");
            else
                System.out.println("id : " + student01.id + ", 전화 : " + student01.tel);
        }
        sc.close();
    }
}