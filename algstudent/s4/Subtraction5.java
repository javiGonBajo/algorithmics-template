package algstudent.s3;

public class Subtraction5 {
	/*
	 * Params: a=3;b=2;k=0
	 * Complexity O(3^(n/2))
	 */	
	public static long rec5(int n) {
		long cont = 0;
		if (n <= 0)
			cont++;
		else {
			cont++; // O(1)
			rec5(n - 2);
			rec5(n - 2);
			rec5(n - 2);
		}
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2, cont = 0;
		int rep = Integer.parseInt(arg[0]);
		
		for (int n = 30; n <= 10000000; n += 2) {
			t1 = System.currentTimeMillis();
			for(int i = 0; i < rep; i++) {
				cont = rec5(n);
				} // for
			t2 = System.currentTimeMillis();
			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main
}
