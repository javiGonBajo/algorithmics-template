package algstudent.s6;

import java.util.LinkedList;
import java.util.Random;

public class NullPath {
	private int[][] weights;
	private LinkedList<Integer> result;
	private boolean found;
	
	public NullPath(int n) {
		newNullPath(n, true);
	}
	
	public NullPath() {
		
	}
	
	public static void main(String[] args) {
		NullPath np = new NullPath(Integer.parseInt(args[0]));
	}
	
	private void printPath() {
		System.out.println(result);
	}

	public void newNullPath(int n, boolean print) {
		weights = new int[n][n];
		result = new LinkedList<Integer>();
		found = false;
		Random p = new Random();
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights.length; j++) {
				weights[i][j] = p.nextInt(10, 100);
				if(p.nextBoolean())
					weights[i][j] = - weights[i][j];
			}
		}
		
		CalculateNullPath(print);
	}

	private void CalculateNullPath(boolean print) {
		result.add(0);
		found = auxiliarMethod(1);
		if(!found && print) {
			 System.out.println("There is no solution");
		}
		else {
			result.add(weights.length-1);
			printPath();
		}
		
		printMatrix();
		
	}
	
	private boolean auxiliarMethod(int current) {
		if(checkResult())
			return true;
		
		int counter = 0;
		int node;
		int rep = 0;
		while(!found && counter < weights.length - 2 && result.size() < weights.length) {
			do {
				node = (current + counter + rep++)%(weights.length - 2);
			} while(result.contains(node) && result.size()!=weights.length-1);
			
			
			if(result.size() < weights.length-1) {
				result.add(node);
				found = auxiliarMethod((node + 1)%(weights.length - 1));
				if(!found)
					result.removeLast();
					counter++;
			}
			
			else {
				if(!found ) {
					result.removeLast();
					return false;
				}
			}
		}		
		
		return found;
	}
	
	private boolean checkResult() {
		if(result.size() < weights.length - 1)
			return false;
		
		int value = 0;
		for(int i = 1; i<result.size() - 3; i++) {
			value += weights[result.get(i)][result.get(i+1)];
		}
		value += weights[result.getFirst()][weights.length-1];
		value += weights[result.getLast()][weights.length-1];
		return value <= 99 && value >= -99;
	}
	
	private void printMatrix() {
		int n = weights.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(String.format("%10s", weights[i][j]));
			System.out.println();
		}
		System.out.println();
	}
}
