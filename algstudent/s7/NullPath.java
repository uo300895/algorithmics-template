package s7;
import java.util.ArrayList;
import java.util.Random;

import utils.BranchAndBound;
import utils.Node;

public class NullPath extends Node{
	private int[] path;
    private boolean[] visited;
    private int pathIndex;
    private int cost;

    static int[][] weights;
    static int n;
    static int origin = 0;
    static int target;

    public static void main(String[] args) {
        n = Integer.parseInt(args[0]);
        weights = new int[n][n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rand.nextBoolean()) {
                    weights[i][j] = rand.nextInt(10, 100);  
                } else {
                    weights[i][j] = rand.nextInt(-99, -9);  
                }
            }
        }
        BranchAndBound solver = new BranchAndBound();
        solver.branchAndBound(new NullPath());
       // solver.printSolutionTrace();
    }
    
    public NullPath() {
        path = new int[n];
        visited = new boolean[n];
        path[0] = origin;
        visited[origin] = true;
        pathIndex = 1;
        cost = 0;
        target = n - 1;
        calculateHeuristicValue();
    }

    private NullPath(NullPath parent, int nextNode) {
        this.path = parent.path.clone();
        this.visited = parent.visited.clone();
        this.pathIndex = parent.pathIndex + 1;
        this.cost = parent.cost + weights[parent.path[parent.pathIndex - 1]][nextNode];

        this.path[this.pathIndex - 1] = nextNode;
        this.visited[nextNode] = true;

        this.parentID = parent.getID();
        this.depth = parent.depth + 1;
        calculateHeuristicValue();
    }

    @Override
    public void calculateHeuristicValue() {
        this.heuristicValue = Math.abs(cost);
    }

    @Override
    public ArrayList<Node> expand() {
        ArrayList<Node> children = new ArrayList<>();
        if (pathIndex >= n) return children;

        for (int i = 1; i < n - 1; i++) {
            if (!visited[i]) {
                children.add(new NullPath(this, i));
            }
        }

        if (pathIndex == n - 1) {
            children.add(new NullPath(this, target));
        }

        return children;
    }

    @Override
    public boolean isSolution() {
        if(pathIndex == n && path[n - 1] == target && cost >= -99 && cost <= 99)
        	return true ;
        else
        	return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Path: ");
        for (int i = 0; i < pathIndex; i++) {
            sb.append(path[i]).append(" ");
        }
        sb.append(" Cost: ").append(cost);
        return sb.toString();
    }

}
