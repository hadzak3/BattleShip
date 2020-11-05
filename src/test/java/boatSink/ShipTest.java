package boatSink;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {
	
	@Test
	public void isDownTest() {
		OneCellShip ocs;
		ocs = new OneCellShip();
		
		ocs.setDown(0, 0);
		assertTrue(ocs.isDown(0,0));
		
		ocs.setAllShipsDownFalse();
		
		ocs.setDown( 9, 9);
		assertTrue(ocs.isDown(9,9));
		
		ocs.setAllShipsDownFalse();
		
		ocs.setDown(-1, 0);
		assertFalse(ocs.isDown(-1,0));
		
		ocs.setAllShipsDownFalse();
		
		ocs.setDown(10, 0);
		assertFalse(ocs.isDown(10,0));
		
		ocs.setAllShipsDownFalse();
		
		ocs.setDown(0, 10);
		assertFalse(ocs.isDown(0, 10));
		
		ocs.setAllShipsDownFalse();
		
		ocs.setDown(0, -1);
		assertFalse(ocs.isDown(0, -1));
		
		ocs.setAllShipsDownFalse();
		
		ocs.setDown(5, 5);
		assertTrue(ocs.isDown(5, 5));
	}
	
	@Test
	public void shootTestOne() {
		OneCellShip ocs;
		ocs = new OneCellShip();
		
		//Test case 1
		ocs.shoot(0,0);
		int resultHealth = 0;
		assertEquals(resultHealth, ocs.health);
		boolean resultDown = ocs.isDown(0, 0);
		assertTrue(resultDown);
		
		//Test case 2
		ocs.setAllShipsDownFalse();
		ocs.setHealth(1);
		ocs.shoot(10, 10);
		resultHealth = 1;
		resultDown = ocs.isDown(10, 10);
		assertEquals(resultHealth, ocs.health);
		assertFalse(resultDown);
		
		//Test case 3
		ocs.setAllShipsDownFalse();
		ocs.setHealth(1);
		ocs.shoot(1, 0);
		resultHealth = 0;
		resultDown = ocs.isDown(1, 0);
		assertEquals(resultHealth, ocs.health);
		assertTrue(resultDown);
		
		//Test case 4
		ocs.setAllShipsDownFalse();
		ocs.setHealth(1);
		ocs.shoot(10, 10);
		resultHealth = 1;
		resultDown = ocs.isDown(10, 10);
		assertEquals(resultHealth, ocs.health);
		assertFalse(resultDown);
		
		//Test case 5
		ocs.setAllShipsDownFalse();
		ocs.setHealth(1);
		ocs.shoot(0, -1);
		resultHealth = 1;
		resultDown = ocs.isDown(0,-1);
		assertEquals(resultHealth, ocs.health);
		assertFalse(resultDown);
		
		//Test case 6
		ocs.setAllShipsDownFalse();
		ocs.setHealth(1);
		ocs.shoot(5, 5);
		resultHealth = 0;
		resultDown = ocs.isDown(5, 5);
		assertEquals(resultHealth, ocs.health);
		assertTrue(resultDown);
	}
	
	@Test
	public void shootTestTwo() {
		TwoCellShip tcs;
		tcs = new TwoCellShip();
		
		//Test case 1
		tcs.shoot(0,0);
		int resultHealth = 1;
		assertEquals(resultHealth, tcs.health);
		boolean resultDown = tcs.isDown(0, 0);
		assertTrue(resultDown);
		
		//Test case 2
		tcs.setAllShipsDownFalse();
		tcs.setHealth(2);
		tcs.shoot(-1, 0);
		resultHealth = 2;
		resultDown = tcs.isDown(-1, 0);
		assertEquals(resultHealth, tcs.health);
		assertFalse(resultDown);
		
		//Test case 3
		tcs.setAllShipsDownFalse();
		tcs.setHealth(2);
		tcs.shoot(1, 0);
		resultHealth = 1;
		resultDown = tcs.isDown(1, 0);
		assertEquals(resultHealth, tcs.health);
		assertTrue(resultDown);
		
		//Test case 4
		tcs.setAllShipsDownFalse();
		tcs.setHealth(2);
		tcs.shoot(10, 10);
		resultHealth = 2;
		resultDown = tcs.isDown(10, 10);
		assertEquals(resultHealth, tcs.health);
		assertFalse(resultDown);
		
		//Test case 5
		tcs.setAllShipsDownFalse();
		tcs.setHealth(2);
		tcs.shoot(0, -1);
		resultHealth = 2;
		resultDown = tcs.isDown(0,-1);
		assertEquals(resultHealth, tcs.health);
		assertFalse(resultDown);
		
		//Test case 6
		tcs.setAllShipsDownFalse();
		tcs.setHealth(2);
		tcs.shoot(5, 5);
		resultHealth = 1;
		resultDown = tcs.isDown(5, 5);
		assertEquals(resultHealth, tcs.health);
		assertTrue(resultDown);
	}
	
	@Test
    public void shootTestThree() {
        Ship ship = new ThreeCellShip();
        final int threeCellShipHealth = 3;

        //Test case 1
        ship.shoot(-1, 0);
        boolean resultIsDown = ship.isDown(-1, 0);
        assertEquals(3, ship.health);
        assertFalse(resultIsDown);

        ship.setAllShipsDownFalse();
        ship.setHealth(threeCellShipHealth);

        //Test case 2
        ship.shoot(0, -1);
        resultIsDown = ship.isDown(0, -1);
        assertEquals(3, ship.health);
        assertFalse(resultIsDown);

        ship.setAllShipsDownFalse();
        ship.setHealth(threeCellShipHealth);

        //Test case 3
        ship.shoot(9, 0);
        resultIsDown = ship.isDown(9, 0);
        assertEquals(2, ship.health);
        assertTrue(resultIsDown);

        ship.setAllShipsDownFalse();
        ship.setHealth(threeCellShipHealth);

        //Test case 4
        ship.shoot(10, 0);
        resultIsDown = ship.isDown(10, 0);
        assertEquals(3, ship.health);
        assertFalse(resultIsDown);

        ship.setAllShipsDownFalse();
        ship.setHealth(threeCellShipHealth);

        //Test case 5
        ship.shoot(5, 5);
        resultIsDown = ship.isDown(5, 5);
        assertEquals(2, ship.health);
        assertTrue(resultIsDown);
    }
	
	@Test
	public void isCorrectCoordinatesTest() {
		OneCellShip ocs;
		ocs = new OneCellShip();
		
		boolean result1 = ocs.isCorrectCoordinates(-1, 0);
		assertFalse(result1);
		
		boolean result2 = ocs.isCorrectCoordinates(10, 0);
		assertFalse(result2);
		
		boolean result3 = ocs.isCorrectCoordinates(9, 9);
		assertTrue(result3);
		
		boolean result4 = ocs.isCorrectCoordinates(0, 0);
		assertTrue(result4);
		
	}
	
	@Test
	public void isSunkTest() {
		OneCellShip ocs;
		ocs = new OneCellShip();
		
		ocs.setHealth(0);
		assertTrue(ocs.isSunk());

		ocs.setHealth(1);
		assertFalse(ocs.isSunk());

		ocs.setHealth(-1);
		assertFalse(ocs.isSunk());
	}
	
	
	
}
