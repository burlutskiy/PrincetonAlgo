import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 
 * @author alexey
 *
 */
public class Board {
	final private int[][] blocks;
	final private int n;
	final private int ei, ej;
	// (where blocks[i][j] = block in row i, column j)
	// construct a board from an N-by-N array of
	// blocks
	public Board(int[][] blocks) {
		this.n = blocks.length;
		this.blocks = deepclone(blocks);
		int k = findEmptyBlock();
		ei = oneDemToTwoI(k);
		ej = oneDemToTwoJ(k);
	}

	private int[][] deepclone(int[][] b){
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) 
			System.arraycopy(b[i], 0, a[i], 0, n);
		return a;
	}
	
	private int twoDemToOne(int i, int j) {
		return i*n + j;
	}
	
	private int oneDemToTwoI(int x){
		return (int) (Math.ceil((x + 1) / (n+.0))  - 1);
	}

	private int oneDemToTwoJ(int x){
		return x % n;
	}
	
	// return coordinates in one dimention
	private int findEmptyBlock(){
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) 
				if(blocks[i][j] == 0)
					return twoDemToOne(i, j);
		throw new RuntimeException("empty block not present");
	}
	
	// board dimension N
	public int dimension() {
		return n;
	}

	// number of blocks out of place
	// O(N^2)
	public int hamming() {
		int count = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] != twoDemToOne(i, j) + 1 && blocks[i][j] != 0)
					count++;
			}
		return count;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int count = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				int v = blocks[i][j];
				if(v == 0)
					continue;
				int ri = oneDemToTwoI(v - 1);
				int rj = oneDemToTwoJ(v - 1);
				count+= Math.abs(ri - i) + Math.abs(rj - j);
				v = 0;
			}
		return count;
	}

	// is this board the goal board?
	// O(N^2)
	public boolean isGoal() {
		return hamming() == 0 &&  blocks[n - 1][n - 1] == 0;
	}

	// a board that is obtained by exchanging any pair of
	// blocks
	public Board twin() {
		final int[][] twinBlocks = deepclone(blocks);
		int k, l;
		do{
			k = StdRandom.uniform(n*n);
			l = StdRandom.uniform(n*n);
		}while(k == l || (oneDemToTwoI(k) == ei && oneDemToTwoJ(k) == ej) || (oneDemToTwoI(l) == ei && oneDemToTwoJ(l) == ej));
		swap(oneDemToTwoI(k), oneDemToTwoJ(k), oneDemToTwoI(l), oneDemToTwoJ(l), twinBlocks);
		return new Board(twinBlocks);
	}
	
	// does this board equal y?
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(blocks, other.blocks))
			return false;
		return true;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		List<Board> list = new ArrayList<>();
		Board left = leftNeighbor();
		Board right = rightNeighbor();
		Board top = topNeighbor();
		Board bottom = bottomNeighbor();
		if(left != null )
			list.add(left);
		if(right != null )
			list.add(right);
		if(top != null )
			list.add(top);
		if(bottom != null )
			list.add(bottom);
		return list;
	}

	private void swap(int i1, int j1, int i2, int j2, int[][] blocks){
		int tmp = blocks[i1][j1];
		blocks[i1][j1] = blocks[i2][j2];
		blocks[i2][j2] = tmp;		
	}
	
	private Board leftNeighbor() {
		if (ej == 0)
			return null;
		return swapAndGetBoard(ei, ej, ei, ej - 1);
	}

	private Board rightNeighbor() {
		if (ej == n - 1)
			return null;
		return swapAndGetBoard(ei, ej, ei, ej + 1);
	}

	private Board topNeighbor() {
		if (ei == 0)
			return null;
		return swapAndGetBoard(ei, ej, ei - 1, ej);
	}

	private Board bottomNeighbor() {
		if (ei == n - 1)
			return null;
		return swapAndGetBoard(ei, ej, ei + 1, ej);
	}

	private Board swapAndGetBoard(int i1, int j1, int i2, int j2) {
		int[][] nblocks = deepclone(blocks);
		swap(i1, j1, i2, j2, nblocks);
		return new Board(nblocks);
	}
	
	// string representation of this board (in the
	// output format specified below)
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(n).append("\n");
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				sb.append(blocks[i][j]);
				if(j < n - 1)
					sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 9; i++) {
			System.out.println(i % 10);
		}
	}

}