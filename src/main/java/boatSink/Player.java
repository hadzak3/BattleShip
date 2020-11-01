package boatSink;

import java.util.ArrayList;

public class Player {

	private String name;
	private ArrayList<Cell> shipCells;
	
	Player(String name) {
		this.name = name;
		this.shipCells = new ArrayList<>();
	}
	
	public String getName() {
		return this.name;
	}

	public void addShip(Cell shipCell) {
		this.shipCells.add(shipCell);
	}
}
