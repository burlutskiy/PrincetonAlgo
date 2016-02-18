import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import org.junit.Test;

public class FastCollinearPointsTest {

	@Test
	public void testInput6() throws Exception {
		readAndPrintSegments("test/input6.txt");
	}

	@Test
	public void testInput6dup() throws Exception {
		readAndPrintSegments("test/input6dup.txt");
	}

	@Test
	public void testDup() throws Exception {
		Point[] points = new Point[] {new Point(14172,5086), new Point(23711,877),new Point(23711,877) };
		FastCollinearPoints cp = new FastCollinearPoints(points);
		cp.segments();
	}

	@Test
	public void testEdgeCase() throws Exception {
		readAndPrintSegments("test/edgecase16.txt");
	}

	@Test
	public void testInput8() throws Exception {
		readAndPrintSegments("test/input8.txt");
	}

	@Test
	public void testinput40() throws Exception {
		readAndPrintSegments("test/input40.txt");
	}
	
	@Test
	public void testinput80() throws Exception {
		readAndPrintSegments("test/input80.txt");
	}

	@Test
	public void testinput4000() throws Exception {
		readAndPrintSegments("test/input4000.txt");
	}

	@Test
	public void testinput8000() throws Exception {
		readAndPrintSegments("test/input8000.txt");
	}
	
	private void readAndPrintSegments(String testFilePath) throws Exception{
		FileReader fr = new FileReader(new File(testFilePath));
		BufferedReader br = new BufferedReader(fr);
		String line;
		int n = Integer.parseInt(br.readLine());
		Point[] points = new Point[n];
		int i = 0;
		while ((line = br.readLine()) != null) {
			line = line.trim().replaceAll(" {2,}", " ");
			String[] split = line.split(" ");
			if(split != null && split.length == 2)
				points[i++] = new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		}
		br.close();
		FastCollinearPoints bruteCollinearPoints = new FastCollinearPoints(points);
		LineSegment[] segments = bruteCollinearPoints.segments();
		System.out.println(Arrays.toString(segments));
	}
}
