package BattleShip;

import controller.BattleShipController;

public class BattleShip {
	
	/** Main class which creates the game. **/
	public static void main(String args[]) {
		BattleShipController controller = new BattleShipController();
		controller.playOneGame();
	}
}
