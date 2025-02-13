package algstudent.s12;

public class Loop5 {
	public static long loop5(int n) {
		long cont = 0;
		for (int i = 0; i <n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 1; k < n; k*=5)
					for (int l = 1; l <= n; l*=5)
						cont++;
		return cont;

	}

	public static void main(String arg[]) {
		long c = 0;
		long t1, t2;

		int nTimes = Integer.parseInt(arg[0]);

		System.out.println("n\ttime\trepetions\tcounter");

		for (int n = 6400; n <= 819200; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repetitions = 1; repetitions <= nTimes; repetitions++)
				c = loop5(n);

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1) + "\t" + nTimes + "\t\t" + c);
		} // for
	}
}
