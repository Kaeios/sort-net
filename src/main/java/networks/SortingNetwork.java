package networks;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

public class SortingNetwork {

    private final int inputCount;
    private final List<Wire> wires = new LinkedList<>();

    public SortingNetwork(int inputCount) {
        this.inputCount = inputCount;
    }

    public List<Wire> getWires() {
        return wires;
    }

    public void popWire() {
        wires.remove(wires.size() - 1);
    }

    public void addWire(Wire wire) {
        this.wires.add(wire);
    }

    public boolean isValid() {
        long max = (long) Math.pow(2, inputCount);
        return LongStream.range(0, max).allMatch(this::canSort);
    }

    public boolean canSort(long input) {
        return isSorted(transform(input));
    }

    public int score() {
        long max = (long) Math.pow(2, inputCount);
        int worstScore = (int) LongStream.range(0, max).map(this::transform).map(this::countMisplaced).max().getAsLong();

        return worstScore / 2 + (worstScore % 2);
    }

    public int countMisplaced(long output) {
        int oneCount = Long.bitCount(output);
        if(oneCount == 0) return 0;
        int mask = (2 << oneCount-1) - 1;

        return Long.bitCount(output ^ mask);
    }

    public long transform(long input) {
        for (Wire wire : wires) {
            input = swapBits(input, wire);
        }

        return input;
    }

    private long swapBits(long input, Wire wire) {
        long bit1 = (input >> wire.x()) & 1;
        long bit2 = (input >> wire.y()) & 1;

        if(bit1 == 1) return input;

        long tmp = (bit1 ^ bit2);
        tmp = (tmp << wire.x()) | (tmp << wire.y());

        return input ^ tmp;
    }

    private boolean isSorted(long output) {
        if(output == 0) return true;
        if(output == 1) return true;
        return ((output+1) & (output)) == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Inputs = ").append(inputCount)
                .append(", Size = ").append(wires.size());

        for (int j = 0; j < inputCount; j++) {
            sb.append("\n");
            for (Wire wire : wires) {
                String sym = (wire.x() == j || wire.y() == j) ? "X" : "-";
                sb.append(sym);
            }
        }

        return sb.toString();
    }

    public int getSize() {
        return this.wires.size();
    }

    public int getInputCount() {
        return inputCount;
    }

    public SortingNetwork clone() {
        SortingNetwork newNet = new SortingNetwork(inputCount);
        newNet.wires.addAll(this.wires);

        return newNet;
    }

}
