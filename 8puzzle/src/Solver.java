import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 
 * @author alexey
 *
 */
public class Solver {
	private final class SearchNode implements Comparable<SearchNode>{
		final Board board;
		final SearchNode prev;
		final int moves;
		final int priority;
		public SearchNode(Board board, SearchNode prev, int moves) {
			this.board = board;
			this.prev = prev;
			this.moves = moves;
			this.priority = board.manhattan() + moves;
		}
		@Override
		public int compareTo(SearchNode that) {
			return  priority - that.priority;
		}
		@Override
		public String toString() {
			return "[" + board.manhattan() + ", " + moves + ",  " + priority + "]";
		}
		
	}
	// find a solution to the initial board (using the A* algorithm)
	private final MinPQ<SearchNode> queue;
	private final MinPQ<SearchNode> twinQueue;
	private final Stack<Board> solution = new Stack<>();
	public Solver(Board initial) {
		queue = new MinPQ<>();
		twinQueue = new MinPQ<>();
		if(initial == null)
			throw new NullPointerException();

		SearchNode node = new SearchNode(initial, null, 0);
		SearchNode twinNode = new SearchNode(initial.twin(), null, 0);
		queue.insert(node);
		twinQueue.insert(twinNode);
		do {
			node = queue.delMin();
			twinNode = twinQueue.delMin();
			if(node.board.isGoal() || twinNode.board.isGoal())
				break;
			for(Board neighbor : node.board.neighbors())
				if(node.prev == null || !node.prev.board.equals(neighbor))
					queue.insert(new SearchNode(neighbor, node, node.moves + 1));
			for(Board neighbor : twinNode.board.neighbors())
				if(twinNode.prev == null || !twinNode.prev.board.equals(neighbor))
					twinQueue.insert(new SearchNode(neighbor, twinNode, twinNode.moves + 1));
		} while(true);
		
		if(twinNode.board.isGoal())
			return;
		
		while(node != null){
			solution.push(node.board);
			node = node.prev;
		}
	} 

	// is the initial board solvable?
	public boolean isSolvable() {
		return solution.size() > 0;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		return solution.size() - 1;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		return solution.size() == 0 ? null : solution;
	}

	public static void main(String[] args) {
	    In in = new In(args[0]);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

	    // solve the puzzle
	    Solver solver = new Solver(initial);

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}
	
}