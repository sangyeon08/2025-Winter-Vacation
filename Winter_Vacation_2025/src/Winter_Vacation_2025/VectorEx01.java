package Winter_Vacation_2025;
import java.util.Vector;

public class VectorEx01 {
 public static void main(String[] args) {
	 Vector<Integer>v = new Vector<Integer>();
	 v.add(5);
	 v.add(4);
	 v.add(-1);
	 v.add(2,100);
	 System.out.println("벡터 내의 요소 객체 수 : " + v.size());
	 System.out.println("벡터의 현재 용량 : " + v.capacity());
	 int sum = 0;
		for( int i = 0; i < v.size();i++) {
			int n = v.elementAt(i);
		}
		System.out.println("벡터에 있는 정수 합 : " + sum);
	 for(int i = 0; i < v.size(); i++) {
		 int n = v.get(i);
		 sum += n;
		 System.out.println(n);
	 }
 }
}
