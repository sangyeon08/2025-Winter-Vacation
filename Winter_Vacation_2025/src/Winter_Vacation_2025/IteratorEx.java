package Winter_Vacation_2025;

import java.util.*;

public class IteratorEx {
	public static void main(String[] args) {
		Vector<Integer> v = new Vector<Integer>();
		v.add(5);
		v.add(4);
		v.add(-1);
		v.add(2, 100);
		int sum = 0;
		
		Iterator<Integer> it = v.iterator();
		while(it.hasNext()) {
			int n = it.next();
			sum+= n;
			System.out.println(n);
			
		}
		System.out.println("총합 : " + sum);
	}
}
