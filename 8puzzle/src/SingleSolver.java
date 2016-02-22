import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class SingleSolver {
	public static void main(String[] args) {
		String filename = "test/puzzle4x4-49.txt";
		In in = new In(filename);
		int N = in.readInt();
		int[][] tiles = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tiles[i][j] = in.readInt();
			}
		}

		// solve the slider puzzle
		Board initial = new Board(tiles);
		Solver solver = new Solver(initial);
		StdOut.println(filename + ": " + solver.moves());
		System.out.println(solver.solution());
	}
}
