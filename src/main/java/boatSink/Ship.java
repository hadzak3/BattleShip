package boatSink;

import java.util.ArrayList; 

class Ship {
	
	private String name;
	
	private boolean isDown;
	private boolean isSunk;
	int orientation;
	/* Puede ser 0 o 1 */

	private int sizeOfShip;
	private int health;
	private ArrayList<Cell> listCells;
	Ship(int sizeOfShip, String name, int orientation){
		this.name = name;
		this.orientation = orientation;
		this.sizeOfShip = sizeOfShip;
		this.health = sizeOfShip;
		this.isSunk = false;
		listCells = new ArrayList<>();
	}
	public void addCell(Cell c) {
		
		listCells.add(c);
	}
	public void setDown(boolean down) {
		this.isDown = down;
	}
	public boolean getIsDown() {
		return this.isDown;
	}
	public String getName() {
		return this.name;
	}
	public void shootShip() {
		
		health = health-1;
		isDown = true;
		if(health == 0) {
			System.out.println("Barco " + this.name + " hundido");
			isSunk = true;
		}
		
	}
	public boolean isSunk() {
		return this.isSunk;
	}
}
