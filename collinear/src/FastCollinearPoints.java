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
	public FastCollinearPoints(Point[] points) {
		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			Comparator<Point> slopeOrder = p.slopeOrder();
			Arrays.sort(points, slopeOrder);
			int j = 1;
			double slope;
			do{
				slope = points[0].slopeTo(points[j]);
			}
			while(Double.compare(slope, points[0].slopeTo(points[++j])) == 0);
			if(j > 4){
				Arrays.sort(points, 0, j);
				lineSegments.add(new LineSegment(quadruple[0], quadruple[3]));
			}
			System.out.println();
		}
	}

	public int numberOfSegments() {
		return -1;
	}

	public LineSegment[] segments() // the line segments
	{
		return null;
	}

}