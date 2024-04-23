package solvers;

import networks.SortingNetwork;
import networks.Wire;

public class IDAStarSolver implements NetworkSolver{

    private int maxDepth = 1;
    private SortingNetwork solution;

    @Override
    public SortingNetwork expand(SortingNetwork network) {
        while(solution == null) {
            System.out.println("maxDepth = " + maxDepth);
            solve(network, 0);
            maxDepth++;
        }

        return solution;
    }

    @Override
    public SortingNetwork computeSolution(int inputCount) {
        return expand(new SortingNetwork(inputCount));
    }

    public void solve(SortingNetwork network, int currentDepth) {
        if(solution != null) return;
        // Change with heuristic
        if(currentDepth > maxDepth) return;

        for (int i = 0; i < network.getInputCount(); i++) {
            for (int j = i + 1; j < network.getInputCount(); j++) {
                SortingNetwork newNet = network.clone();
                newNet.addWire(new Wire(i, j));

                int score = newNet.score();
                if(score == 0) {
                    System.out.println("Found !");
                    this.solution = newNet;
                    return;
                } else if (currentDepth + score > maxDepth) continue;

                solve(newNet, currentDepth+1);
            }
        }
    }
}
