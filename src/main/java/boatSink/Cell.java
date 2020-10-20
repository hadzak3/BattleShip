package boatSink;

public class Cell {

	private int x;
	
	private int y;
	
	private Ship ship = null;
	
	Cell(){
		
	}
	public void setX(int x) {
		this.x = x;
	}
	 
	public void setY(int y) {
		this.y = y;
	}
	 

	public void setShip(Ship ship) {
		this.ship = ship;
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
	
	public boolean evaluate(int x, int y) {
		return true;
	}
	
	public boolean isShip() {	
		return this.ship != null;
	}
}
