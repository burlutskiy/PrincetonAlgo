import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import org.junit.Test;

public class BruteCollinearPointsTest {

	@Test
	public void testInput6() throws Exception {
		readAndPrintSegments("test/input6.txt");
	}

	@Test
	public void testInput8() throws Exception {
		readAndPrintSegments("test/input8.txt");
	}

	@Test
	public void testinput40() throws Exception {
		readAndPrintSegments("test/input40.txt");
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
		BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
		LineSegment[] segments = bruteCollinearPoints.segments();
		System.out.println(Arrays.toString(segments));
	}
}
