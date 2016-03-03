/******************************************************************************
 *  Compilation:  javac NearestNeighborVisualizer.java
 *  Execution:    java NearestNeighborVisualizer input.txt
 *  Dependencies: PointSET.java KdTree.java
 *
 *  Read points from a file (specified as a command-line argument) and
 *  draw to standard draw. Highlight the closest point to the mouse.
 *
 *  The nearest neighbor according to the brute-force algorithm is drawn
 *  in red; the nearest neighbor using the kd-tree algorithm is drawn in blue.
 *
 ******************************************************************************/

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;

public class NearestNeighborVisualizer {
	static ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void main(String[] args) throws Exception {
        String filename = "test/input1M2.txt";
        In in = new In(filename);

        StdDraw.show(0);
        StdDraw.setCanvasSize(1240, 950);

        // initialize the two data structures with point from standard input
        final PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.00001);
        brute.draw();
        FuturePoint fpBrute = new FuturePoint();
        FuturePoint fpKdTree = new FuturePoint();
        while (true) {
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();
            Point2D query = new Point2D(x, y);
            StdDraw.copyOffscreen1ToOffscreen();
            
            submitAndGetImmidiatelly(fpBrute, query, brute::nearest);
			if(fpBrute.point != null){
			    StdDraw.setPenRadius(.04);
			    StdDraw.setPenColor(StdDraw.RED);
			    StdDraw.point(fpBrute.point.x(), fpBrute.point.y());
			}
			submitAndGetImmidiatelly(fpKdTree, query, kdtree::nearest);
			if(fpKdTree.point != null){
				StdDraw.setPenRadius(.03);
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.point(fpKdTree.point.x(), fpKdTree.point.y());
			}
			
			StdDraw.setPenRadius(.02);
			StdDraw.setPenColor(StdDraw.GREEN);
			Point2D nearest = kdtree.nearest(query);
			StdDraw.point(nearest.x(), nearest.y());
			StdDraw.show(0);
			StdDraw.show(40);
           }
    }
    
    private static class FuturePoint {
    	Future<Point2D> future;
    	Point2D point;
    }
    
    static void submitAndGetImmidiatelly(FuturePoint fp, Point2D query, Function<Point2D, Point2D> func) throws Exception{
		if(fp.future == null || fp.future.isDone()){
			if(fp.future != null)
				fp.point = fp.future.get();
			fp.future = executor.submit(new Callable<Point2D>() {
				@Override
				public Point2D call() throws Exception {
					return func.apply(query);
				}
			});
		}
    }
}
