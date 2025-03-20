package labs.en._25.algstudent.s5;

import java.util.Random;

//MINIMUM PATHS IN A GRAPH BY FLOYD-WARSHALL
//IT IS A SOLUTION BY DYNAMIC PROGRAMMING
//ITS TIME COMPLEXITY IS CUBIC O(n^3)
public class MinimumPaths {
	static String[] v; // node vector
	static int[][] weights; // weight matrix
	static int[][] costs; // Floyd's paths cost matrix
	static int[][] p; // predecessor matrix (steps) in Floyd paths
	
	static void basicAlgorithm(int nodes) {
		int n = nodes; // nodes of example graph
		v = new String[n];
		for (int i = 0; i < n; i++)
			v[i] = "NODE" + i;

		weights = new int[n][n];
		costs = new int[n][n];
		p = new int[n][n];

		pInitialize();
		
		fillInRandomWeights();
		floyd();
		for (int source = 0; source <= weights.length - 1; source++)
			for (int target = 0; target <= weights.length - 1; target++)
				if (source != target)
					minimumPath(source, target);
	}
	
	public static void main(String arg[]) {
		basicAlgorithm(5);
		System.out.println("WEIGHT MATRIX IS:");
		printMatrix(weights);

		System.out.println("MINIMUM COST MATRIX IS:");
		printMatrix(costs);

		System.out.println("P MATRIX IS:");
		printMatrix(p);

		System.out.println();
		System.out.println("MINIMUM PATHS IN THE EXAMPLE GRAPH (for every pair of different nodes):");
		System.out.println();
		for (int source = 0; source <= weights.length - 1; source++)
			for (int target = 0; target <= weights.length - 1; target++)
				if (source != target) {
					System.out.print("FROM " + v[source] + " TO " + v[target] + " = ");
					System.out.println(minimumPath(source, target));
					if (costs[source][target] < 10000000)
						System.out.println("MINIMUM COST=" + costs[source][target]);
					System.out.println("**************");
				}
			
	}

	private static void fillInRandomWeights() {
		Random p = new Random();
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights.length; j++) {
				if (p.nextBoolean()) {
					weights[i][j] = p.nextInt(10, 100);
				} else
					weights[i][j] = 10000000;
			}
		}
	}

	private static void pInitialize() {
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p.length; j++) {
				p[i][j] = -1;
			}
		}
	}

	/* ITERATIVE WITH CUBIC COMPLEXITY O(n^3) */
	static void floyd() {
		int n = weights.length;
		floydInitialize();
		for (int node = 0; node < n; node++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int newCost = costs[i][node] + costs[node][j];
					if (newCost < costs[i][j]) {
						costs[i][j] = newCost;
						p[i][j] = node;
					}
				}
			}
		}
	}

	private static void floydInitialize() {
		int n = weights.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				costs[i][j] = weights[i][j];
			}
		}
	}

	static String minimumPath(int source, int target) {
		if (costs[source][target] == 10000000)
			return "THERE IS NO PATH";
		return v[source] + path(source, target) + "-->" + v[target];
	}

	/* IT IS RECURSIVE and WORST CASE is O(n), IT IS O(n) if you write all nodes */
	static String path(int source, int target) {
		if (source == target)
			return "";
		int pivot = p[source][target];
		if (pivot == -1)
			return "";
		return path(source, pivot) + "-->" + v[pivot];
	}

	/* load the example cost matrix */
	static void fillInWeights() {
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights.length; j++) {
				weights[i][j] = 10000000;
				if (i == j)
					weights[i][j] = 0;
			}
		}
		weights[0][1] = 19;
		weights[0][2] = 10;
		weights[1][2] = 20;
		weights[2][1] = 19;
		weights[2][3] = 14;
		weights[3][0] = 27;
		weights[3][1] = 67;
		weights[3][2] = 21;
		weights[4][1] = 80;
	}

	/* print the cost matrix */
	static void printMatrix(int[][] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(String.format("%10s", a[i][j]));
			System.out.println();
		}
		System.out.println();
	}
}
