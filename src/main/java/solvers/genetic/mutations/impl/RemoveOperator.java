package solvers.genetic.mutations.impl;

import networks.SortingNetwork;
import solvers.genetic.mutations.SingleMutationOperator;

public class RemoveOperator implements SingleMutationOperator<SortingNetwork> {

    @Override
    public SortingNetwork mutate(SortingNetwork network) {
        SortingNetwork copy = network.clone();

        int index = RNG.nextInt(network.getSize());
        copy.getWires().remove(index);

        return copy;
    }

}
