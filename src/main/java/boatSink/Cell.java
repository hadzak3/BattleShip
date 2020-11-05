package boatSink;

public class Cell {

	private int x, y;
	private Ship ship = null;
	
	
	Cell(int x, int y, Ship ship){
		//Comprobamos si la x es negativa o superior al tamaño del tablero
		if(x < 0 || x >= Constants.N_BOARD_ROWS_CELLS || y < 0 || y >= Constants.N_BOARD_ROWS_CELLS) {
			throw new IllegalArgumentException();
		}
		this.x = x;
		this.y = y;
		this.ship = ship;
	}
	Cell(){
		
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
	
	public void shootShip(int x, int y) {
		if (this.ship.isDown(x, y)) {
			System.out.println("Barco tocado previamente.");
		} else {
			this.ship.shoot(x, y);
		}
	}
	
	public boolean isShipDown(int x, int y) {
		return this.ship.isDown(x, y);
	}
	
	@Override
	public String toString() {
		String out = "-"; // Water
		if (this.ship != null) {
			out = Integer.toString(this.ship.getHealth()); // ship
			if (this.ship.isSunk()) {
				out = "+"; // Sunk
			}
		}
		
		return out;
	}
}
