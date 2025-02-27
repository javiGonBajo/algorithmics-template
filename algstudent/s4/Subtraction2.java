package algstudent.s3;

/* Class that models T(n)=T(n-1)+O(n)
 * The time complexity is quadratic O(n^2) 
 * and the waste of stack is O(n)
 * In this case => the stack overflows 
 */
public class Subtraction2 {
	public static long rec2(int n) {
		long cont = 0;
		if (n <= 0)
			cont++;
		else {
			for (int i = 0; i < n; i++)
				cont++; // O(n)
			rec2(n - 1);
			for (int i = 0; i < n; i++)
				cont++; // O(n)
		}
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2, cont = 0;
		int rep = Integer.parseInt(arg[0]);
		for (int n = 1; n <= 100000; n *= 2) {
			t1 = System.currentTimeMillis();
			for(int i = 0; i < rep; i++) {
				cont = rec2(n);
				} // for
			t2 = System.currentTimeMillis();
			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main
} // class