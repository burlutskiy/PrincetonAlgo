import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author alexey
 *
 */
public class BruteCollinearPoints {
	final Set<Point> set = new HashSet<>();
	
	public BruteCollinearPoints(Point[] points){
		for (Point point : points) {
			point.hashCode();//throw NPE
			if(!set.add(point))
				throw new java.lang.IllegalArgumentException();
		}
	}

	public int numberOfSegments() 
	{
		return -1;
	}

	public LineSegment[] segments() // the line segments
	{
		return null;
	}
}