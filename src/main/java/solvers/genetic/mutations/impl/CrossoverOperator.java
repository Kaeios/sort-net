package solvers.genetic.mutations.impl;

import networks.SortingNetwork;
import solvers.genetic.mutations.CoupleMutationOperator;

import java.util.Random;

public class CrossoverOperator implements CoupleMutationOperator<SortingNetwork> {

    @Override
    public Pair<SortingNetwork> mutate(SortingNetwork x, SortingNetwork y) {
        int maxSectionSizeX = Math.max(1, x.getSize() / 3);
        int maxSectionSizeY = Math.max(1, y.getSize() / 3);

        int sectionSizeX = RNG.nextInt(maxSectionSizeX) + 1;
        int sectionSizeY = RNG.nextInt(maxSectionSizeY) + 1;

        int maxOffsetX = x.getSize() - sectionSizeX;
        int maxOffsetY = y.getSize() - sectionSizeY;

        int offsetX = RNG.nextInt(maxOffsetX);
        int offsetY = RNG.nextInt(maxOffsetY);

        SortingNetwork newX = x.clone();
        SortingNetwork newY = y.clone();

        for (int i = 0; i < sectionSizeX; i++)
            newX.getWires().remove(offsetX);

        for (int i = 0; i < sectionSizeY; i++)
            newY.getWires().remove(offsetY);

        for (int sizeX = sectionSizeX - 1; sizeX >= 0; sizeX--) {
            newY.getWires().add(offsetY, x.getWires().get(offsetX + sizeX));
        }

        for (int sizeY = sectionSizeY - 1; sizeY >= 0; sizeY--) {
            newX.getWires().add(offsetX, y.getWires().get(offsetY + sizeY));
        }

        return new Pair<>(newX, newY);
    }

}
