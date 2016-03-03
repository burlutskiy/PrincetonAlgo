import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

/**
 * 
 * @author alexey
 *
 */
public class PointSET {
	private final Set<Point2D> set;
	
	// construct an empty set of points
	public PointSET() {
		this.set = new TreeSet<>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return set.isEmpty();
	}

	// number of points in the set
	public int size() {
		return set.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		set.add(p);
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		return set.contains(p);
	}

	// draw all points to standard draw
	public void draw() {
		for(Point2D p : set)
			StdDraw.point(p.x(), p.y());
	}

	// all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		List<Point2D> list = new ArrayList<>();
		for(Point2D p : set)
			if(rect.contains(p))
				list.add(p);
		return list;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if(isEmpty())
			return null;
		Iterator<Point2D> iterator = set.iterator();
		Point2D nearest = iterator.next();
		while(iterator.hasNext()){
			Point2D cur = iterator.next();
			if(cur.distanceTo(p) < nearest.distanceTo(p))
				nearest = cur;
		}
		return nearest;
	}

	// unit testing of the methods (optional)
	public static void main(String[] args) {
//		PointSET tree = new PointSET();
//		tree.insert(new Point2D(5, 3));
//		tree.insert(new Point2D(7, 2));
//		tree.insert(new Point2D(3, 4));
//		tree.insert(new Point2D(2, 1));
//		tree.insert(new Point2D(1, 3));
//		tree.insert(new Point2D(4, 6));
//		tree.insert(new Point2D(6, 1));
//		tree.insert(new Point2D(9, 6));
//		tree.insert(new Point2D(8, 5));
//		tree.insert(new Point2D(7, .5));
//		System.out.println(tree.range(new RectHV(5.5, 0, 8, 2.5)));
//		System.out.println(tree.range(new RectHV(1, 0, 3, 2)));
//		System.out.println();
	}
}
