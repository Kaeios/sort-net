package solvers.genetic.mutations.impl;

import networks.SortingNetwork;
import solvers.genetic.mutations.SingleMutationOperator;

import java.util.Collections;

public class SwapOperator implements SingleMutationOperator<SortingNetwork> {

    @Override
    public SortingNetwork mutate(SortingNetwork network) {

        SortingNetwork cloned = network.clone();

        int x1 = RNG.nextInt(cloned.getSize());
        int x2;
        do {
            x2 = RNG.nextInt(cloned.getSize());
        } while(x1 == x2);

        Collections.swap(cloned.getWires(), x1, x2);

        return cloned;
    }

}
