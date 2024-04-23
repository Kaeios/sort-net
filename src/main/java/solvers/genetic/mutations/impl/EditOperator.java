package solvers.genetic.mutations.impl;

import networks.SortingNetwork;
import networks.Wire;
import solvers.genetic.mutations.SingleMutationOperator;

public class EditOperator implements SingleMutationOperator<SortingNetwork> {

    @Override
    public SortingNetwork mutate(SortingNetwork network) {
        SortingNetwork copy = network.clone();

        int index = RNG.nextInt(network.getSize());
        copy.getWires().remove(index);

        int x = RNG.nextInt(network.getInputCount());
        int y = x;
        while(x == y) y = RNG.nextInt(network.getInputCount());

        copy.getWires().add(index, new Wire(x, y));

        return copy;
    }

}
