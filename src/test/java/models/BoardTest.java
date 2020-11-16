package models;

import static org.junit.Assert.*;
import org.junit.Test;

import model.Board;
import model.FourCellShip;
import model.OneCellShip;
import model.Ship;
import model.ThreeCellShip;
import model.TwoCellShip;

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
		String orientations[] = {"h", "h", "h", "v", "h", "v", "h", "v", "v", "v", "h", "z", "h", "h", "h", "h","h"}; 
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
				{
					{8, 8}, {7, 8}, 
				},
				{
					{9,8}, {9,7},
				},
				{
					{9,6},{9,5},
				},
				{
					{9,3}, {9,4}
				},
				{
					{9,0},{9,1}
				},
				{
					{9,2}, {8,2}
				}
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
				{
					false, false,
				},
				{
					true, true,
				},
				{
					true, true,
				},
				{
					true, true,
				},
				{
					true, true,
				},
				{
					false, false,
				}
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
		
		//Reset board to prove more cases:
		Board board2 = new Board(10,10);
		assertTrue(board2.createShip(0, 1, "h", ship));
		
		assertTrue(board2.isShipCell(0, 2));
		
		assertFalse(board2.createShip(0, 0, "h", ship));
		
		Board board3 = new Board(10,10);
		
		assertTrue(board3.createShip(0, 0, "v", ship));
		
		assertTrue(board3.isShipCell(1, 0));
		
		assertTrue(board3.createShip(3, 0, "v", ship));
		
		assertTrue(board3.isShipCell(4, 0));
		
		assertFalse(board3.createShip(2, 0, "v", ship));
		
		Board board4 = new Board(10,10);
		assertTrue(board4.createShip(1, 0, "v", ship));
		
		assertTrue(board4.isShipCell(2, 0));
		
		assertFalse(board4.createShip(0, 0, "v", ship));
		
		
	}
	
	@Test
	public void isAllShipSunkTest() {
		//Test 1 - Board with one ship down (isEndGame=true)
		Board board = new Board(10, 10);
		OneCellShip ocs1 = new OneCellShip();
		ocs1.setSunk();
		board.createShip(0, 0, "h", ocs1);
		assertTrue(board.isAllShipsSunk());
		
		//Test 2 - Board with one ship alive(isEndGame=false)
		Board board2 = new Board(10,10);
		OneCellShip ocs2 = new OneCellShip();
		board2.createShip(0, 0, "h", ocs2);
		assertFalse(board2.isAllShipsSunk());
		
		//Test 3 - Board with two ship down (isEndGame=true)
		Board board3 = new Board(10,10);
		OneCellShip ocs3_0 = new OneCellShip();
		OneCellShip ocs3_1 = new OneCellShip();
		ocs3_0.setSunk();
		ocs3_1.setSunk();
		board3.createShip(0, 0, "h", ocs3_0);
		board3.createShip(9, 9, "h", ocs3_1);
		assertTrue(board3.isAllShipsSunk());
		
		//Test 4 - Board with one ship alive and the other is down (isEndGame = false)
		Board board4 = new Board(10,10);
		OneCellShip ocs4_0 = new OneCellShip();
		OneCellShip ocs4_1 = new OneCellShip();
		ocs4_0.setSunk();
		board4.createShip(0, 0, "h", ocs4_0);
		board4.createShip(0, 1, "h", ocs4_1);
		assertFalse(board4.isAllShipsSunk());
		
		//Test 5 - Board with no one ship 
		Board board5 = new Board(10,10);
		assertTrue(board5.isAllShipsSunk());
	}
	
	@Test
	public void shootOneCellShipTestValidInput() {
		Ship ship = new OneCellShip();
		Board b = new Board(10,10);
		
		b.createShip(0, 0, "h", ship);
		assertTrue(b.shoot(0, 0));
		
		b.createShip(0, 1, "h", ship);
		assertTrue(b.shoot(0, 1));
		
		b.createShip(9, 9, "h", ship);
		assertTrue(b.shoot(9, 9));
		
		b.createShip(5, 5, "h", ship);
		assertTrue(b.shoot(5, 5));
		
		b.createShip(9, 8, "h", ship);
		assertTrue(b.shoot(9, 8));
		
		b.createShip(8, 9, "h", ship);
		assertTrue(b.shoot(8, 9));
		
		b.createShip(9, 0, "h", ship);
		assertTrue(b.shoot(9, 0));
		
		
	}
	
	@Test
	public void shootOneCellShipTestInvalidInput() {
		Ship ship = new OneCellShip();
		Board b = new Board(10,10);
		
		b.createShip(-1, 0, "h", ship);
		assertFalse(b.shoot(-1, 0));
		
		b.createShip(0, -1, "h", ship);
		assertFalse(b.shoot(0, -1));
		
		b.createShip(-9, -9, "h", ship);
		assertFalse(b.shoot(-9, -9));
		
		b.createShip(-5, -5, "h", ship);
		assertFalse(b.shoot(-5, -5));
		
		b.createShip(-9, -8, "h", ship);
		assertFalse(b.shoot(-9, -8));
		
		b.createShip(-8, -9, "h", ship);
		assertFalse(b.shoot(-8, -9));
		
		b.createShip(-9, 0, "h", ship);
		assertFalse(b.shoot(-9, 0));
		
		b.createShip(10, 0, "h", ship);
		assertFalse(b.shoot(10, 0));
		
		b.createShip(100, 0, "h", ship);
		assertFalse(b.shoot(100, 0));
		
		b.createShip(-100, 0, "h", ship);
		assertFalse(b.shoot(-100, 0));
		
		//orientation = v
		
		b.createShip(-1, 0, "v", ship);
		assertFalse(b.shoot(-1, 0));
		
		b.createShip(0, -1, "v", ship);
		assertFalse(b.shoot(0, -1));
		
		b.createShip(-9, -9, "v", ship);
		assertFalse(b.shoot(-9, -9));
		
		b.createShip(-5, -5, "v", ship);
		assertFalse(b.shoot(-5, -5));
		
		b.createShip(-9, -8, "v", ship);
		assertFalse(b.shoot(-9, -8));
		
		b.createShip(-8, -9, "v", ship);
		assertFalse(b.shoot(-8, -9));
		
		b.createShip(-9, 0, "v", ship);
		assertFalse(b.shoot(-9, 0));
		
		b.createShip(10, 0, "v", ship);
		assertFalse(b.shoot(10, 0));
		
		b.createShip(100, 0, "v", ship);
		assertFalse(b.shoot(100, 0));
		
		b.createShip(-100, 0, "v", ship);
		assertFalse(b.shoot(-100, 0));
		
	
	}
	
	@Test
	public void shootTwoCellShipTestValidInput() {
		Ship ship = new TwoCellShip();
		Board b = new Board(10,10);
		
		b.createShip(0, 0, "h", ship);
		assertTrue(b.shoot(0, 0));
		
		b.createShip(0, 1, "h", ship);
		assertTrue(b.shoot(0, 1));
		
		b.createShip(9, 9, "h", ship);
		assertTrue(b.shoot(9, 9));
		
		b.createShip(5, 5, "h", ship);
		assertTrue(b.shoot(5, 5));
		
		b.createShip(9, 8, "h", ship);
		assertTrue(b.shoot(9, 8));
		
		b.createShip(8, 9, "h", ship);
		assertTrue(b.shoot(8, 9));
		
		b.createShip(9, 0, "h", ship);
		assertTrue(b.shoot(9, 0));
		
		//orientation = v
		
		b.createShip(0, 0, "v", ship);
		assertTrue(b.shoot(0, 0));
		
		b.createShip(0, 1, "v", ship);
		assertTrue(b.shoot(0, 1));
		
		b.createShip(9, 9, "v", ship);
		assertTrue(b.shoot(9, 9));
		
		b.createShip(5, 5, "v", ship);
		assertTrue(b.shoot(5, 5));
		
		b.createShip(9, 8, "v", ship);
		assertTrue(b.shoot(9, 8));
		
		b.createShip(8, 9, "v", ship);
		assertTrue(b.shoot(8, 9));
		
		b.createShip(9, 0, "v", ship);
		assertTrue(b.shoot(9, 0));
	}
	
	@Test
	public void shootTwoCellShipTestInvalidInput() {
		Ship ship = new TwoCellShip();
		Board b = new Board(10,10);
		
		b.createShip(-1, 0, "h", ship);
		assertFalse(b.shoot(-1, 0));
		
		b.createShip(0, -1, "h", ship);
		assertFalse(b.shoot(0, -1));
		
		b.createShip(-9, -9, "h", ship);
		assertFalse(b.shoot(-9, -9));
		
		b.createShip(-5, -5, "h", ship);
		assertFalse(b.shoot(-5, -5));
		
		b.createShip(-9, -8, "h", ship);
		assertFalse(b.shoot(-9, -8));
		
		b.createShip(-8, -9, "h", ship);
		assertFalse(b.shoot(-8, -9));
		
		b.createShip(-9, 0, "h", ship);
		assertFalse(b.shoot(-9, 0));
		
		b.createShip(10, 0, "h", ship);
		assertFalse(b.shoot(10, 0));
		
		b.createShip(100, 0, "h", ship);
		assertFalse(b.shoot(100, 0));
		
		b.createShip(-100, 0, "h", ship);
		assertFalse(b.shoot(-100, 0));
		
		//orientation = v
		
		b.createShip(-1, 0, "v", ship);
		assertFalse(b.shoot(-1, 0));
		
		b.createShip(0, -1, "v", ship);
		assertFalse(b.shoot(0, -1));
		
		b.createShip(-9, -9, "v", ship);
		assertFalse(b.shoot(-9, -9));
		
		b.createShip(-5, -5, "v", ship);
		assertFalse(b.shoot(-5, -5));
		
		b.createShip(-9, -8, "v", ship);
		assertFalse(b.shoot(-9, -8));
		
		b.createShip(-8, -9, "v", ship);
		assertFalse(b.shoot(-8, -9));
		
		b.createShip(-9, 0, "v", ship);
		assertFalse(b.shoot(-9, 0));
		
		b.createShip(10, 0, "v", ship);
		assertFalse(b.shoot(10, 0));
		
		b.createShip(100, 0, "v", ship);
		assertFalse(b.shoot(100, 0));
		
		b.createShip(-100, 0, "v", ship);
		assertFalse(b.shoot(-100, 0));
	}
	@Test
	public void shootThreeCellShipTestValidInput() {
		Ship ship = new ThreeCellShip();
		Board b = new Board(10,10);
		
		b.createShip(0, 0, "h", ship);
		assertTrue(b.shoot(0, 0));
		
		b.createShip(0, 1, "h", ship);
		assertTrue(b.shoot(0, 1));
		
		b.createShip(9, 9, "h", ship);
		assertTrue(b.shoot(9, 9));
		
		b.createShip(5, 5, "h", ship);
		assertTrue(b.shoot(5, 5));
		
		b.createShip(9, 8, "h", ship);
		assertTrue(b.shoot(9, 8));
		
		b.createShip(8, 9, "h", ship);
		assertTrue(b.shoot(8, 9));
		
		b.createShip(9, 0, "h", ship);
		assertTrue(b.shoot(9, 0));
	}
	@Test
	public void shootThreeCellShipTestInvalidInput() {
		Ship ship = new ThreeCellShip();
		Board b = new Board(10,10);
		
		b.createShip(-1, 0, "h", ship);
		assertFalse(b.shoot(-1, 0));
		
		b.createShip(0, -1, "h", ship);
		assertFalse(b.shoot(0, -1));
		
		b.createShip(-9, -9, "h", ship);
		assertFalse(b.shoot(-9, -9));
		
		b.createShip(-5, -5, "h", ship);
		assertFalse(b.shoot(-5, -5));
		
		b.createShip(-9, -8, "h", ship);
		assertFalse(b.shoot(-9, -8));
		
		b.createShip(-8, -9, "h", ship);
		assertFalse(b.shoot(-8, -9));
		
		b.createShip(-9, 0, "h", ship);
		assertFalse(b.shoot(-9, 0));
		
		b.createShip(10, 0, "h", ship);
		assertFalse(b.shoot(10, 0));
		
		b.createShip(100, 0, "h", ship);
		assertFalse(b.shoot(100, 0));
		
		b.createShip(-100, 0, "h", ship);
		assertFalse(b.shoot(-100, 0));
	}
	
	@Test
	public void shootFourCellShipTestValidInput() {
		Ship ship = new FourCellShip();
		Board b = new Board(10,10);
		
		b.createShip(0, 0, "h", ship);
		assertTrue(b.shoot(0, 0));
		
		b.createShip(0, 1, "h", ship);
		assertTrue(b.shoot(0, 1));
		
		b.createShip(9, 9, "h", ship);
		assertTrue(b.shoot(9, 9));
		
		b.createShip(5, 5, "h", ship);
		assertTrue(b.shoot(5, 5));
		
		b.createShip(9, 8, "h", ship);
		assertTrue(b.shoot(9, 8));
		
		b.createShip(8, 9, "h", ship);
		assertTrue(b.shoot(8, 9));
		
		b.createShip(9, 0, "h", ship);
		assertTrue(b.shoot(9, 0));
	}
	
	@Test
	public void shootFourCellShipTestInvalidInput() {
		Ship ship = new TwoCellShip();
		Board b = new Board(10,10);
		
		b.createShip(-1, 0, "h", ship);
		assertFalse(b.shoot(-1, 0));
		
		b.createShip(0, -1, "h", ship);
		assertFalse(b.shoot(0, -1));
		
		b.createShip(-9, -9, "h", ship);
		assertFalse(b.shoot(-9, -9));
		
		b.createShip(-5, -5, "h", ship);
		assertFalse(b.shoot(-5, -5));
		
		b.createShip(-9, -8, "h", ship);
		assertFalse(b.shoot(-9, -8));
		
		b.createShip(-8, -9, "h", ship);
		assertFalse(b.shoot(-8, -9));
		
		b.createShip(-9, 0, "h", ship);
		assertFalse(b.shoot(-9, 0));
		
		b.createShip(10, 0, "h", ship);
		assertFalse(b.shoot(10, 0));
		
		b.createShip(100, 0, "h", ship);
		assertFalse(b.shoot(100, 0));
		
		b.createShip(-100, 0, "h", ship);
		assertFalse(b.shoot(-100, 0));
	}
	
	@Test
	public void shootWaterTest() {
		Board b = new Board(10,10);
		
		assertFalse(b.shoot(9, 9));
		
		assertFalse(b.shoot(0, 0));
		
		assertFalse(b.shoot(0, 9));
		
		assertFalse(b.shoot(9, 0));
		
		assertFalse(b.shoot(5, 5));
		
		assertFalse(b.shoot(1, 0));
		
		assertFalse(b.shoot(0, 1));
		
		//Shoots near a ship
		Ship s = new OneCellShip();
		b.createShip(0, 0, "h", s);
		assertFalse(b.shoot(0, 1));
		
		assertFalse(b.shoot(1, 0));
		
		Board b2 = new Board(10,10);
		s = new TwoCellShip();
		b2.createShip(9, 9, "h", s);
		
		assertFalse(b2.shoot(8, 8));
		assertFalse(b2.shoot(8, 9));
	}
	
	@Test
	public void gettersTest() {
		Board b = new Board(10,10);
		assertEquals(b.getNCols(), 10);
		assertEquals(b.getNRows(), 10);
		assertNotNull(b.getCells());
	}
	
}
