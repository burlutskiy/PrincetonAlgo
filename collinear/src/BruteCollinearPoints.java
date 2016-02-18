import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
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
	private final List<PointTuple> segmentPoints = new ArrayList<>();
	private static class PointTuple {
		private final Point p1;
		private final Point p2;
		private final double slope;

		public PointTuple(Point p1, Point p2, double slope) {
			super();
			this.p1 = p1;
			this.p2 = p2;
			this.slope = slope;
		}

		@Override
		public String toString() {
			return "[" + p1 + "," + p2  + "," + slope + "]";
		}
	}

	public BruteCollinearPoints(Point[] points) {
		select4TupleAndVisit(points);
		PointTuple[] tuples = segmentPoints.toArray(new PointTuple[segmentPoints.size()]);
		Arrays.sort(tuples, new Comparator<PointTuple>() {
			public int compare(PointTuple o1, PointTuple o2) {
				return Double.compare(o1.slope, o2.slope);
			}
		});
		double slope = tuples[0].slope;
		Point min = tuples[0].p1;
		Point max = tuples[0].p2;
		for (int i = 1; i < tuples.length; i++) {
			if(Double.compare(tuples[i].slope, slope) == 0){
				if(tuples[i].p1.compareTo(min) < 0)
					min = tuples[i].p1; 
				if(tuples[i].p2.compareTo(max) > 0)
					max = tuples[i].p2; 
			} else {
				lineSegments.add(new LineSegment(min, max));
				min = tuples[i].p1;
				max = tuples[i].p2;
			}
		}
		lineSegments.add(new LineSegment(min, max));
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
			segmentPoints.add(new PointTuple(quadruple[0], quadruple[3], sl1));
		}
	}

	private void select4TupleAndVisit(Point[] points) {
		int n = points.length;
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

	public int numberOfSegments() {
		return lineSegments.size();
	}

	public LineSegment[] segments() {
		return lineSegments.toArray(new LineSegment[lineSegments.size()]);
	}

}