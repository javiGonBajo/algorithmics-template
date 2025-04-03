package s7;

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
		LinkedList<Integer> l = new LinkedList<Integer>();
		found = auxiliarMethod(l);
		if(print) {
			if(!found) {
				 System.out.println("There is no solution");
			}
			else {
				result.add(weights.length-1);
				printPath();
			}
			
			printMatrix();
		}
		
		
	}
	
	private boolean auxiliarMethod(LinkedList<Integer> usedNodes) {
		if(checkResult())
			return true;
		
		for(int node = 1; node<weights.length-1;node++) {		
			if(usedNodes.size())
			if(!usedNodes.contains(node)) {
				result.add(node);
				usedNodes.add(node);
				found = auxiliarMethod(usedNodes);
				if(!found) {
					usedNodes.removeLast();
					result.removeLast();
				}
				else
					break;
			}
			
		}		
		return found;
	}
	
	private boolean checkResult() {
		if(result.size() < weights.length - 1)
			return false;
		
		int value = 0;
		for(int i = 0; i<result.size() - 1; i++) {
			value += weights[result.get(i)][result.get(i+1)];
		}
		value += weights[result.getLast()][weights.length-1];
		System.out.println(value);
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
