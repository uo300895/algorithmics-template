package s5;

import java.util.Random;

public class MinimunPaths {
	static String[] v; //node vector
	static int[][] weights; //weight matrix
	static int[][] costs; //Floyd's paths cost matrix
	static int[][] p; //predecessor matrix (steps) in Floyd paths

	public static void main(String arg[]) {
		int n = 5; //nodes of example graph
		v = new String[n];
		for (int i = 0; i < n; i++)
			v[i] = "NODE" + i;

		weights = new int[n][n];
		costs = new int[n][n];
		p = new int[n][n];

		fillInWeights(weights); //weights for the example
		System.out.println("WEIGHT MATRIX IS:");
		printMatrix(weights);

		floyd(weights, costs, p);

		System.out.println("MINIMUM COST MATRIX IS:");
		printMatrix(costs);

		System.out.println("P MATRIX IS:");
		printMatrix(p);

		System.out.println();
		System.out.println("MINIMUM PATHS IN THE EXAMPLE GRAPH (for every pair of different nodes):");
		System.out.println();
		for (int source = 0; source <= n-1; source++)
			for (int target = 0; target <= n-1; target++)
				if (source != target) {
					System.out.print("FROM " + v[source] + " TO " + v[target] + " = ");
					minimumPath(v, weights, costs, p, source, target);
					if (costs[source][target] < 10000000)
						System.out.println("MINIMUM COST=" + costs[source][target]);
					System.out.println("**************");
				}

	}

	static void floyd(int[][] weights, int[][] costs, int[][] p) {
		int n = weights.length;
		for (int or = 0; or < n; or++) {
	        for (int tg = 0; tg < n; tg++) {
	        	costs[or][tg]=weights[or][tg];
	        }    
		}
		for (int pivot = 0; pivot < n; pivot++) {
	        for (int or = 0; or < n; or++) {
	            for (int tg = 0; tg < n; tg++) {
	                if (costs[or][tg] > costs[or][pivot] + costs[pivot][tg]) {
	                	costs[or][tg] = costs[or][pivot] + costs[pivot][tg];
	                    p[or][tg] = pivot;
	                }
	            }
	        }
	    }
	}

	static void minimumPath(String[] v, int[][] weights, int[][] costs, int[][] steps, int source, int target) {
		System.out.print(v[source] + "-->");
		path(v,steps, source, target);
		System.out.println(v[target]);
	}

	/* IT IS RECURSIVE and WORST CASE is O(n), IT IS O(n) if you write all nodes */
	static void path(String[] v, int[][] steps, int i, int j) {
		if (steps[i][j] != 0) {
			path(v, steps, i , steps[i][j]);
			System.out.print(v[steps[i][j]] + "-->");
			path(v, steps, steps[i][j], j);
			
		}
	}

	/* load the example cost matrix */
	static void fillInWeights(int[][] w) {
		Random random = new Random();
		int minWeight=10; 
		int maxWeight=99;
		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w.length; j++) {
				if (random.nextInt(0, 1+1) > 0.5)
					w[i][j] = random.nextInt(minWeight, maxWeight+1);
				else
					w[i][j] = 10000;
			}
		}
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
