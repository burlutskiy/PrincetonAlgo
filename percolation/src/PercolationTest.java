import org.junit.Assert;
import org.junit.Test;

public class PercolationTest {

	@Test
	public void test1x1() {
		Percolation percolation = new Percolation(1);
		Assert.assertFalse(percolation.percolates());
		Assert.assertFalse(percolation.isFull(1, 1));
		percolation.open(1, 1);
		Assert.assertTrue(percolation.percolates());
	}

	@Test
	public void test2x2() {
		Percolation percolation = new Percolation(2);
		Assert.assertFalse(percolation.percolates());
		Assert.assertFalse(percolation.isFull(1, 2));
		Assert.assertFalse(percolation.isFull(2, 1));
		Assert.assertFalse(percolation.isFull(1, 1));
		Assert.assertFalse(percolation.isFull(2, 2));
	}

	@Test
	public void test3x3Multi() {
		Percolation percolation = new Percolation(3);
		percolation.open(1, 3);
		percolation.open(2, 3);
		percolation.open(3, 3);
		percolation.open(3, 1);
		percolation.open(2, 1);
		percolation.open(1, 1);
		Assert.assertTrue(percolation.isFull(2, 1));
	}
	@Test
	public void test3x3() {
		Percolation percolation = new Percolation(3);
		Assert.assertFalse(percolation.percolates());
		percolation.open(2, 2);
		Assert.assertTrue(percolation.isOpen(2, 2));
		Assert.assertFalse(percolation.isFull(2, 2));
		Assert.assertFalse(percolation.isFull(2, 1));
		percolation.open(1, 2);
		percolation.open(3, 2);
		Assert.assertTrue(percolation.percolates());
	}

	@Test
	public void test4x4l() {
		Percolation percolation = new Percolation(4);
		percolation.open(1, 1);
		percolation.open(2, 1);
		percolation.open(2, 2);
		percolation.open(3, 2);
		percolation.open(3, 3);
		percolation.open(4, 4);
		percolation.open(3, 4);
		Assert.assertTrue(percolation.percolates());
	}

	@Test
	public void test4x4r() {
		Percolation percolation = new Percolation(4);
		percolation.open(1, 4);
		percolation.open(2, 4);
		percolation.open(2, 3);
		percolation.open(3, 3);
		percolation.open(3, 2);
		percolation.open(4, 1);
		percolation.open(3, 1);
		Assert.assertTrue(percolation.percolates());
	}

	@Test
	public void test6x6() {
		Percolation percolation = new Percolation(6);
		Assert.assertFalse(percolation.isFull(1, 1));
		Assert.assertFalse(percolation.isFull(6, 6));
		Assert.assertFalse(percolation.isFull(1, 6));
		percolation.open(1, 6);
		Assert.assertTrue(percolation.isFull(1, 6));
		percolation.open(2, 6);
		percolation.open(3, 6);
		percolation.open(4, 6);
		percolation.open(5, 6);
		percolation.open(5, 5);
		percolation.open(4, 4);
		percolation.open(3, 4);
		percolation.open(2, 4);
		percolation.open(2, 3);
		percolation.open(2, 2);
		percolation.open(2, 1);
		percolation.open(3, 1);
		percolation.open(4, 1);
		percolation.open(5, 1);
		percolation.open(5, 2);
		percolation.open(6, 2);
		percolation.open(5, 4);
		Assert.assertTrue(percolation.percolates());
	}

	@Test
	public void test20x20() {
		Percolation percolation = new Percolation(20);
		percolation.open(7, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(9, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 4);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(16, 19);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(3, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(9, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 4);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(3, 3);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(2, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 17);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 20);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(17, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(10, 3);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 17);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(13, 4);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 18);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(3, 4);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(13, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 20);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 18);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(9, 10);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(7, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(7, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(9, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(3, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(2, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 10);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(10, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(9, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(10, 17);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(13, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(9, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(6, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 17);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(6, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 10);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 16);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 16);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(17, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 3);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 20);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(2, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 18);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(13, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 20);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(13, 10);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 17);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(6, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 10);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(3, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 10);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(6, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 10);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(9, 19);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(6, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(9, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(10, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 20);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 8);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 8);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(13, 8);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(2, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 20);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(2, 8);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(20, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 16);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(10, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(2, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 19);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(3, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(20, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 8);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(16, 18);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(9, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(17, 10);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 19);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 8);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(7, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 18);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(13, 18);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 19);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 19);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(17, 4);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(17, 19);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(13, 20);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(7, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(2, 4);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(20, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 16);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 3);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(17, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(17, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(20, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(3, 19);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(16, 17);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(17, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 19);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(10, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 8);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(2, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(6, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 18);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 4);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 17);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(16, 20);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(16, 4);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 3);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(16, 3);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 4);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 8);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(17, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(4, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(7, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(13, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(2, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(16, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 8);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(17, 7);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(16, 8);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 3);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(3, 18);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(7, 8);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(10, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(17, 16);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 4);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(18, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(7, 10);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(16, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(6, 8);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(11, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 9);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(7, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(2, 3);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(13, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(13, 16);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(17, 20);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(13, 19);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(15, 19);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 10);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 13);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 17);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(14, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(6, 10);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(7, 16);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(20, 19);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(9, 11);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(3, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(16, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(20, 1);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(7, 18);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(20, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(7, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(9, 2);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 20);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(3, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(3, 14);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(3, 5);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 18);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(19, 17);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(16, 12);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(20, 4);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(5, 16);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(6, 3);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(8, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(12, 15);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(2, 16);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(16, 6);
		Assert.assertFalse(percolation.isFull(18, 1));
		percolation.open(1, 18);
		Assert.assertFalse(percolation.isFull(18, 1));
	}
}
