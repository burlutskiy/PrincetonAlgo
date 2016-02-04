import java.util.BitSet;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private final WeightedQuickUnionUF uf;
	private final WeightedQuickUnionUF ufBase;
	private final int n;
	private final BitSet bsites;

	public Percolation(int N) {
		if (N <= 0) 
			throw new IllegalArgumentException();
		this.uf = new WeightedQuickUnionUF(N * N + 2);
		this.ufBase = new WeightedQuickUnionUF(N * N + 1);
		this.n = N;
		this.bsites = new BitSet(n * n);
		for (int i = 1; i <= N; i++) {
			uf.union(0, i);
			ufBase.union(0, i);
			uf.union(n * n + 1, n * (n - 1) + i);
		}
	}

	public void open(int i, int j) {
		checkBounds(i, j);
		if (!bsites.get((i - 1) * n + (j - 1))) {
			bsites.set((i - 1) * n + (j - 1));
			int p = (int) (i - 1) * n + j;
			if (j - 2 >= 0 && bsites.get((i - 1) * n + (j - 2)))// 1. check left
				openSite(p, (int) (i - 1) * n + (j - 1));
			if (i - 2 >= 0 && bsites.get((i - 2) * n + (j - 1)))// 2. check top
				openSite(p, (int) (i - 2) * n + j);
			if (j < n && bsites.get((i - 1) * n + (j))) // 3. check right
				openSite(p, (int) (i - 1) * n + (j + 1));
			if (i < n && bsites.get((i) * n + (j - 1))) // 4. check bottom
				openSite(p, (int) i * n + j);
		}
	}

	private void openSite(int p, int q) {
		if (!ufBase.connected(p, q)) {
			uf.union(p, q);
			ufBase.union(p, q);
		}
	}

	private void checkBounds(int i, int j) {
		if (i <= 0 || j <= 0 || i > n || j > n)
			throw new IndexOutOfBoundsException();
	}

	public boolean isOpen(int i, int j) { // is site (row i, column j) open?
		checkBounds(i, j);
		return bsites.get((i - 1) * n + (j - 1));
	}

	public boolean isFull(int i, int j) { // is site (row i, column j) full?
		checkBounds(i, j);
		return bsites.get((i - 1) * n + (j - 1)) && ufBase.connected(0, (int) (i - 1) * n + j);
	}

	public boolean percolates() {
		return uf.connected(0, n * n + 1) && !bsites.isEmpty();
	}

}