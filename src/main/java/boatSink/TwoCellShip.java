package boatSink;

import java.util.ArrayList;

public class TwoCellShip extends Ship {
	
	private static int HEALTH = 2;
	
	TwoCellShip() {
		super(HEALTH);
	}
	
	TwoCellShip(ArrayList<Cell> cells) {
		super(HEALTH);
		for (Cell cell : cells) {
			this.addCell(cell);
		}
	}
}
