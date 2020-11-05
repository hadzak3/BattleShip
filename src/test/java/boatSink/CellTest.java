package boatSink;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

public class CellTest {
	private Cell c;
	
	//Test of constructor
	@Test
	public void firstCorrectConstructorExceptionTest() {
		c = new Cell(0,0, null); 
	}
	@Test
	public void secondCorrectConstructorExceptionTest() {
		c = new Cell(9,9, null); 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void secondConstructorExceptionTest() {
		c = new Cell(-1,0, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void threeConstructorExceptionTest() {
		c = new Cell(0,-1, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void fourConstructorExceptionTest() {
		c = new Cell(10,10, null);
	}
	
	
	
	

}
