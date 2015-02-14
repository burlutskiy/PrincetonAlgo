public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final int n;
    private final boolean[][] sites;

    public Percolation(int N) {
        // create N-by-N grid, with all sites blocked
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.uf = new WeightedQuickUnionUF(N * N + 2);
        this.n = N;
        this.sites = new boolean[n][n];
        for (int i = 1; i <= N; i++) {
            uf.union(0, i);
            uf.union(n * n + 1, n * (n - 1) + i);
        }
    }

    public void open(int i, int j) {
        // open site (row i, column j) if it is not
        // open already
        checkBounds(i, j);
        if (!sites[i - 1][j - 1]) {
            sites[i - 1][j - 1] = true;
            int p = (int) (i - 1) * n + j;
            // 1. check left
            if (j - 2 >= 0 && sites[i - 1][j - 2]) {
                int q = (int) (i - 1) * n + (j - 1);
                if (!uf.connected(p, q)) {
                    uf.union(p, q);
                }
            }
            // 2. check top
            if (i - 2 >= 0 && sites[i - 2][j - 1]) {
                int q = (int) (i - 2) * n + j;
                if (!uf.connected(p, q)) {
                    uf.union(p, q);
                }
            }
            // 3. check right
            if (j < n && sites[i - 1][j]) {
                // printSites();
                int q = (int) (i - 1) * n + (j + 1);
                if (!uf.connected(p, q)) {
                    uf.union(p, q);
                }
            }
            // 4. check bottom
            if (i < n && sites[i][j - 1]) {
                int q = (int) i * n + j;
                if (!uf.connected(p, q)) {
                    uf.union(p, q);
                }
            }
        }
    }

    private void checkBounds(int i, int j) {
        if (i <= 0 || j <= 0 || i > n || j > n) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isOpen(int i, int j) { // is site (row i, column j) open?
        checkBounds(i, j);
        return sites[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) { // is site (row i, column j) full?
        checkBounds(i, j);
        int p = 0;
        int q = (int) (i - 1) * n + j;
        return sites[i - 1][j - 1] && uf.connected(p, q);
    }

    public boolean percolates() {
        // does the system percolate?
        // for (int i = 1; i <= n; i++) {
        // if(sites[n-1][i - 1] == 1 && uf.connected(0, (n-1)* n + i)){
        // return true;
        // }
        // }
        // return false;
        return uf.connected(0, n * n + 1);
    }

}