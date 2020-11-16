package models;

import static org.junit.Assert.*;

import org.junit.Test;

import constants.Constants;
import model.Cell;
import model.FourCellShip;
import model.OneCellShip;
import model.Ship;
import model.ThreeCellShip;
import model.TwoCellShip;

public class CellTest {
	
	@Test
	public void isNotShipCellTest() {	
		Cell cell = new Cell();
		
		cell.setShip(null);
		assertFalse(cell.isShip());
	}
	
	@Test
	public void isShipCellTest() {	
		Cell cell = new Cell();
		
		// no importa el tipo de barco instanciado, ya que todos se comportan de forma equivalente en cuanto al test.
		cell.setShip(new OneCellShip());
		assertTrue(cell.isShip());
	}
	
	@Test 
	public void waterCellStateTest() {
		Cell c = new Cell();
		
		assertEquals(c.getState(), Constants.WATER_CELL_STATE);		
	}
	
	@Test 
	public void shipCellStateTest() {
		Cell c = new Cell();
		
		// no importa el tipo de barco instanciado, todos se comportan de forma equivalente en cuanto al test.
		c.setShip(new TwoCellShip());
		assertEquals(c.getState(), Integer.toString(c.getShip().getHealth()));
	}
	
	@Test 
	public void sunkCellStateTest() {
		Cell c = new Cell();
		
		/* test case 1: barco de una casilla hundido. */
		c.setShip(new OneCellShip());
		c.shootShip(5, 5);
		assertEquals(c.getState(), Constants.SUNK_CELL_STATE);
		
		/* test case 2: barco de dos casillas hundido. */
		c.setShip(new TwoCellShip());
		c.shootShip(5, 5);
		c.shootShip(5, 6);
		assertEquals(c.getState(), Constants.SUNK_CELL_STATE);
		
		/* test case 3: barco de tres casillas hundido. */
		c.setShip(new ThreeCellShip());
		c.shootShip(5, 5);
		c.shootShip(5, 6);
		c.shootShip(5, 7);
		assertEquals(c.getState(), Constants.SUNK_CELL_STATE);
		
		/* test case 4: barco de cuatro casillas hundido. */
		c.setShip(new FourCellShip());
		c.shootShip(5, 5);
		c.shootShip(5, 6);
		c.shootShip(5, 7);
		c.shootShip(5, 8);
		assertEquals(c.getState(), Constants.SUNK_CELL_STATE);
	}
	
	@Test
	public void shootNoShipTest() {
		Cell cell = new Cell();
		
		String prevState = cell.getState();
		cell.shootShip(5, 5);
		assertEquals(prevState, cell.getState());
	}
	
	@Test
	public void shootShipTest() {
		Cell cell = new Cell();
		
		// no importa el tipo de barco instanciado, todos se comportan de forma equivalente en cuanto al test.
		Ship ship = new OneCellShip();
		
		cell.setShip(ship);
		String prevState = cell.getState();
		cell.shootShip(0, 0);
		assertFalse(prevState.equals(cell.getState()));
	}
	
	
	@Test
	public void shootPrevShipTest() {
		Cell cell = new Cell();
		
		// no importa el tipo de barco instanciado, todos se comportan de forma equivalente en cuanto al test.
		Ship ship = new OneCellShip();
		cell.setShip(ship);
		cell.shootShip(0, 0);
		String prevState = cell.getState();
		cell.shootShip(0, 0);
		assertEquals(prevState, cell.getState());
	}

	@Test
	public void isShipDownFalseTest() { 
		Cell cell = new Cell();

		assertFalse(cell.isShipDown(0, 0));
	}
	
	@Test
	public void isShipDownTrueTest() { 
		Cell cell = new Cell();
		// no importa el tipo de barco instanciado, todos se comportan de forma equivalente en cuanto al test. */
		Ship ship = new OneCellShip();
		
		ship.setDown(5, 5);
		cell.setShip(ship);
		assertTrue(cell.isShipDown(5, 5));
	}
}
