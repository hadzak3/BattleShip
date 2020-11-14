package models;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Cell;
import model.FourCellShip;
import model.OneCellShip;
import model.Ship;
import model.ThreeCellShip;
import model.TwoCellShip;

public class CellTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorExceptionFirstTest() {
		new Cell(-1, 0, null); 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorExceptionSecondTest() {
		new Cell(0, -1, null); 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorExceptionThirdTest() {
		new Cell(10, 0, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorExceptionFourthTest() {
		new Cell(0, 10, null);
	}
	
	@Test
	public void isShipTest() {	
		Cell cell;
		
		/* test case 1. */
		cell = new Cell(0, 0, null);
		assertFalse(cell.isShip(-1, 0));
		assertFalse(cell.isShip(0, -1));
		assertFalse(cell.isShip(0, 0));
		assertFalse(cell.isShip(9, 9));
		assertFalse(cell.isShip(10, 0));
		assertFalse(cell.isShip(0, 10));
		
		/* test case 2. */
		cell = new Cell(0, 0, new OneCellShip());
		assertFalse(cell.isShip(-1, 0));
		assertFalse(cell.isShip(0, -1));
		assertTrue(cell.isShip(0, 0));
		assertFalse(cell.isShip(9, 9));
		assertFalse(cell.isShip(10, 0));
		assertFalse(cell.isShip(0, 10));
		
		/* test case 3. */
		cell = new Cell(5, 5, new TwoCellShip());
		assertFalse(cell.isShip(-1, 0));
		assertFalse(cell.isShip(0, -1));
		assertTrue(cell.isShip(5, 5));
		assertFalse(cell.isShip(9, 9));
		assertFalse(cell.isShip(10, 0));
		assertFalse(cell.isShip(0, 10));
		
		/* test case 4. */
		cell = new Cell(5, 7, new ThreeCellShip());
		assertFalse(cell.isShip(-1, 0));
		assertFalse(cell.isShip(0, -1));
		assertTrue(cell.isShip(5, 7));
		assertFalse(cell.isShip(9, 9));
		assertFalse(cell.isShip(10, 0));
		assertFalse(cell.isShip(0, 10));
		
		/* test case 5. */
		cell = new Cell(9, 9, new FourCellShip());
		assertFalse(cell.isShip(-1, 0));
		assertFalse(cell.isShip(0, -1));
		assertTrue(cell.isShip(9, 9));
		assertFalse(cell.isShip(10, 0));
		assertFalse(cell.isShip(0, 10));
	}
	
	@Test
	public void isShipDown() { 
		Cell cell;
		// doesn't matter which type of ship we instantiate, test output is the same.
		Ship ship = new OneCellShip();
		
		/* test case 1. */
		cell = new Cell(0, 0, null);
		assertFalse(cell.isShipDown(-1, 0));
		assertFalse(cell.isShipDown(0, -1));
		assertFalse(cell.isShipDown(0, 0));
		assertFalse(cell.isShipDown(9, 9));
		assertFalse(cell.isShipDown(10, 0));
		assertFalse(cell.isShipDown(0, 10));
		
		ship.setAllShipsDownFalse();
		
		/* test case 2. */
		ship.setDown(0, 0);
		cell = new Cell(0, 0, ship);
		assertFalse(cell.isShipDown(-1, 0));
		assertFalse(cell.isShipDown(0, -1));
		assertTrue(cell.isShipDown(0, 0));
		assertFalse(cell.isShipDown(9, 9));
		assertFalse(cell.isShipDown(10, 0));
		assertFalse(cell.isShipDown(0, 10));
		
		ship.setAllShipsDownFalse();
		
		/* test case 3. */
		ship.setDown(5, 5);
		cell = new Cell(5, 5, ship);
		assertFalse(cell.isShipDown(-1, 0));
		assertFalse(cell.isShipDown(0, -1));
		assertTrue(cell.isShipDown(5, 5));
		assertFalse(cell.isShipDown(9, 9));
		assertFalse(cell.isShipDown(10, 0));
		assertFalse(cell.isShipDown(0, 10));
		
		ship.setAllShipsDownFalse();
		
		/* test case 4. */
		ship.setDown(9, 9);
		cell = new Cell(9, 9, ship);
		assertFalse(cell.isShipDown(-1, 0));
		assertFalse(cell.isShipDown(0, -1));
		assertFalse(cell.isShipDown(5, 5));
		assertTrue(cell.isShipDown(9, 9));
		assertFalse(cell.isShipDown(10, 0));
		assertFalse(cell.isShipDown(0, 10));
	}
	
	@Test
	public void shootShip() {
		Cell cell;
		// doesn't matter which type of ship we instantiate, test output is the same.
		Ship ship = new OneCellShip();
		
		
		/* test case 1. */
		ship.setDown(0, 0);
		cell = new Cell(0, 0, ship);
		cell.shootShip(0, 0);
		assertTrue(cell.getShip().isDown(0, 0));
		
		ship.setAllShipsDownFalse();
		
		/* test case 2. */
		cell = new Cell(5, 5, ship);
		cell.shootShip(5, 5);
		assertTrue(cell.getShip().isDown(5, 5));
		
		/* test case 3. */
		cell.shootShip(-1, -1);
		assertFalse(cell.getShip().isDown(-1, -1));
	}
}
