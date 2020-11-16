package models;

import static org.junit.Assert.*;

import org.junit.Test;

import model.FourCellShip;
import model.OneCellShip;
import model.Ship;
import model.ThreeCellShip;
import model.TwoCellShip;

public class ShipTest {
	
	@Test
	public void isCorrectCoordinatesTest() {
		// doesn't matter which type of ship we instantiate, test output is the same.
		Ship ship = new OneCellShip();
		
		/* test case 1. */
		boolean result1 = ship.isCorrectCoordinates(-1, 0);
		assertFalse(result1);
		
		/* test case 2. */
		boolean result2 = ship.isCorrectCoordinates(0, 0);
		assertTrue(result2);
		
		/* test case 3. */
		boolean result3 = ship.isCorrectCoordinates(5, 5);
		assertTrue(result3);
		
		/* test case 4. */
		boolean result5 = ship.isCorrectCoordinates(9, 9);
		assertTrue(result5);
		
		/* test case 5. */
		boolean result4 = ship.isCorrectCoordinates(10, 0);
		assertFalse(result4);
	}
	
	@Test
	public void isSunkTest() {
		// doesn't matter which type of ship we instantiate, test output is the same.
		Ship ship = new OneCellShip();
		
		/* test case 1. */
		ship.setHealth(-1);
		assertFalse(ship.isSunk());
		
		/* test case 2. */
		ship.setHealth(0);
		assertTrue(ship.isSunk());

		/* test case 3. */
		ship.setHealth(1);
		assertFalse(ship.isSunk());
	}
	
	@Test
	public void isDownTest() {
		// doesn't matter which type of ship we instantiate, test output is the same.
		Ship ship = new OneCellShip(); 
		
		/* test case 1. */
		ship.setDown(-1, 0);
		assertFalse(ship.isDown(-1, 0));
		
		ship.setAllShipsDownFalse();
		
		/* test case 2. */
		ship.setDown(0, -1);
		assertFalse(ship.isDown(0, -1));
		
		ship.setAllShipsDownFalse();
		
		/* test case 3. */
		ship.setDown(0, 0);
		assertTrue(ship.isDown(0, 0));
		
		ship.setAllShipsDownFalse();
		
		/* test case 4. */
		ship.setDown(5, 5);
		assertTrue(ship.isDown(5, 5));
		
		ship.setAllShipsDownFalse();
		
		/* test case 4. */
		ship.setDown(9, 9);
		assertTrue(ship.isDown(9, 9));
		
		ship.setAllShipsDownFalse();
		
		/* test case 5. */
		ship.setDown(10, 0);
		assertFalse(ship.isDown(10, 0));
		
		ship.setAllShipsDownFalse();
		
		/* test case 6. */
		ship.setDown(0, 10);
		assertFalse(ship.isDown(0, 10));
	}

	@Test
	public void oneCellShipShootTestInvalidInput() {
        Ship ship = new OneCellShip();
        final int oneCellShipHealth = 1;

        ship.shoot(-1, 0);
        assertFalse(ship.isDown(-1, 0));
        assertEquals(ship.getHealth(), 1);

        ship.setAllShipsDownFalse();
        ship.setHealth(oneCellShipHealth);

        ship.shoot(0, -1);
        assertFalse(ship.isDown(0, -1));
        assertEquals(ship.getHealth(), 1);

        ship.setAllShipsDownFalse();
        ship.setHealth(oneCellShipHealth);
        
        ship.setAllShipsDownFalse();
        ship.setHealth(oneCellShipHealth);

        ship.shoot(10, 0);
        assertFalse(ship.isDown(10, 0));
        assertEquals(ship.getHealth(), oneCellShipHealth);

        ship.setAllShipsDownFalse();
        ship.setHealth(oneCellShipHealth);
        
        ship.shoot(0, 10);
        assertFalse(ship.isDown(0, 10));
        assertEquals(ship.getHealth(), oneCellShipHealth);
	}
	
	@Test
	public void oneCellShipShootTestValidInput() {
		Ship ship = new OneCellShip();
		final int shipHealth = 0;
        ship.shoot(0, 0);
        assertTrue(ship.isDown(0, 0));
        assertEquals(ship.getHealth(), shipHealth);
        
        ship = new OneCellShip();
        ship.shoot(0, 9);
        assertTrue(ship.isDown(0, 9));
        assertEquals(ship.getHealth(),shipHealth);
        
        ship = new OneCellShip();
        ship.shoot(5, 5);
        assertTrue(ship.isDown(5, 5));
        assertEquals(ship.getHealth(),shipHealth);
        
        ship = new OneCellShip();
        ship.shoot(9, 9);
        assertTrue(ship.isDown(9, 9));
        assertEquals(ship.getHealth(), shipHealth);
	}
	
