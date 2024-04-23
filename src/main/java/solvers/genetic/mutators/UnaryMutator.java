package solvers.genetic.mutators;

import solvers.genetic.mutations.SingleMutationOperator;
import solvers.genetic.pickers.FixedSizePicker;
import solvers.genetic.pickers.Picker;

import java.util.Collections;
import java.util.List;

public class UnaryMutator<T> implements Mutator<T> {

    private final SingleMutationOperator<T> operator;
    private final Picker<T> picker = new FixedSizePicker<>(1);

    public UnaryMutator(SingleMutationOperator<T> operator) {
        this.operator = operator;
    }

    @Override
    public List<T> performMutation(List<T> state) {
        return Collections.singletonList(operator.mutate(picker.pick(state).get(0)));
    }

}
