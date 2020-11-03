package boatSink;

import java.util.ArrayList; 

abstract class Ship {
	
	private ArrayList<Cell> cells;
	protected boolean isDown;
	protected int health;
	
	Ship(int shipSize){
		this.cells = new ArrayList<>();
		this.isDown = false;
		this.health = shipSize; 
	}
	
	public void addCell(Cell cell) {
		cells.add(cell);
	}
	
	public boolean isDown() {
		return this.isDown;
	}
	
	public boolean isSunk() {
		return (this.health == 0);
	}
	
	public void shoot() {
		System.out.println("Barco de " + this.cells.size() + " casilla(s) tocado.");
		this.health--;
		this.isDown = true;
	}
}
