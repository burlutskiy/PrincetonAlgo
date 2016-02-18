import java.util.BitSet;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 
 * @author Burlutskiy Alexey
 *
 */
public class Percolation2 {
	private final WeightedQuickUnionUF uf;
	private final int n;
	private final BitSet openSites;
	private final BitSet bottomCC;
	private boolean percolates;
	private long ufoperations;

	public Percolation2(int N) {
		if (N <= 0)
			throw new IllegalArgumentException();
		this.uf = new WeightedQuickUnionUF(N * N + 1);
		this.n = N;
		this.openSites = new BitSet(n * n);
		this.bottomCC = new BitSet(n * n);
		for (int i = 1; i <= N; i++) {
			uf.union(0, i);
			ufoperations++;
		}
		bottomCC.set(n * (n - 1) + 1, n * n + 1);
	}

	public void open(int i, int j) {
		checkBounds(i--, j--);
		int p = (int) i * n + j + 1;
		if (!isOpen(p)) {
			openSites.set(p);
			if (j != 0 && isOpen(p - 1)) // 1. check left
				openSite(p, p - 1);
			if (i != 0 && isOpen((i - 1) * n + j + 1)) // 2. check top
				openSite(p, (i - 1) * n + j + 1);
			if (j != n - 1 && isOpen(i * n + j + 2)) // 3. check right
				openSite(p, i * n + j + 2);
			if (i != n - 1 && isOpen((i + 1) * n + j + 1)) // 4. check bottom
				openSite(p, (i + 1) * n + j + 1);
		}
	}

	private void openSite(int p, int q) {
		int ccp = uf.find(p);
		int ccq = uf.find(q);
		ufoperations+=2;
		if (ccp != ccq) {
			if (connectedToBottom(ccp) && !connectedToBottom(ccq))
				connectToBottom(ccq);
			else if (!connectedToBottom(ccp) && connectedToBottom(ccq))
				connectToBottom(ccp);
			uf.union(p, q);
			ufoperations+=3;
		}
	}

	private void connectToBottom(int cc) {
		bottomCC.set(cc);
	}

	private boolean connectedToTop(int cc) {
		ufoperations++;
		return uf.find(0) == cc;
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
		return openSites.get(i * n + j + 1);
	}

	private boolean isOpen(int p) {
		return openSites.get(p);
	}

	public boolean isFull(int i, int j) { // is site (row i, column j) full?
		checkBounds(i--, j--);
		return openSites.get(i * n + j + 1) && ufoperations++ >= 0 && connectedToTop(uf.find(i * n + j + 1));
	}

	public boolean percolates() {
		ufoperations++;
		return percolates = percolates || (ufoperations++ >= 0 && (bottomCC.get(uf.find(0)) || (!openSites.isEmpty() && n == 1)));
	}

	public static void main(String[] args) {
		int n = 50;
		Percolation2 perc = new Percolation2(n);
		int[] operations = new int[]{0,1,2}; 
		while(perc.ufoperations < 100000000 && perc.openSites.cardinality() != n*n ){
			int i = StdRandom.uniform(n) + 1;
			int j = StdRandom.uniform(n) + 1;
			StdRandom.shuffle(operations);
			for (int op: operations) {
				if(op == 0){
					perc.isFull(i, j);
				}
				else if(op == 1){ 
					perc.percolates();
				}
				else if(op == 2){ 
					perc.open(i, j);
				}
			}
		}
		StdOut.println(perc.ufoperations);
	}
}