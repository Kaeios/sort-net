package solvers;

import networks.SortingNetwork;
import networks.Wire;

public class IDDSolver implements NetworkSolver {

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
        if(currentDepth > maxDepth) return;

        for (int i = 0; i < network.getInputCount(); i++) {
            for (int j = i + 1; j < network.getInputCount(); j++) {
                SortingNetwork newNet = network.clone();
                newNet.addWire(new Wire(i, j));

                if(newNet.isValid()) {
                    this.solution = newNet;
                    return;
                }

                solve(newNet, currentDepth+1);
            }
        }
    }

}
