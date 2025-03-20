package labs.en._25.algstudent.s5;


public class MinimumPathsTimes {
	
	public static void main(String[] args) {
		long t1, t2;

		for (int n = 200; n <= 1000000000; n *= 2) {

			t1 = System.currentTimeMillis();
			
			MinimumPaths.basicAlgorithm(n);

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1));
		}
	}
}
