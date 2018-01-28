package training;

public class Test {

	static String res = "";
	static boolean check = true;

	public static void main(String[] args) {
		Test test = new Test();
		test.findNearest(12, 13, 10, 15, 14);

		System.out.println(res + " are the nearest ones");

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
}