package solvers.genetic.pickers;

import java.util.*;

public class FixedSizePicker<T> implements Picker<T> {

    private final int size;

    public FixedSizePicker(int size) {
        this.size = size;
    }

    @Override
    public List<T> pick(List<T> collection) {

        List<T> shuffled = new ArrayList<>(collection);
        Collections.shuffle(collection);

        return shuffled.subList(0, size);
    }

}
