package model;

import constants.Constants;

public class Cell {

	private String state;
	private Ship ship;
	
	public Cell(){
		this.state = "-"; // water
		this.ship = null;
	}
	 
	public void setShip(Ship ship) {
		if (ship == null) {
			this.ship = null;
			this.state = Constants.WATER_CELL_STATE;
		} else {
			this.ship = ship;
			this.state = Integer.toString(this.ship.getHealth());
		}
	}
	
	public Ship getShip() {
		return this.ship;
	}
	
	public String getState() {
		return this.state;
	}
	
	public boolean isShip() {	
		return (this.ship != null);
	}
	
	public void shootShip(int x, int y) {
		if (this.isShip()) { 
			if (this.ship.isDown(x, y)) {
				System.out.println("Barco tocado previamente.");
			} else {
				this.ship.shoot(x, y);
				if (this.ship.isSunk()) {
					System.out.println("Barco de " + this.ship.getNCells() + " casilla(s) hundido.");
					this.state = Constants.SUNK_CELL_STATE;
				} else { // this.ship.isDown()
					System.out.println("Barco de " +  this.ship.getNCells() + " casilla(s) tocado.");
					this.state = Integer.toString(this.ship.getHealth());
				}
			}
		}
	}
	
	public boolean isShipDown(int x, int y) {
		boolean isShipDown = false;
		if (this.isShip()) {
			isShipDown = this.ship.isDown(x, y);
		}
	

		return isShipDown;
	}
}
