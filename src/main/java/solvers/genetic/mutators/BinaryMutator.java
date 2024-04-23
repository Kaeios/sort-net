package solvers.genetic.mutators;

import solvers.genetic.mutations.CoupleMutationOperator;
import solvers.genetic.pickers.FixedSizePicker;
import solvers.genetic.pickers.Picker;

import java.util.Arrays;
import java.util.List;

public class BinaryMutator<T> implements Mutator<T> {

    private final CoupleMutationOperator<T> operator;
    private final Picker<T> picker = new FixedSizePicker<>(2);

    public BinaryMutator(CoupleMutationOperator<T> operator) {
        this.operator = operator;
    }

    @Override
    public List<T> performMutation(List<T> state) {
        List<T> subset = picker.pick(state);

        CoupleMutationOperator.Pair<T> result = operator.mutate(subset.get(0), subset.get(1));

        return Arrays.asList(result.x(), result.y());
    }

}
