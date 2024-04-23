import networks.SortingNetwork;
import networks.Wire;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SortingNetworkTest {

    @Test
    public void testSortingNetwork() {
        SortingNetwork network = new SortingNetwork(2);
        network.addWire(new Wire(0, 1));

        assertTrue(network.canSort(0));
        assertTrue(network.canSort(1));
        assertTrue(network.canSort(2));
        assertTrue(network.canSort(3));
    }

    @Test
    public void testSortingNetworkAll() {
        SortingNetwork network = new SortingNetwork(2);
        network.addWire(new Wire(0, 1));

        assertTrue(network.isValid());
    }


    @Test
    public void testSortingNetwork3x3() {
        SortingNetwork network = new SortingNetwork(3);
        network.addWire(new Wire(0, 2));
        network.addWire(new Wire(0, 1));
        network.addWire(new Wire(1, 2));

        assertTrue(network.isValid());
    }

    @Test
    public void testSortingNetworkAllWrong() {
        SortingNetwork network = new SortingNetwork(2);

        assertFalse(network.isValid());
    }

}
