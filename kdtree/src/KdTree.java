import java.util.LinkedList;
import java.util.Queue;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
	private static class Node{
		Point2D point;
		Node left;
		Node right;
		public Node(Point2D point, Node left, Node right) {
			super();
			this.point = point;
			this.left = left;
			this.right = right;
		}
	}
	private Node root;
	private int size;
	
	// is the set empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// number of points in the set
	public int size() {
		return size;
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		root = insert(root, p, new Node(p, null, null), 0);
	}

	private double key(Point2D point, int level){
		if(level % 2 == 0)
			return point.x();
		else
			return point.y();
	}
	
	private Node insert(Node node, Point2D p, Node newNode, int level) {
		if (node == null) {
			size++;
			return newNode;
		}
		double cmp = key(p, level) - key(node.point, level);
		if(cmp <= 0)
			node.left = insert(node.left, p, newNode, level + 1);
		else if(cmp >= 0 && !p.equals(node.point))
			node.right = insert(node.right, p, newNode, level + 1);
		return node;
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		return contains(root, p, 0);
	}

	private boolean contains(Node node, Point2D p, int level) {
		if (node == null)
			return false;
		double cmp = key(p, level) - key(node.point, level);
		if(cmp < 0)
			return contains(node.left, p, level + 1);
		else if(cmp >= 0 && !p.equals(node.point))
			return contains(node.right, p, level + 1);
		else
			return true;
	}
	
	// draw all points to standard draw
	public void draw() {
		draw(root);
	}

	private void draw(Node node) {
		if (node == null)
			return;
		draw(node.left);
		StdDraw.point(node.point.x(), node.point.y());
		draw(node.right);
	}

	// all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		Queue<Point2D> queue = new LinkedList<>();
		rangeSearch(root, queue, rect, 0);
		return queue;
	}

	private void rangeSearch(Node node, Queue<Point2D> queue, RectHV rect, int level) {
		if(node == null)
			return;
		if(less(rect, node.point, level))  
			rangeSearch(node.left, queue, rect, level + 1);
		if(rect.contains(node.point)) 
			queue.add(node.point);
		if(more(rect, node.point, level))  
			rangeSearch(node.right, queue, rect, level + 1);
	}

	private boolean less(RectHV rec, Point2D p, int level){
		if(level % 2 == 0)
			return rec.xmin() <= p.x();
		else {
			return rec.ymin() <= p.y();
		}
	}

	private boolean more(RectHV rec, Point2D p, int level){
		if(level % 2 == 0)
			return rec.xmax() >= p.x();
		else {
			return rec.ymax() >= p.y();
		}
	}
	private static class Reference<T> {
		public T value;

		public Reference(T value) {
			this.value = value;
		}
		
	}
	
	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		Reference<Point2D> nearest = new Reference<Point2D>(root.point);
		nearest(root, p, nearest, 0);
		return nearest.value;
	}

	private boolean closer(Point2D point, Point2D query, Point2D nearest){
		return point.distanceTo(query) <= nearest.distanceTo(query);
	}

	private void nearest(Node node, Point2D query, Reference<Point2D> nearest, int level) {
		if (node == null)
			return;
		double cmp = key(query, level) - key(node.point, level);
		if (closer(node.point, query, nearest.value)) {
			nearest.value = node.point;
		}
		Point2D closest = nearest.value;
		if (cmp < 0) {
			nearest(node.left, query, nearest, level + 1);
			if(closest.equals(nearest.value))
					nearest(node.right, query, nearest, level + 1);
		} else {
			nearest(node.right, query, nearest, level + 1);
			if(closest.equals(nearest.value))
				nearest(node.left, query, nearest, level + 1);
		}
	}

}
