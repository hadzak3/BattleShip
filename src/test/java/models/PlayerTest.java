package models;

import static org.junit.Assert.*;
import org.junit.Test;

import constants.Constants;
import model.Board;
import model.FourCellShip;
import model.OneCellShip;
import model.Player;
import model.Ship;
import model.ThreeCellShip;
import model.TwoCellShip;

public class PlayerTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorExceptionFirstTest() {
		new Player("");
	}
	
	@Test
	public void isCorrectBoardTest() {
		Player player = new Player("someone");
		
		boolean res1 = player.isCorrectBoard(null);
		assertFalse(res1);
		
		boolean res2 = player.isCorrectBoard(new Board(Constants.N_BOARD_ROWS_CELLS, Constants.N_BOARD_ROWS_CELLS));
		assertTrue(res2);
	}
	
	@Test
	public void isEndGameTest() {
		//Test 1 - Board with one ship down (isEndGame=true)
		Player p1 = new Player("name");
		OneCellShip ocs1 = new OneCellShip();
		ocs1.setSunk();
		p1.createShip(0, 0, "h", ocs1);
		assertTrue(p1.isAllShipsSunk());
		
		//Test 2 - Board with one ship alive(isEndGame=false)
		Player p2 = new Player("name"); 
		OneCellShip ocs2 = new OneCellShip();
		p2.createShip(0, 0, "h", ocs2);
		assertFalse(p2.isAllShipsSunk());
		
		//Test 3 - Board with two ship down (isEndGame=true)
		Player p3 = new Player("name");
		OneCellShip ocs3_0 = new OneCellShip();
		OneCellShip ocs3_1 = new OneCellShip();
		ocs3_0.setSunk();
		ocs3_1.setSunk();
		p3.createShip(0, 0, "h", ocs3_0);
		p3.createShip(9, 9, "h", ocs3_1);
		assertTrue(p3.isAllShipsSunk());
		
		//Test 4 - Board with one ship alive and the other is down (isEndGame = false)
		Player p4 = new Player("name");
		OneCellShip ocs4_0 = new OneCellShip();
		OneCellShip ocs4_1 = new OneCellShip();
		ocs4_0.setSunk();
		p4.createShip(0, 0, "h", ocs4_0);
		p4.createShip(0, 1, "h", ocs4_1);
		assertFalse(p4.isAllShipsSunk());
		
		//Test 5 - Board with no one ship 
		Player p5 = new Player("name");
		assertTrue(p5.isAllShipsSunk());
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
		Player p = new Player("player");
		
		//There is not a ship
		assertFalse(p.shoot(0, 0));
		
		assertFalse(p.shoot(9, 9));
		
		assertFalse(p.shoot(5, 5));
		
		assertFalse(p.shoot(0, 9));
		
		assertFalse(p.shoot(9,0));
		
		//There is a ship (returns true)
		OneCellShip ocs = new OneCellShip();
		p.createShip(0, 0, "h", ocs);
		assertTrue(p.shoot(0, 0));
		
		TwoCellShip tcs = new TwoCellShip();
		p.createShip(9, 9, "h", tcs);
		assertTrue(p.shoot(9, 9));
		assertTrue(p.shoot(9, 8));
		
		ThreeCellShip threeCellShip = new ThreeCellShip();
		p.createShip(5, 5, "h",threeCellShip);
		assertTrue(p.shoot(5, 5));
		assertTrue(p.shoot(5, 6));
		assertTrue(p.shoot(5, 7));
		
		FourCellShip fcs = new FourCellShip();
		p.createShip(9, 0, "v", fcs);
		assertTrue(p.shoot(9, 0));
		assertTrue(p.shoot(8, 0));
		assertTrue(p.shoot(7, 0));
		assertTrue(p.shoot(6, 0));
	}

	@Test
	public void shootTestIncorrectInput1() {
		Player p = new Player();
		assertFalse(p.shoot(10, 0));
	}
	
	@Test
	public void shootTestIncorrectInput2() {
		Player p = new Player();
		assertFalse(p.shoot(0, 10));
	}
	
	@Test
	public void shootTestIncorrectInput3() {
		Player p = new Player();
		assertFalse(p.shoot(10, 10));
	}
	
	@Test
	public void shootTestIncorrectInput4() {
		Player p = new Player();
		assertFalse(p.shoot(-1, 0));
	}
	
	@Test
	public void shootTestIncorrectInput5() {
		Player p = new Player();
		assertFalse(p.shoot(0, -1));
	}
	
	@Test
	public void shootTestIncorrectInput6() {
		Player p = new Player();
		assertFalse(p.shoot(-100, 0));
	}
	
	@Test
	public void createShipTest() {
		Player player = new Player("player1");
		Ship ship = new OneCellShip();
		
		assertTrue(player.createShip(0, 0, "h", ship));
		
		assertTrue(player.createShip(9, 9, "h", ship));
		
		assertTrue(player.createShip(5, 5, "h", ship));
		
		assertTrue(player.createShip(0, 9, "h", ship));
		
		assertTrue(player.createShip(9, 0, "h", ship));
		
		assertFalse(player.createShip(0, 0, "h", ship));
		
		assertFalse(player.createShip(9, 9, "h", ship));
		
		assertFalse(player.createShip(5, 5, "h", ship));
		
		assertFalse(player.createShip(0, 9, "h", ship));
		
		assertFalse(player.createShip(9, 0, "h", ship));
		
		Player player2 = new Player("player2");
		
		assertTrue(player2.createShip(0, 0, "v", ship));
		
		assertTrue(player2.createShip(9, 9, "v", ship));
		
		assertTrue(player2.createShip(5, 5, "v", ship));
		
		assertTrue(player2.createShip(0, 9, "v", ship));
		
		assertTrue(player2.createShip(9, 0, "v", ship));
		
		assertFalse(player2.createShip(0, 0, "v", ship));
		
		assertFalse(player2.createShip(9, 9, "v", ship));
		
		assertFalse(player2.createShip(5, 5, "v", ship));
		
		assertFalse(player2.createShip(0, 9, "v", ship));
		
		assertFalse(player2.createShip(9, 0, "v", ship));
	
		Player player3 = new Player("player3");
		
		assertFalse(player3.createShip(10, 0, "h", ship));
		
		assertFalse(player3.createShip(0, 10, "h", ship));
		
		assertFalse(player3.createShip(-1, 0, "h", ship));
		
		assertFalse(player3.createShip(0, -1, "h", ship));
		
		
	}
	
	@Test
	public void gettersTest() {
		Player p = new Player("test name");
		assertNotNull(p.getName());
		assertNotNull(p.getShipsBoard());
		assertNotNull(p.getShootsBoard());
	}
}
