package algstudent.s3;

public class Divison5 {
	/* Params: a=4;b=2;k=1
	 * Complexity O(n^2)
	 * a>b^k
	 */
	public static long rec5 (int n)
	{ 
	 long cont = 0;
	 if (n<=0) cont++;
	 else
	  { 
		 for (int i=1;i<n;i++) {
			 cont++ ;  //O(n^2)
		 }
			 
	    rec5(n/2);
	    rec5(n/2);
	    rec5(n/2);
	    rec5(n/2);
	  }
	 return cont;   
	}
	
	public static void main (String arg []) 
	{
		long t1,t2,cont = 0;
		int rep = Integer.parseInt(arg[0]);
		
		for (int n = 1000; n <= 10000000; n *= 2) {
			t1 = System.currentTimeMillis();
			for(int i = 0; i < rep; i++) {
				cont = rec5(n);
				} // for
			t2 = System.currentTimeMillis();
			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		}	
	} // main


}
