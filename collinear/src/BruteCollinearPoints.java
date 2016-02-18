import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author alexey
 *
 */
public class BruteCollinearPoints {
	private final List<LineSegment> lineSegments = new ArrayList<>();
	private final static int t = 4;
	private final Point[] quadruple = new Point[4];

	public BruteCollinearPoints(Point[] points) {
		int n = points.length;
		checkInputs(points.clone());
		if(n < 4)
			return;
		int[] c = new int[t + 2];
		for (int j = 0; j < t; j++)
			c[j] = j;
		c[t] = n;
		c[t + 1] = 0;
		while (true) {
			visit(points, c);
			int j = 0;
			while (c[j] + 1 == c[j + 1]) {
				c[j] = j;
				j++;
			}
			if (j >= t)
				break;
			c[j]++;
		}
	}
	
	private void checkInputs(Point[] points){
		Arrays.sort(points);
		for (int i = 1; i < points.length; i++) {
			if(points[i].compareTo(points[i-1])==0)
				throw new IllegalArgumentException();
		}
	}
	
	private void visit(Point[] points, int[] c) {
		for (int i = 0; i < 4; i++)
			quadruple[i] = points[c[i]];
		Point p = quadruple[0];
		Point q = quadruple[1];
		Point r = quadruple[2];
		Point s = quadruple[3];
		double sl1 = p.slopeTo(q);
		double sl2 = p.slopeTo(r);
		double sl3 = p.slopeTo(s);
		if (Double.compare(sl1, sl2) == 0 && Double.compare(sl3, sl2) == 0 ) {
			Arrays.sort(quadruple);
			lineSegments.add(new LineSegment(quadruple[0], quadruple[3]));
		}
	}

	public int numberOfSegments() {
		return lineSegments.size();
	}

	public LineSegment[] segments() {
		return lineSegments.toArray(new LineSegment[lineSegments.size()]);
	}

}