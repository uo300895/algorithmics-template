package s5;

import java.util.Random;

public class MinimumPathsTimes {
	static String[] v; //node vector
	static int[][] weights; //weight matrix
	static int[][] costs; //Floyd's paths cost matrix
	static int[][] p; //predecessor matrix (steps) in Floyd paths
	
	public static void main(String arg[]) {
		long timeElapsed = 0;
		for (int n = 200; n < 100000; n*=2) {
			long start = System.nanoTime();
			
				v = new String[n];
				for (int i = 0; i < n; i++)
					v[i] = "NODE" + i;
				weights = new int[n][n];
				costs = new int[n][n];
				p = new int[n][n];
				fillInWeights(weights);
				floyd(weights, costs, p);
				for (int source = 0; source <= n-1; source++)
					for (int target = 0; target <= n-1; target++)
						if (source != target) {
							minimumPath(v, weights, costs, p, source, target);
						}
			
			
			long end = System.nanoTime();
			timeElapsed = (end - start) / 1_000_000;
			System.out.print("n= "+n+", time= "+ timeElapsed+"\n");
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
		
		path(v,steps, source, target);
	}

	/* IT IS RECURSIVE and WORST CASE is O(n), IT IS O(n) if you write all nodes */
	static void path(String[] v, int[][] steps, int i, int j) {
		if (steps[i][j] != 0) {
			path(v, steps, i , steps[i][j]);
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
