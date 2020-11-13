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
	public void isEndGameTest() {
		//Test 1 - Board with one ship down (isEndGame=true)
		Board board = new Board(10, 10);
		OneCellShip ocs1 = new OneCellShip();
		ocs1.setSunk();
		board.createShip(0, 0, "h", ocs1);
		assertTrue(board.isEndGame());
		
		//Test 2 - Board with one ship alive(isEndGame=false)
		Board board2 = new Board(10,10);
		OneCellShip ocs2 = new OneCellShip();
		board2.createShip(0, 0, "h", ocs2);
		assertFalse(board2.isEndGame());
		
		//Test 3 - Board with two ship down (isEndGame=true)
		Board board3 = new Board(10,10);
		OneCellShip ocs3_0 = new OneCellShip();
		OneCellShip ocs3_1 = new OneCellShip();
		ocs3_0.setSunk();
		ocs3_1.setSunk();
		board3.createShip(0, 0, "h", ocs3_0);
		board3.createShip(9, 9, "h", ocs3_1);
		assertTrue(board3.isEndGame());
		
		//Test 4 - Board with one ship alive and the other is down (isEndGame = false)
		Board board4 = new Board(10,10);
		OneCellShip ocs4_0 = new OneCellShip();
		OneCellShip ocs4_1 = new OneCellShip();
		ocs4_0.setSunk();
		board4.createShip(0, 0, "h", ocs4_0);
		board4.createShip(0, 1, "h", ocs4_1);
		assertFalse(board4.isEndGame());
		
		//Test 5 - Board with no one ship 
		Board board5 = new Board(10,10);
		assertTrue(board5.isEndGame());
	}
	
	@Test
	public void shootTest() {
		//Test 1 - Shoot to one cell ship
		OneCellShip ocs = new OneCellShip();
		Board b = new Board(10,10);
		b.createShip(0, 0, "h", ocs);
		assertTrue(b.shoot(0, 0));
		
		//Test 2 - Shoot water
		assertFalse(b.shoot(9, 9));
		
		//Test 3 - Shoots near ship
		assertFalse(b.shoot(0, 1));
	}
	
	
	@Test
	public void isShipCellTest() {
		// TODO
	}
	
}
