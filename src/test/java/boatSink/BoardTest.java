package boatSink;

import static org.junit.Assert.*;
import org.junit.Test;

public class BoardTest {
	
	//Test of constructor:
	@Test(expected = IllegalArgumentException.class)
	public void firstConstructorExceptionTest() {
		new Board(0,0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void secondConstructorExceptionTest() {
		new Board(-1,0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void threeConstructorExceptionTest() {
		new Board(-1,-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void fourConstructorExceptionTest() {
		new Board(9,9);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void fiveConstructorExceptionTest() {
		new Board(11,11);
	}
	
	@Test
	public void isShipCellTest() {

	}
	
}
