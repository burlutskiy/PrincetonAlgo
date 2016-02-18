import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author alexey
 *
 */
public class FastCollinearPoints {
	private final List<LineSegment> lineSegments = new ArrayList<>();

	public FastCollinearPoints(Point[] original_points) {
		final Point[] points = original_points.clone();
		checkInputs(points);
		int n = points.length;
		if(n < 4)
			return;
		for (int i = 0; i < n; i++) {
			Point p = original_points[i];
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
		if (count <= 2)
			return;
		Point min  = points[0];
		Point max  = checkOriginIsMinAndFindMax(min, points, j - count, j - 1);
		if (max != null)
			lineSegments.add(new LineSegment(min, max));
	}

	/**
	 *	return null if origin is not min 
	 */
	private Point checkOriginIsMinAndFindMax(Point origin, Point[] aux, int lo, int hi){
		Point max = origin;
		for (int i = lo; i <= hi; i++) {
			if(aux[i].compareTo(origin) < 0)
				return null;
			if(aux[i].compareTo(max) > 0)
				max = aux[i];
		}
		return max;
	}
	
	private void checkInputs(Point[] points){
		Arrays.sort(points);
		for (int i = 1; i < points.length; i++) {
			if(equalPoints(points[i], points[i-1]))
				throw new IllegalArgumentException();
		}
	}

	private boolean equalPoints(Point p, Point q) {
		return p.compareTo(q) == 0;
	}

	public int numberOfSegments() {
		return lineSegments.size();
	}

	public LineSegment[] segments() {
		return lineSegments.toArray(new LineSegment[lineSegments.size()]);
	}


}