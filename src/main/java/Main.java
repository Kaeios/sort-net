import networks.SortingNetwork;
import solvers.NetworkSolver;
import solvers.genetic.GeneticSolver;

// En partant de 0 : n = ² | 39s avec IDD - Déjà trop long pour n = 6
// Avec une base pré-construite de manière efficace (Green Filter) : n = 6 | 30s avec IDD

// IDD sans préfixe
// ----------------------
// | n | temps | taille |
// | 2 | 15ms  | 1      |
// | 3 | 15ms  | 3      |
// | 4 | 31ms  | 5      |
// | 5 | 50s   | 9      |
// ----------------------
// IDD avec préfixe
// ----------------------
// | 6 | 30s   | 12     |



// En le laissant tourner un peu : arrive à atteindre 19 pour n=8, ce qui est la solution optimale
// pour n=9, atteint 25, borne optimale
// Pour n=10, atteint 30, solution optimale : 29
// Pour n=11, atteint 38, solution optimale : 35
// pour n=12, atteint 45, solution optimale : 39
// pour n=13, atteint 53, solution la plus optimale connue : 45
public class Main {

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        NetworkSolver solver = new GeneticSolver(8, 1000000);
//        NetworkSolver solver = new IDDSolver();

        SortingNetwork net = solver.computeSolution(8);

        long t2 = System.currentTimeMillis();

        System.out.println(net);
        System.out.println((t2-t1)/1000 + "s " + (t2-t1)%1000 + "ms");
    }

}