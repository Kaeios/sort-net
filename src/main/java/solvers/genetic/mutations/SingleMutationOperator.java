package solvers.genetic.mutations;

import networks.SortingNetwork;

import java.util.Random;

public interface SingleMutationOperator<T> {

    static final Random RNG = new Random();

    T mutate(T network);

}
