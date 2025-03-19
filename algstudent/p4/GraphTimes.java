package algstudent.s4;

public class GraphTimes {
	public static void main(String arg[]) {
		long t1, t2;
		Greedy gr = new Greedy();
		for (int n = 8; n <= 65536; n *= 2) {
			t1 = System.currentTimeMillis();
			for (int rep = 0; rep <= 8; rep++) {
				gr.greedyAlgorithm(n);
			}
			t2 = System.currentTimeMillis();
			System.out.println(n + "\t" + (t2 - t1));
		}
	}
}