	@Test
	public void twoCellShipShootTestInvalidInput() {
        Ship ship = new TwoCellShip();
        final int twoCellShipHealth = 2;

        ship.shoot(-1, 0);
        assertFalse(ship.isDown(-1, 0));
        assertTrue(ship.getHealth() == 2);

        ship.setAllShipsDownFalse();
        ship.setHealth(twoCellShipHealth);

        ship.shoot(0, -1);
        assertFalse(ship.isDown(0, -1));
        assertTrue(ship.getHealth() == twoCellShipHealth);

        ship.setAllShipsDownFalse();
        ship.setHealth(twoCellShipHealth);
 

        ship.shoot(10, 0);
        assertFalse(ship.isDown(10, 0));
        assertTrue(ship.getHealth() == twoCellShipHealth);

        ship.setAllShipsDownFalse();
        ship.setHealth(twoCellShipHealth);
        
        ship.shoot(0, 10);
        assertFalse(ship.isDown(0, 10));
        assertTrue(ship.getHealth() == twoCellShipHealth);
	}
	
	@Test
	public void twoCellShipShootTestValidInput() {
		Ship ship = new TwoCellShip();
		final int healthOfTwoCellShipShooted = 1;
		
        ship.shoot(0, 0);
        assertTrue(ship.isDown(0, 0));
        assertTrue(ship.getHealth() == healthOfTwoCellShipShooted);
        
        ship = new TwoCellShip();
        ship.shoot(9, 9);
        assertTrue(ship.isDown(9, 9));
        assertTrue(ship.getHealth() == healthOfTwoCellShipShooted);
        
        ship = new TwoCellShip();
        ship.shoot(0, 9);
        assertTrue(ship.isDown(0, 9));
        assertTrue(ship.getHealth() == healthOfTwoCellShipShooted);
        
        ship = new TwoCellShip();
        ship.shoot(9, 0);
        assertTrue(ship.isDown(9, 0));
        assertTrue(ship.getHealth() == healthOfTwoCellShipShooted);
        
        ship = new TwoCellShip();
        ship.shoot(5, 5);
        assertTrue(ship.isDown(5, 5));
        assertTrue(ship.getHealth() == healthOfTwoCellShipShooted);
        
        ship = new TwoCellShip();
        ship.shoot(8, 9);
        assertTrue(ship.isDown(8, 9));
        assertTrue(ship.getHealth() == healthOfTwoCellShipShooted);
        
        ship = new TwoCellShip();
        ship.shoot(9, 8);
        assertTrue(ship.isDown(9, 8));
        assertTrue(ship.getHealth() == healthOfTwoCellShipShooted);
        
        ship = new TwoCellShip();
        ship.shoot(0, 1);
        assertTrue(ship.isDown(0, 1));
        assertTrue(ship.getHealth() == healthOfTwoCellShipShooted);
        
        ship = new TwoCellShip();
        ship.shoot(1, 0);
        assertTrue(ship.isDown(1, 0));
        assertTrue(ship.getHealth() == healthOfTwoCellShipShooted);
        
        
	}
	@Test
    public void threeCellShipShootTestInvalidInput() {
        Ship ship = new ThreeCellShip();
        final int threeCellShipHealth = 3;

        ship.shoot(-1, 0);
        assertFalse(ship.isDown(-1, 0));
        assertTrue(ship.getHealth() == threeCellShipHealth);

        ship.setAllShipsDownFalse();
        ship.setHealth(threeCellShipHealth);

        ship.shoot(0, -1);
        assertFalse(ship.isDown(0, -1));
        assertTrue(ship.getHealth() == threeCellShipHealth);

        ship.setAllShipsDownFalse();
        ship.setHealth(threeCellShipHealth);

        ship.shoot(10, 0);
        assertFalse(ship.isDown(10, 0));
        assertTrue(ship.getHealth() == threeCellShipHealth);

        ship.setAllShipsDownFalse();
        ship.setHealth(threeCellShipHealth);
        
        ship.shoot(0, 10);
        assertFalse(ship.isDown(0, 10));
        assertTrue(ship.getHealth() == threeCellShipHealth);
    }
	
