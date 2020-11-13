package boatSink;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
	@Test(expected = IllegalArgumentException.class)
	public void constructorExceptionFirstTest() {
		new Player("", null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void constructorExceptionSecondTest() {
		new Player("correct name", null);
	}
	@Test
	public void constructorTestCorrect() {
		Board board = new Board(10,10);
		Board sameBoard = new Board(10,10);
		Player player1 = new Player("player1", board);
		
		//Test of getter name
		assertEquals(player1.getName(), "player1");
		
		assertEquals(player1.getBoard().getShips(), sameBoard.getShips());
	}
	@Test
	public void isEndGameTest() {
		//Test 1 - Board with one ship down (isEndGame=true)
		Board board = new Board(10, 10);
		OneCellShip ocs1 = new OneCellShip();
		ocs1.setSunk();
		board.createShip(0, 0, "h", ocs1);
		Player p1 = new Player("name", board);
		assertTrue(p1.isEndGame());
		
		//Test 2 - Board with one ship alive(isEndGame=false)
		Board board2 = new Board(10,10);
		OneCellShip ocs2 = new OneCellShip();
		board2.createShip(0, 0, "h", ocs2);
		Player p2 = new Player("name", board2); 
		assertFalse(p2.isEndGame());
		
		//Test 3 - Board with two ship down (isEndGame=true)
		Board board3 = new Board(10,10);
		OneCellShip ocs3_0 = new OneCellShip();
		OneCellShip ocs3_1 = new OneCellShip();
		ocs3_0.setSunk();
		ocs3_1.setSunk();
		board3.createShip(0, 0, "h", ocs3_0);
		board3.createShip(9, 9, "h", ocs3_1);
		Player p3 = new Player("name", board3);
		assertTrue(p3.isEndGame());
		
		//Test 4 - Board with one ship alive and the other is down (isEndGame = false)
		Board board4 = new Board(10,10);
		OneCellShip ocs4_0 = new OneCellShip();
		OneCellShip ocs4_1 = new OneCellShip();
		ocs4_0.setSunk();
		board4.createShip(0, 0, "h", ocs4_0);
		board4.createShip(0, 1, "h", ocs4_1);
		Player p4 = new Player("name", board4);
		assertFalse(p4.isEndGame());
		
		//Test 5 - Board with no one ship 
		Board board5 = new Board(10,10);
		Player p5 = new Player("name", board5);
		assertTrue(p5.isEndGame());
		
	}

	@Test
	public void setAllShootsBoardWaterTest() {
		String expected[][] = new String[Constants.N_BOARD_ROWS_CELLS][Constants.N_BOARD_ROWS_CELLS];;
		String obtained[][];
		Player p = new Player();
		
		//Expected result
		for(int i = 0; i < Constants.N_BOARD_ROWS_CELLS; i++) {
			for(int j = 0; j < Constants.N_BOARD_ROWS_CELLS; j++) {
				expected[i][j] = "-"; 
			}
		}
		
		obtained = p.setAllShootsBoardWater();
		
		assertArrayEquals(expected, obtained);
	}
	
	@Test
	public void shootTestCorrect() {
		Player p = new Player();
		Board b = new Board(10,10);
		
		//There is not a ship
		assertFalse(p.shoot(0, 0));
		
		assertFalse(p.shoot(9, 9));
		
		assertFalse(p.shoot(5, 5));
		
		assertFalse(p.shoot(0, 9));
		
		assertFalse(p.shoot(9,0));
		
		//There is a ship (returns true)
		OneCellShip ocs = new OneCellShip();
		b.createShip(0, 0, "h", ocs);
		Player p2 = new Player("player", b);
		assertTrue(p2.shoot(0, 0));
		
		TwoCellShip tcs = new TwoCellShip();
		b.createShip(9, 9, "h", tcs);
		assertTrue(p2.shoot(9, 9));
		
		assertTrue(p2.shoot(9, 8));
		
		ThreeCellShip threeCellShip = new ThreeCellShip();
		b.createShip(5, 5, "h",threeCellShip);
		assertTrue(p2.shoot(5, 5));
		assertTrue(p2.shoot(5, 6));
		assertTrue(p2.shoot(5, 7));
		
		FourCellShip fcs = new FourCellShip();
		b.createShip(9, 0, "v", fcs);
		assertTrue(p2.shoot(9, 0));
		assertTrue(p2.shoot(8, 0));
		assertTrue(p2.shoot(7, 0));
		assertTrue(p2.shoot(6, 0));
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void shootTestIncorrectInput1() {
		Player p = new Player();
		p.shoot(10, 0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void shootTestIncorrectInput2() {
		Player p = new Player();
		p.shoot(0, 10);
	}
	@Test(expected = IllegalArgumentException.class)
	public void shootTestIncorrectInput3() {
		Player p = new Player();
		p.shoot(10, 10);
	}
	@Test(expected = IllegalArgumentException.class)
	public void shootTestIncorrectInput4() {
		Player p = new Player();
		p.shoot(-1, 0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void shootTestIncorrectInput5() {
		Player p = new Player();
		p.shoot(0, -1);
	}
	@Test(expected = IllegalArgumentException.class)
	public void shootTestIncorrectInput6() {
		Player p = new Player();
		p.shoot(-100, 0);
	}
}
