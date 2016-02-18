import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author alexey
 *
 */
public class FastCollinearPoints {
//	private final List<LineSegment> lineSegments = new ArrayList<>();
	private final List<Tuple> list = new ArrayList<>();
	private static class Tuple{
		private final Point p1;
		private final Point p2;
		public Tuple(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
		@Override
		public String toString() {
			return "[" + p1 + "," + p2 + "]";
		}
		
	}
	
	public FastCollinearPoints(Point[] points) {
		int n = points.length;
		Point[] original_points = points.clone();
//		checkInputs(original_points);
		if(n < 4)
			return;
		for (int i = 0; i < n; i++) {
			Point p = original_points[i];
//			if(belongsToOneOfSegments(p))
//				continue;
			Comparator<Point> slopeOrder = p.slopeOrder();
			Arrays.sort(points, slopeOrder);
			double slope = points[0].slopeTo(points[1]);
			int count = 1;
			for (int j = 2; j < n; j++) {
				double nslope = points[0].slopeTo(points[j]);
				if (Double.compare(slope, nslope) == 0)
					count++;
				else{
					checkCountAndAddSegment(points, slope, count, j);
					count = 1;
					slope = nslope;
				}
			}
			checkCountAndAddSegment(points, slope, count, n );
		}
	}

	private void checkCountAndAddSegment(Point[] points, double slope, int count, int j) {
		if (count > 2) {
			Point[] cpoints = new Point[count + 1];
			cpoints[0] = points[0];
			System.arraycopy(points, j - count, cpoints, 1, count);
			Arrays.sort(cpoints);
//			if(!containsMinPointSlope(cpoints[0], slope)){
//				lineSegments.add(new LineSegment(cpoints[0], cpoints[count]));
				list.add(new Tuple(cpoints[0], cpoints[count]));
//			}
		}
	}

	private void checkInputs(Point[] points){
		Arrays.sort(points);
		for (int i = 1; i < points.length; i++) {
			if(points[i].compareTo(points[i-1])==0)
				throw new IllegalArgumentException();
		}
	}

//	private boolean belongsToOneOfSegments(Point p) {
//		for (MinPointSlope minPoint : segmentPoints) {
//			if(Double.compare(minPoint.p.slopeTo(p), minPoint.slope) == 0)
//				return true;
//		}
//		return false;
//	}
//	
//	private boolean containsMinPointSlope(Point point, double slope) {
//		for (MinPointSlope minPoint : segmentPoints) {
//			if(minPoint.p.compareTo(point) == 0 && Double.compare(slope, minPoint.slope) == 0)
//				return true;
//		}
//		return false;
//	}

	public int numberOfSegments() {
		return set.size();
//		return lineSegments.size();
	}

	public LineSegment[] segments() {
		LineSegment[] result = new LineSegment[set.size()];
		int i = 0;
		for (Tuple tuple : set) {
			result[i++] = new LineSegment(tuple.p1, tuple.p2);
		}
		return result;
	}

}