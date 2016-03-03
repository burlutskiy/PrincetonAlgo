import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

	@Test
	public void testSlopeTo() {
		Point p1 = new Point(0, 0);
		Assert.assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(p1), 0); // degenerate line segment (between a point and itself) 
		Assert.assertEquals(0, new Point(1, 1).slopeTo(new Point(2, 1)), 0); //horizontal line
		Assert.assertEquals(Double.POSITIVE_INFINITY, new Point(1, 1).slopeTo(new Point(1, 2)), 0); //vertical line
		Assert.assertEquals(1, new Point(1, 1).slopeTo(new Point(2, 2)), 0);
		Assert.assertEquals(0.243, new Point(19000, 10000).slopeTo(new Point(1234, 5678)), 0.001);
		Assert.assertEquals(1, new Point(3, 4).slopeTo(new Point(20, 21)), 0);
		Assert.assertEquals(-1, new Point(10, 0).slopeTo(new Point(0, 10)), 0);
	}
	
	@Test
	public void testSlopeOrder() {
		Point p00 = new Point(0, 0);
		Point p11 = new Point(10, 10);
		Point[] p = new Point[] { new Point(0, 0), new Point(0, 10), new Point(0, 20), new Point(10, 0), new Point(10, 10), new Point(10, 20),
				new Point(20, 0), new Point(20, 10), new Point(20, 20), new Point(11, 200)};
		Arrays.sort(p, p11.slopeOrder());
		System.out.println(Arrays.toString(p));
		for (Point point : p) {
			System.out.println(p11.slopeTo(point));
		}
	}
}
