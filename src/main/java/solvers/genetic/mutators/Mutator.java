package solvers.genetic.mutators;

import java.util.List;

public interface Mutator<T> {

    List<T> performMutation(List<T> state);

}
