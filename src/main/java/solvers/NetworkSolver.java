package solvers;

import networks.SortingNetwork;

public interface NetworkSolver {

    SortingNetwork expand(SortingNetwork network);
    SortingNetwork computeSolution(int inputCount);

}
