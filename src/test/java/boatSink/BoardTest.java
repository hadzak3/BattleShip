package boatSink;

import static org.junit.Assert.*;
import org.junit.Test;

public class BoardTest {
	private Board b;
	//Test of constructor:
	@Test(expected = IllegalArgumentException.class)
	public void firstConstructorExceptionTest() {
		b = new Board(0,0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void secondConstructorExceptionTest() {
		b = new Board(-1,0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void threeConstructorExceptionTest() {
		b = new Board(-1,-1);
	}
	@Test(expected = IllegalArgumentException.class)
	public void fourConstructorExceptionTest() {
		b = new Board(9,9);
	}
	@Test(expected = IllegalArgumentException.class)
	public void fiveConstructorExceptionTest() {
		b = new Board(11,11);
	}
	@Test
	public void isShipCellTest() {

	}
	
}
