package training;

import java.util.HashMap;
import java.util.Map;

public class Test {

	static String res = "";
	static boolean check = true;

	public static void main(String[] args) {
		collectionTests();
		int[] arr1 = {1,2,3,4,5,6,7,8,9,10};
		System.out.println(binarySearch(arr1,6));
		fabonacciPrint(100);
		palindrome(121);
	}
	
	public static void fabonacciPrint(int limit) {
		int fabanocci = 1;
		int temp=0;
		int temp1=0;
		boolean check = true;
		System.out.println(fabanocci);
		while(check) {
			temp1 = fabanocci;
			fabanocci = temp+fabanocci;
			if(limit > fabanocci) {
				System.out.println(fabanocci);
			}else {
				check = false;
			}
			temp = temp1;
		}
	}
	
	public static void palindrome(int checkPlaindrome) {
		int temp = 0;
		int checkPlaind = checkPlaindrome;
		while(checkPlaind > 0 ) {
			temp = (temp*10)+checkPlaind%10;
			checkPlaind = checkPlaind/10;
		}
		if(temp==checkPlaindrome) {
			System.out.println("Yes, the given number is a plaindrome");
		}else {
			System.out.println("No, given number is not a palindrome");
		}
	}
	
	public static int binarySearch(int[] arr, int key) {
		int start = 0;
		int end = arr.length-1;
		while(start <= end) {
			int mid = (start+end)/2;
			if(key == arr[mid]) {
				return mid;
			}if(key < arr[mid]) {
				end = mid-1;
			}else {
				start = mid+1;
			}
		}
		return -1;
	}
	
	public static void collectionTests() {
		Employee e1 = new Employee();
		e1.setId(1);
		Employee e2 = e1;
		Map<Employee, Integer> map = new HashMap<Employee, Integer>();
		map.put(e1,1);
		map.put(e2,2);
		for (Employee i : map.keySet()) {
			System.out.println(i.getId());
		}
	}
	
	public void findNearest(int a, int b, int c, int d, int target) {
		int[] list = { Math.abs(target - a), Math.abs(target - b), Math.abs(target - c), Math.abs(target - d) };
		int temp = list[0];
		for (int i = 0; i < 3; i++) {
			temp = gimmeResult(temp, list[i + 1], check);
		}
		for (int i = 0; i < 4; i++) {
			if (temp == list[i]) {
				if (i == 0) {
					res = res + " a";
				} else if (i == 1) {
					res = res + " b";
				} else if (i == 2) {
					res = res + " c";
				} else if (i == 3) {
					res = res + " d";
				}
			}
		}

	}

	public int gimmeResult(int e, int f, boolean check) {
		if (e > f) {
			return f;
		} else if (f > e) {
			return e;
		} else if (e == f) {
			return e;
		}
		return 0;
	}

	/*
	 * public void printSortedArray() { Integer[] a = { 1, 4, 2 }, b = { 9, 6, 5 };
	 * Integer[] c = { a[0], a[1], a[2], b[0], b[1], b[2] }; int temp; for (int i =
	 * 0; i < c.length; i++) { for (int j = 1; j < c.length - i; j++) { if (c[j] <
	 * c[j - 1]) { temp = c[j]; c[j] = c[j - 1]; c[j - 1] = temp; } } }
	 * 
	 * for (int i = 0; i < 6; i++) { System.out.println(c[i]); } }
	 */
}