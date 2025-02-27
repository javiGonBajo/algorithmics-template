package algstudent.s3;

public class Subtraction4 {
	/*
	 * Params: a=1;b=1;k=2
	 * Complexity O(n^3)
	 */
	public static long rec4(int n) {
		long cont = 0;
		if (n <= 0)
			cont++;
		else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					cont++; // O(n^2)
			}
				
			rec4(n - 1);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
				cont++; // O(n^2)
			}
		}
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2, cont = 0;
		int rep = Integer.parseInt(arg[0]);
		for (int n = 1; n <= 100000; n *= 2) {
			t1 = System.currentTimeMillis();
			for(int i = 0; i < rep; i++) {
				cont = rec4(n);
				} // for
			t2 = System.currentTimeMillis();
			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main
}
