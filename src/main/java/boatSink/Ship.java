package boatSink;

class Ship {
	
	private String name;
	
	private boolean isDown;
	private boolean isSunk;
	/* Puede ser H o V */
	private int orientation;
	private int sizeOfShip;
	private int health;
	Ship(int sizeOfShip, String name, int orientation){
		this.name = name;
		this.orientation = orientation;
		this.sizeOfShip = sizeOfShip;
		this.health = sizeOfShip;
		this.isSunk = false;
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
		if(health == 0) {
			System.out.println("Barco " + this.name + " hundido");
			isSunk = true;
		}
	}
	public boolean isSunk() {
		return this.isSunk;
	}
}
