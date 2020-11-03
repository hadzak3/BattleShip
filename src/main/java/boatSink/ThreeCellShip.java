package boatSink;

import java.util.ArrayList;

public class ThreeCellShip extends Ship {
	
	private static int HEALTH = 3;
	
	ThreeCellShip() {
		super(HEALTH);
	}
	
	ThreeCellShip(ArrayList<Cell> cells) {
		super(HEALTH);
		for (Cell cell : cells) {
			this.addCell(cell);
		}
	}
}
