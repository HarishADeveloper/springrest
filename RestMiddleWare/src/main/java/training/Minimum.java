package training;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Minimum {
	public static void main(String[] args) {
		
		least1(1, 2, 4, 5, 3);
		least(1, 2, 4, 5, 3);
		//least(5,8,9,5,3);
		//bubbleSort();
	}
	public static void least1(int a, int b, int c, int d, int target) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("a", Math.abs(target-a));
		map.put("b", Math.abs(target-b));
		map.put("c", Math.abs(target-c));
		map.put("d", Math.abs(target-d));
		
		int least = -1;
		String key = "";
		
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			int temp = entry.getValue();
			if(least == temp) {
				key = key +" "+entry.getKey();
			}
			if(least == -1 || temp<least) {
				key = "";
				least = temp;
				key = entry.getKey();
			}
		}
		System.out.println("\""+key+"\" jjj");
		date = new Date();
		System.out.println(dateFormat.format(date));
	}
	public static void least(int a, int b, int c, int d, int target) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int temp = Math.abs(target-a);
		String res = "";
		boolean check = true;
		map.put("a", Math.abs(target-a));
		map.put("b", Math.abs(target-b));
		map.put("c", Math.abs(target-c));
		map.put("d", Math.abs(target-d));
		
		for (String key : map.keySet()) {
			if(map.get(key)==temp) {
				if(check) {
					res = key+" ";
				}else {
					res+=key+" ";
				}
				
			}else if(map.get(key)<temp) {
				res = key+" ";
				temp = map.get(key);
			}
			check=false;
		}
		System.out.println(res+" are the nearest ones");
		
		date = new Date();
		System.out.println(dateFormat.format(date));
	}
	
	public static void bubbleSort() {
		int[] intArray = {1,2,5,6,3,8};
		int temp;
		
		for(int i=0; i<intArray.length; i++)
			System.out.print(intArray[i]);
		System.out.println();
		for(int i=0; i<intArray.length; i++) {
			for(int j=1; j<intArray.length-i; j++) {
				if(intArray[j-1]>intArray[j]) {
					temp = intArray[j];
					intArray[j] = intArray[j-1];
					intArray[j-1] = temp;
				}
			}
		}
		for(int i=0; i<intArray.length; i++)
		System.out.print(intArray[i]);
		System.out.println();
	}
}
