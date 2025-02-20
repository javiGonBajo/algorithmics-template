package algstudent.s2;

public class QuicksortInsertionTimes {
	static int[] v;

	public static void main(String arg[]) {
		long t1, t2;
		int k = Integer.parseInt(arg[0]);
		int n = 16000000;
		v = new int[n];

		Vector.randomSorted(v);

		t1 = System.currentTimeMillis();

		QuicksortInsertion.quicksortInsertion(v, k);

		t2 = System.currentTimeMillis();

		System.out.println(n + "\t" + (t2 - t1));
	}
}
