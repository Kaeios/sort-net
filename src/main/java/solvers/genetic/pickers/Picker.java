package solvers.genetic.pickers;

import java.util.Collection;
import java.util.List;

public interface Picker<T> {

    List<T> pick(List<T> collection);

}
