package s8;

public class NullPathTimes {
	public static void main(String[] args) {
		long t1, t2;
		NullPath np = new NullPath();
		for (int n = 20; n <= 1000000000; n += 5) {
			t1 = System.currentTimeMillis();
			
			for (int i = 0; i < 100; i++) {
				np.newNullPath(n, false);
			}

			t2 = System.currentTimeMillis();
			System.out.println(n + "\t" + (t2 - t1));
		}
	}
}
