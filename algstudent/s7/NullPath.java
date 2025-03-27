package s6;

import java.util.ArrayList;
import java.util.Random;

public class NullPath {
	private int[][] weights;
	private ArrayList<Integer> result;
	private int counter;
	private boolean found = false;
	
	public NullPath(int n) {
		weights = new int[n][n];
		counter = n;
		result = new ArrayList<Integer>();
		Random p = new Random();
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights.length; j++) {
				weights[i][j] = p.nextInt(10, 100);
				if(p.nextBoolean())
					weights[i][j] = - weights[i][j];
			}
		}
		
		CalculateNullPath();
	}

	private void CalculateNullPath() {
		found = auxiliarMethod(0);
		if(!found)
			 System.out.println("There is no solution");
	}
	
	private boolean auxiliarMethod(int current) {
		result.add(current);
		if(checkResult())
			found = true;
		else
			if(!found)
				auxiliarMethod(current + 1);
		return found;
	}

	private boolean checkResult() {
		if(result.size() < weights.length)
			return false;
		
		int value = 0;
		for(int i = 0; i<result.size() - 2; i++) {
			value += weights[result.get(i)][result.get(i+1)];
		}
		return value < 99 && value > -99;
	}
	
	
}
