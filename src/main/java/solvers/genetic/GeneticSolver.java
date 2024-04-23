package solvers.genetic;

import networks.SortingNetwork;
import networks.Wire;
import solvers.NetworkSolver;
import solvers.genetic.mutations.impl.*;
import solvers.genetic.mutators.BinaryMutator;
import solvers.genetic.mutators.Mutator;
import solvers.genetic.mutators.UnaryMutator;

import java.util.*;

public class GeneticSolver implements NetworkSolver {

    private static final Random RNG = new Random();

    private final int populationSize = 200;

    private final int inputSize;

    private final List<Mutator<SortingNetwork>> mutators = Arrays.asList(
            new UnaryMutator<>(
                    new IterativeMutationOperator(1, 3,
                            new EditOperator(),
                            new InsertOperator(),
                            new SwapOperator()
                    )
            ),
            new UnaryMutator<>(
                    new IterativeMutationOperator(1, 3,
                            new RemoveOperator()
                    )
            ),
            new BinaryMutator<>(new CrossoverOperator())
    );

    private List<SortingNetwork> population = new ArrayList<>(populationSize * 2);

    public GeneticSolver(int inputSize, int steps) {
        this.inputSize = inputSize;

        System.out.println("Generation de la population initiale...");

        createRandomPopulation();

        System.out.println("Fait !");

        System.out.println("Mutation de la population...");

        int best = Integer.MAX_VALUE;

        for (int i = 0; i < steps; i++) {
            SortingNetwork bestNetwork = this.population.get(0);
            if(bestNetwork.getSize() < best) {
                best = bestNetwork.getSize();
                System.out.println("Step = " + i + ", best = " + best);
                System.out.println(bestNetwork);
            }
            geneticStep();
        }

        System.out.println("Fait !");
    }

    public void geneticStep() {
        List<SortingNetwork> mutated = new ArrayList<>();

        for (Mutator<SortingNetwork> mutator : this.mutators) {
            mutated.addAll(mutator.performMutation(population));
        }

        for (SortingNetwork network : mutated) {
            if(!network.isValid()) continue;
            this.population.add(network);
        }

        this.population.sort(Comparator.comparingInt(SortingNetwork::getSize));
        List<SortingNetwork> bests = new ArrayList<>(this.population.subList(0, populationSize / 4));
        List<SortingNetwork> others = new ArrayList<>(this.population.subList(populationSize /4, population.size()));
        Collections.shuffle(others);

        this.population.clear();
        this.population.addAll(bests);
        this.population.addAll(others.subList(0, populationSize - populationSize / 4));
    }

    public void createRandomPopulation() {
        for (int i = 0; i < this.populationSize; i++) {
            SortingNetwork network = new SortingNetwork(this.inputSize);
            while(!network.isValid()) {
                int x1 = RNG.nextInt(this.inputSize);
                int x2;
                do {
                    x2 = RNG.nextInt(this.inputSize);
                } while (x1 == x2);

                network.addWire(new Wire(Math.min(x1, x2), Math.max(x1, x2)));
            }

            this.population.add(network);
        }

        population.sort(Comparator.comparingInt(SortingNetwork::getSize));
    }

    @Override
    public SortingNetwork expand(SortingNetwork network) {
        return this.population.get(0);
    }

    @Override
    public SortingNetwork computeSolution(int inputCount) {
        return this.population.get(0);
    }

}
