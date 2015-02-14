public class PercolationStats {
    private double mean = 0;
    private double stddev = 0;
    private double confidenceLo = 0;
    private double confidenceHi = 0;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }
        double[] fractions = new double[t];
        int count = 0;
        do {
            Percolation percolation = new Percolation(n);
            int openSites = 0;
            do {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    openSites++;
                }
            } while (!percolation.percolates());
            fractions[count] = openSites / (double) (n * n);
        } while (++count < t);
        mean = StdStats.mean(fractions);
        stddev = StdStats.stddev(fractions);
        confidenceLo = mean - (1.96 * stddev) / Math.sqrt(t);
        confidenceHi = mean + (1.96 * stddev) / Math.sqrt(t);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);
        StdOut.print("mean\t\t\t= ");
        StdOut.println(stats.mean());
        StdOut.print("stddev\t\t\t= ");
        StdOut.println(stats.stddev());
        StdOut.print("95% confidence interval\t= ");
        StdOut.print(stats.confidenceLo);
        StdOut.print(", ");
        StdOut.println(stats.confidenceHi);
    }
}