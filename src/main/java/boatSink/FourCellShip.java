package boatSink;

import java.util.ArrayList;

public class FourCellShip extends Ship {
	
	private static int HEALTH = 4;
	
	FourCellShip() {
		super(HEALTH);
	}
	
	FourCellShip(ArrayList<Cell> cells) {
		super(HEALTH);
		for (Cell cell : cells) {
			this.addCell(cell);
		}
	}
}
