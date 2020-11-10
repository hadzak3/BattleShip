package boatSink;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
	@Test(expected = IllegalArgumentException.class)
	public void constructorExceptionFirstTest() {
		new Player("", null);
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
		p.shoot(0, 0);
		p.shoot(9, 9);
		p.shoot(5, 5);
		p.shoot(0, 9);
		p.shoot(9,0);
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
