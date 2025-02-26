package algstudent.s2;

public class QuicksortInsertionTimes {
	static int[] v;

	public static void main(String arg[]) {
		long t1, t2;
		int n = 16000000;
		int[] k = {0,5,10,20,30,50,100,200,500,1000};
		
		for(int i = 0; i<k.length; i+=1) {
			v = new int[n];

			Vector.randomSorted(v);
			
			t1 = System.currentTimeMillis();

			QuicksortInsertion.quicksortInsertion(v, i);

			t2 = System.currentTimeMillis();

			System.out.println(k[i] + "\t" + (t2 - t1));
		}
	}
}
