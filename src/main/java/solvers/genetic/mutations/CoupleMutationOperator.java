package solvers.genetic.mutations;

import java.util.Random;

public interface CoupleMutationOperator<T> {

    static final Random RNG = new Random();

    record Pair<T>(T x, T y){};

    Pair<T> mutate(T x, T y);


}
