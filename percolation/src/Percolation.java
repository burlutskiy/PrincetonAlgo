import java.util.BitSet;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private final WeightedQuickUnionUF uf;
	private final int n;
	private final BitSet openSites;
	private final BitSet topCC;
	private final BitSet bottomCC;
	private boolean percolates;

	public Percolation(int N) {
		if (N <= 0)
			throw new IllegalArgumentException();
		this.uf = new WeightedQuickUnionUF(N * N);
		this.n = N;
		this.openSites = new BitSet(n * n);
		this.topCC = new BitSet(n * n);
		this.bottomCC = new BitSet(n * n);
		topCC.set(0, n);
		bottomCC.set(n * (n - 1), n * n);
	}

	public void open(int i, int j) {
		checkBounds(i--, j--);
		int p = (int) i * n + j;
		if (!isOpen(p)) {
			openSites.set(p);
			if (j != 0 && isOpen(i * n + j - 1)) // 1. check left
				openSite(p, i * n + j - 1);
			if (i != 0 && isOpen((i - 1) * n + j)) // 2. check top
				openSite(p, (i - 1) * n + j);
			if (j != n - 1 && isOpen(i * n + (j + 1))) // 3. check right
				openSite(p, i * n + (j + 1));
			if (i != n - 1 && isOpen((i + 1) * n + j)) // 4. check bottom
				openSite(p, (i + 1) * n + j);
		}
	}

	private void openSite(int p, int q) {
		if (!uf.connected(p, q)) {
			int ccp = uf.find(p);
			int ccq = uf.find(q);
			if (!percolates && (connectedToTop(ccp) || connectedToTop(ccq)) 
					&& (connectedToBottom(ccp) || connectedToBottom(ccq)))
				percolates = true;
			if (connectedToTop(ccp) && !connectedToTop(ccq))
				connectToTop(ccq);
			else if (!connectedToTop(ccp) && connectedToTop(ccq))
				connectToTop(ccp);
			if (connectedToBottom(ccp) && !connectedToBottom(ccq))
				connectToBottom(ccq);
			else if (!connectedToBottom(ccp) && connectedToBottom(ccq))
				connectToBottom(ccp);
			uf.union(p, q);
		}
	}

	private void connectToTop(int cc) {
		topCC.set(cc);
	}

	private void connectToBottom(int cc) {
		bottomCC.set(cc);
	}

	private boolean connectedToTop(int cc) {
		return topCC.get(cc);
	}

	private boolean connectedToBottom(int cc) {
		return bottomCC.get(cc);
	}

	private void checkBounds(int i, int j) {
		if (i <= 0 || j <= 0 || i > n || j > n)
			throw new IndexOutOfBoundsException();
	}

	public boolean isOpen(int i, int j) { // is site (row i, column j) open?
		checkBounds(i--, j--);
		return openSites.get(i * n + j);
	}

	private boolean isOpen(int p) {
		return openSites.get(p);
	}

	public boolean isFull(int i, int j) { // is site (row i, column j) full?
		checkBounds(i--, j--);
		return connectedToTop(uf.find(i * n + j)) && openSites.get(i * n + j);
	}

	public boolean percolates() {
		return percolates || (!openSites.isEmpty() && n == 1);
	}

}