	@Test
    public void threeCellShipShootTestValidInput() {
		final int healthOfThreeCellShipShooted = 2;
		
		Ship ship = new ThreeCellShip();
        
		ship = new ThreeCellShip();
        ship.shoot(1, 0);
        assertTrue(ship.isDown(1, 0));
        assertTrue(ship.getHealth() == healthOfThreeCellShipShooted);
        
		ship = new ThreeCellShip();
        ship.shoot(0, 1);
        assertTrue(ship.isDown(0, 1));
        assertTrue(ship.getHealth() == healthOfThreeCellShipShooted);
        
		ship = new ThreeCellShip();
        ship.shoot(9, 0);
        assertTrue(ship.isDown(9, 0));
        assertTrue(ship.getHealth() == healthOfThreeCellShipShooted);
        
		ship = new ThreeCellShip();
        ship.shoot(0, 9);
        assertTrue(ship.isDown(0, 9));
        assertTrue(ship.getHealth() == healthOfThreeCellShipShooted);
        
		ship = new ThreeCellShip();
        ship.shoot(9, 8);
        assertTrue(ship.isDown(9, 8));
        assertTrue(ship.getHealth() == healthOfThreeCellShipShooted);
        
		ship = new ThreeCellShip();
        ship.shoot(8, 9);
        assertTrue(ship.isDown(8, 9));
        assertTrue(ship.getHealth() == healthOfThreeCellShipShooted);
        
        ship = new ThreeCellShip();
        ship.shoot(9, 9);
        assertTrue(ship.isDown(9, 9));
        assertTrue(ship.getHealth() == healthOfThreeCellShipShooted);
	}
	
	@Test
    public void fourCellShipShootTestInvalidInput() {
        Ship ship = new FourCellShip();
        final int fourCellShipHealth = 4;

        ship.shoot(-1, 0);
        assertFalse(ship.isDown(-1, 0));
        assertTrue(ship.getHealth() == fourCellShipHealth);

        ship.setAllShipsDownFalse();
        ship.setHealth(fourCellShipHealth);

        ship.shoot(0, -1);
        assertFalse(ship.isDown(0, -1));
        assertTrue(ship.getHealth() == fourCellShipHealth);

        ship.setAllShipsDownFalse();
        ship.setHealth(fourCellShipHealth);

        ship.shoot(10, 0);
        assertFalse(ship.isDown(10, 0));
        assertTrue(ship.getHealth() == 4);

        ship.setAllShipsDownFalse();
        ship.setHealth(fourCellShipHealth);
        
        ship.shoot(0, 10);
        assertFalse(ship.isDown(0, 10));
        assertTrue(ship.getHealth() == fourCellShipHealth);
    }
	
	@Test
    public void fourCellShipShootTesValidInput() {
		final int healthOfFourCellShipShooted = 3;
		Ship ship = new FourCellShip();
        ship.shoot(5, 5);
        assertTrue(ship.isDown(5, 5));
        assertTrue(ship.getHealth() == healthOfFourCellShipShooted);
        
		ship = new FourCellShip();
        ship.shoot(0, 0);
        assertTrue(ship.isDown(0, 0));
        assertTrue(ship.getHealth() == healthOfFourCellShipShooted);
        
		ship = new FourCellShip();
        ship.shoot(9, 9);
        assertTrue(ship.isDown(9, 9));
        assertTrue(ship.getHealth() == healthOfFourCellShipShooted);
        
		ship = new FourCellShip();
        ship.shoot(9, 8);
        assertTrue(ship.isDown(9, 8));
        assertTrue(ship.getHealth() == healthOfFourCellShipShooted);
        
		ship = new FourCellShip();
        ship.shoot(0, 9);
        assertTrue(ship.isDown(0, 9));
        assertTrue(ship.getHealth() == healthOfFourCellShipShooted);
        
		ship = new FourCellShip();
        ship.shoot(0, 1);
        assertTrue(ship.isDown(0, 1));
        assertTrue(ship.getHealth() == healthOfFourCellShipShooted);
        
		ship = new FourCellShip();
        ship.shoot(9, 8);
        assertTrue(ship.isDown(9, 8));
        assertTrue(ship.getHealth() == healthOfFourCellShipShooted);
	}
}
