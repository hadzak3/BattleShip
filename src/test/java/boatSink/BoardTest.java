package boatSink;

import static org.junit.Assert.*;
import org.junit.Test;

public class BoardTest {
	final boolean TRACE_MODE = false; // true to show test info.
	
	@Test(expected = IllegalArgumentException.class)
	public void firstConstructorExceptionTest() {
		new Board(-1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void secondConstructorExceptionTest() {
		new Board(0, -1);
	}
	
	//Test of constructor:
	@Test(expected = IllegalArgumentException.class)
	public void thirdConstructorExceptionTest() {
		new Board(0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void fourthConstructorExceptionTest() {
		new Board(5, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void fifthConstructorExceptionTest() {
		new Board(9, 9);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void sixthConstructorExceptionTest() {
		new Board(9, 10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void seventhConstructorExceptionTest() {
		new Board(10, 9);
	}
	
	@Test
	public void createOneCellShipTest() {
		if (TRACE_MODE) { 
			System.out.println("start createOneCellShipTest");
		}
		
		Board board = new Board(10, 10);
		Ship ship = new OneCellShip();
		int coordinates[][] = {{-1, 0}, {0, -1}, {0, 0}, {5, 5}, {9, 9}, {9, 10}, {10, 9}};
		boolean expected[] = {false, false, true, true, true, false, false}; 
		boolean result;
		for (int i = 0; i < coordinates.length; i++) {
			result = board.createShip(coordinates[i][0], coordinates[i][1], "", ship);
			if (TRACE_MODE) {
				System.out.printf("coordinates at [%d,%d] expected to be %b --> ", 
						coordinates[i][0], 
						coordinates[i][1],
						expected[i]);
				System.out.printf("current is %b\n", result); 
			}
			assertTrue(result == expected[i]);
		}
		
		if (TRACE_MODE) { 
			System.out.println("end createOneCellShipTest\n");
		}
	}
		
	@Test
	public void createTwoCellShipTest() {
		if (TRACE_MODE) { 
			System.out.println("start createTwoCellShipTest");
		}
		
		Board board = new Board(10, 10);
		Ship ship = new TwoCellShip();
		String orientations[] = {"h", "h", "h", "v", "h", "v", "h", "v", "v", "v", "h"}; 
		int coordinates[][][] = {
				{
					{-1, 0}, {-1, 0}, 
				},
				{
					{0, -1}, {0, -1}, 
				},
				{
					{0, 0}, {0, 1}, 
				},
				{
					{0, 0}, {0, 0}, 
				},
				{
					{1, 1}, {1, 2}, 
				},
				{
					{5, 5}, {6, 5}, 
				},
				{
					{6, 5}, {6, 6}, 
				},
				{
					{9, 9}, {8, 9}, 
				},
				{
					{9, 9}, {8, 9}, 
				},
				{
					{9, 10}, {9, 10}, 
				},
				{
					{10, 9}, {10, 9}, 
				},
			};
		
		boolean expected[][] = {
				{
					false, false,
				},
				{
					false, false,
				},
				{
					true, true,
				},
				{
					false, true,
				},
				{
					true, true,
				},
				{
					true, true,
				},
				{
					false, false,
				},
				{
					true, true,
				},
				{
					false, true,
				},
				{
					false, false,
				},
				{
					false, false,
				},
			};
		boolean result2;
		for (int i = 0; i < coordinates.length; i++) {		
			for (int j = 0; j < ship.getNCells(); j++) {
				if (j == 0) { // first coordinates creates a ship
				    result2 = board.createShip(coordinates[i][j][0], coordinates[i][j][1], orientations[i], ship);
				} else { // then we check if at given coordinates is a ship
					result2 = board.isShipCell(coordinates[i][j][0], coordinates[i][j][1]);
				}
				if (TRACE_MODE) {
					System.out.printf("coordinates at [%d,%d] expected to be %b --> ", 
							coordinates[i][j][0], 
							coordinates[i][j][1], 
							expected[i][j]);
					
					System.out.printf("current is %b\n", result2); 
				}
				assertTrue(result2 == expected[i][j]);
			}
		}
		
		if (TRACE_MODE) { 
			System.out.println("end createTwoCellShipTest\n");
		}
	}
	
	@Test
	public void isShipCellTest() {
		// TODO
	}
	
}
