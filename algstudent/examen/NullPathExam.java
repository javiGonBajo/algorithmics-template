package s7;

import java.util.LinkedList;
import java.util.Random;

public class NullPathExam {
	private int[][] weights;
	private LinkedList<Integer> result;
	private boolean found;
	private int value = 0;
	private int limit;
	private int threshold = 75;
	
	public NullPathExam(int n) {
		newNullPath(n, true);
	}
	
	public NullPathExam() {
		
	}
	
	public static void main(String[] args) {
		NullPathExam np = new NullPathExam(Integer.parseInt(args[0]));
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
		found = auxiliarMethod(0);
		value += weights[result.getLast()][weights.length-1];
		
		if(print) {
			if(!found) {
				 System.out.println("There is no solution");
			}
			else {
				result.add(weights.length-1);
				printPath();
			}
			
			printMatrix();
			if(found) {
				System.out.println("Total weight = " + value);
				printWeights();
			}
				
		}
		
		
	}
	
	private void printWeights() {
		for(int i = 0; i<result.size() - 1;i++) {
			System.out.print(weights[result.get(i)][result.get(i+1)]+" | ");
		}
	}

	private boolean auxiliarMethod(int lastWeight) {
		if(checkResult(lastWeight))
			return true;
		
		limit = (weights.length - result.size())* threshold;
		for(int node = 1; node<weights.length-1;node++) {		
			if(result.size() == weights.length - 1 || value >= limit)
				break;
			
			int last = result.getLast();
			if(!result.contains(node) && ((lastWeight % 2 == 0 && weights[last][node]%2 != 0) || (lastWeight % 2 != 0 && weights[last][node]%2 == 0) || result.size() == 1)) {
				result.add(node);
				value += weights[last][node];
				found = auxiliarMethod(weights[last][node]);
				if(!found) {
					value -= weights[last][node];
					result.removeLast();
				}
				else
					break;
			}
			
		}		
		return found;
	}
	
	private boolean checkResult(int lastWeight) {
		if(result.size() < weights.length - 1)
			return false;
		
		return Math.abs(value + weights[result.getLast()][weights.length-1]) <= threshold &&
				((lastWeight % 2 == 0 && weights[result.getLast()][weights.length-1]%2 != 0) || (lastWeight % 2 != 0 && weights[result.getLast()][weights.length-1]%2 == 0));
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