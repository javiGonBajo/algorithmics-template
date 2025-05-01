package algstudent.s8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NullPath {
	private int[][] weights;
	private ArrayList<Node> result;
	private ArrayList<Node> nodeList;
	private boolean found;
	private Heap ds = new Heap();
	private int threshold = 99;
	int pruneLimit;
	Node lastNode;
	
	public NullPath(int n) {
		newNullPath(n, true);
	}
	
	public NullPath() {
		
	}
	
	public static void main(String[] args) {
		NullPath np = new NullPath(Integer.parseInt(args[0]));
	}
	
	private void printPath() {
		Collections.reverse(result);
		System.out.println(result);
	}

	public void newNullPath(int n, boolean print) {
		weights = new int[n][n];
		result = new ArrayList<Node>();
		found = false;
		nodeList = new ArrayList<Node>();
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
		result = ds.extractUsedNodesFrom(nodeList.get(weights.length-1));
		
		if(print) {
			printMatrix();
			
			if(!found) {
				 System.out.println("There is no solution");
				
			}
			else {
				printPath();
				System.out.println("Total weight = " + pruneLimit);
			}
		}
		
		
	}
	
	public boolean branchAndBound(Node rootNode) {
		ds.insert(rootNode);
		pruneLimit = Integer.MAX_VALUE;
		while (!ds.empty()) {
			if(!(ds.estimateBest() < pruneLimit)) {
				ds.extractBestNode();
				continue;
			}
			Node node = ds.extractBestNode();
			pruneLimit = node.pruneLimit(nodeList.size() - node.depth - 2, threshold);
			ArrayList<Node> children = node.expand(ds, nodeList, weights);
			for (Node child : children) {
				if (child.isSolution(nodeList.size())) {
					int cost =  Math.abs(child.getHeuristicValue() + weights[child.getID()][nodeList.get(nodeList.size()-1).getID()]);
					if (cost <pruneLimit) {
						pruneLimit = cost;
						nodeList.get(nodeList.size()-1).parentID = child.getID();
						found = true;
						lastNode = child;
					}
				}
				else {
					if (child.getHeuristicValue() < pruneLimit) {
						ds.insert(child);
					}
				}
			}
			if(found) {
				ds.addUsedNode(lastNode);
				return true;
			}
		}
		return found;
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

