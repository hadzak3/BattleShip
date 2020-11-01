package boatSink;

import java.util.ArrayList; 

class Ship {
	
	private int id;
	private boolean isDown;
	private boolean isSunk;
	String orientation; /* Puede ser h o v */
	private int shipSize;
	private int health;
	private int playerId;
	private ArrayList<Cell> listCells;
	
	Ship(int shipSize,  String orientation, int playerId){
		this.id = shipSize;
		this.orientation = orientation;
		this.shipSize = shipSize;
		this.health = shipSize;
		this.isSunk = false;
		this.listCells = new ArrayList<>();
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
	
	public int getId() {
		return this.id;
	}
	
	public void shootShip() {
		System.out.println("Barco de " + this.id + " casilla(s) tocado.");
		isDown = true;
		
		health = health-1;
		if(health == 0) {
			System.out.println("Barco de " + this.id + " casilla(s) hundido.");
			isSunk = true;
		}
	}
	
	public boolean isSunk() {
		return this.isSunk;
	}
}
