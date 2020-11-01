package boatSink;

public class Cell {

	private int x;
	
	private int y;
	
	private Ship ship;
	
	Cell(int x, int y, Ship ship){
		this.x = x;
		this.y = y;
		this.ship = null;
	}
	public void setX(int x) {
		this.x = x;
	}
	 
	public void setY(int y) {
		this.y = y;
	}
	 
	public void setShip(Ship ship) {
		this.ship = ship;
		this.ship.addCell(this);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Ship getShip() {
		return this.ship;
	}
	
	public void shootShip() {
		this.ship.shootShip();
	}
	
	public boolean isShipDown() {
		return this.ship.getIsDown();
	}
	
	public boolean isShip() {	
		return this.ship != null;
	}
	
	@Override
	public String toString() {
		String out = "-"; // Water
		
		if (this.ship != null) {
			out = Integer.toString(this.ship.getId()); // Ship id: 1, 2, 3, 4
			if (this.ship.isSunk()) {
				out = "+"; // Sunk
			}
		}
		
		return out;
	}
}
