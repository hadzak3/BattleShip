package model;

import constants.Constants;

public class Cell {

	private int x, y;
	private Ship ship;
	private String state;
	
	public Cell(int x, int y, Ship ship){
		if(x < 0 || x >= Constants.N_BOARD_ROWS_CELLS || y < 0 || y >= Constants.N_BOARD_ROWS_CELLS) {
			throw new IllegalArgumentException();
		}
		this.x = x;
		this.y = y;
		this.ship = ship;
		this.state = "-"; // water
		if (this.ship != null) {
			this.state = "+"; // ship
		}
	}
	 
	public void setShip(Ship ship) {
		this.ship = ship;
		if (this.ship != null) {
			this.state = Integer.toString(this.ship.getHealth()); // health remaining
		}
	}
	
	public Ship getShip() {
		return this.ship;
	}
	
	public String getState() {
		return this.state;
	}
	
	public boolean isShip(int x, int y) {	
		return (this.ship != null && this.x == x && this.y == y);
	}
	
	public void shootShip(int x, int y) {
		if (this.isShip(x, y)) { 
			if (this.ship.isDown(x, y)) {
				System.out.println("Barco tocado previamente.");
			} else {
				this.ship.shoot(x, y);
				if (this.ship.isSunk()) {
					this.state = "+"; // sunk
				} else { // this.ship.isDown()
					this.state = Integer.toString(this.ship.getHealth()); // health remaining
				}
			}
		}
	}
	
	public boolean isShipDown(int x, int y) {
		boolean isShipDown = false;
		if (this.isShip(x, y)) {
			isShipDown = this.ship.isDown(x, y);
		}
		
		return isShipDown;
	}
}
