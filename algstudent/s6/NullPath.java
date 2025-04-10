package s6;
import java.util.Random;

public class NullPath {
	static int[] path;
    static int[][] weights;
    static boolean[][] visitedEdges;
    static int minTolerance = -99;
    static int maxTolerance = 99;
    static int n;
    static int target;
    static int origin;
    static int count;
    
    public static void main(String[] args) {
        n = Integer.parseInt(args[0]);
        target = n - 1;
        origin = 0;
        weights = new int[n][n];
        visitedEdges = new boolean[n][n];
        path = new int[n];
        
        int minWeight = 10;
        int maxWeight = 99;
        Random random = new Random();
        
        for (int i = 0; i <= n-1; i++) {
			for (int j = 0; j <= n-1; j++) {
				if (random.nextInt(0, 1+1) > 0.5) {
					weights[i][j] = random.nextInt(-maxWeight,-minWeight);
				}else {
					weights[i][j] = random.nextInt(minWeight, maxWeight);
					
				}
				visitedEdges[i][j]=false;
			}
		}
        
        findNullPath();
    }
    
    private static void findNullPath() {
        boolean[] visitedNodes = new boolean[n];
        path[0] = origin;
        visitedNodes[origin] = true;
        count = Integer.MAX_VALUE;
        
        backtracking(1, 0, visitedNodes);
        
        if (count <= maxTolerance && count >= minTolerance) {
            //System.out.println("NullPath found with cost: " + count);
            //printPath();
        } else {
            //System.out.println("No valid NullPath found.");
        }
    }
    
    private static void backtracking(int index, int cost, boolean[] visitedNodes) {
        if (index==target) {
            int finalCost = cost + weights[path[n - 1]][target];
            path[target] = target;
            if (finalCost <= maxTolerance && finalCost >= minTolerance) {
                count = finalCost;
            }
            return;
        }
        
        for (int next = 1; next < n-1; next++) {
        	
            if (!visitedNodes[next]) {
                visitedNodes[next] = true;
                path[index] = next;
                backtracking(index + 1, cost + weights[path[index - 1]][next], visitedNodes);
                visitedNodes[next] = false;
            }
        }
    }
    
//    private static void printPath() {
//        for (int i = 0; i < n; i++) {
//            System.out.print(path[i] + (i < n - 1 ? " , " : "\n"));
//        }
//    }

}
