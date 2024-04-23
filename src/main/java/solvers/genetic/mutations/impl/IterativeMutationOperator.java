package solvers.genetic.mutations.impl;

import networks.SortingNetwork;
import solvers.genetic.mutations.SingleMutationOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IterativeMutationOperator implements SingleMutationOperator<SortingNetwork> {

    private final List<SingleMutationOperator<SortingNetwork>> operators = new ArrayList<>();

    private final int min;
    private final int max;

    public IterativeMutationOperator(int min, int max, SingleMutationOperator<SortingNetwork>... operators) {
        this.min = min;
        this.max = max;
        this.operators.addAll(Arrays.asList(operators));
    }

    @Override
    public SortingNetwork mutate(SortingNetwork network) {
        SortingNetwork clone = network.clone();
        int steps = min + RNG.nextInt(max - min);

        for (int i = 0; i < steps; i++) {
            SingleMutationOperator<SortingNetwork> selected = operators.get(RNG.nextInt(operators.size()));
            clone = selected.mutate(clone);
        }

        return clone;
    }

}
