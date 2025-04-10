package s8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class NullPath {
	private int[][] weights;
	private LinkedList<Integer> result;
	private ArrayList<Node> nodeList = new ArrayList<Node>();
	private boolean found;
	private int value = 0;
	private Heap ds = new Heap();
	private int threshold = 99;
	Node bestNode;
	int pruneLimit;
	
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
			nodeList.add(new Node(i));
		}
		
		CalculateNullPath(print);
	}

	private void CalculateNullPath(boolean print) {
		found = branchAndBound(nodeList.get(0));
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
			System.out.println("Total weight = " + value);
		}
		
		
	}
	
//	private boolean auxiliarMethod() {
//		if(checkResult())
//			return true;
//		
//		for(int node = 1; node<weights.length-1;node++) {		
//			if(result.size() == weights.length - 1)
//				break;
//			if(!result.contains(node)) {
//				int last = result.getLast();
//				result.add(node);
//				value += weights[last][node];
//				found = auxiliarMethod();
//				if(!found) {
//					value -= weights[last][node];
//					result.removeLast();
//				}
//				else
//					break;
//			}
//			
//		}		
//		return found;
//	}
	
	public boolean branchAndBound(Node rootNode) {
		ds.insert(rootNode); //first node to be explored
		pruneLimit = rootNode.initialValuePruneLimit(nodeList.size() - 1, threshold);
		while (!ds.empty() && ds.estimateBest() < pruneLimit) {
			Node node = ds.extractBestNode();
			ArrayList<Node> children = node.expand();
			for (Node child : children) {
				if (child.isSolution(nodeList.size(), value, threshold)) {
					int cost = child.getHeuristicValue();
					if (cost < pruneLimit) {
						pruneLimit = cost;
						bestNode = child;
						found = true;
					}
				}
				else {
					if (child.getHeuristicValue() < pruneLimit)
						ds.insert(child);
				}
			}
		} //while
		return found;
	}

	private boolean checkResult() {
		
		
		return Math.abs(value + weights[result.getLast()][weights.length-1]) <= 99;
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

