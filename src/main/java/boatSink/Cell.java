package boatSink;

public class Cell {

	private int x, y;
	private Ship ship = null;
	
	Cell(int x, int y, Ship ship){
		this.x = x;
		this.y = y;
		this.ship = ship;
	}
	public void setX(int x) {
		this.x = x;
	}
	 
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	 
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	public Ship getShip() {
		return this.ship;
	}
	
	public boolean isShip() {	
		return this.ship != null;
	}
	
	public void shootShip() {
		this.ship.shoot();
	}
	
	public boolean isShipDown() {
		return this.ship.isDown();
	}
	
	@Override
	public String toString() {
		String out = "-"; // Water
		if (this.ship != null) {
			out = "s"; // ship
			if (this.ship.isSunk()) {
				out = "+"; // Sunk
			}
		}
		
		return out;
	}
}
